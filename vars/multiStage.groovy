#!/usr/bin/env groovy
def call( Map parameters = [:] ) { // функция принимает в качестве аргумента Map с параметрами
  def namespace = parameters.namespace // имя неймспейса для выката
  // имя ключа по умолчанию для расшифровки секретов (если не указан в параметрах)
  def werf_secret_key = parameters.werfCreds != null ? parameters.werfCreds : "werf-secret-key-default"
  // имя секрета по умолчанию для логина в docker registry
  def dockerCreds = parameters.dockerCreds != null ? parameters.dockerCreds : "docker-credentials-default"
  // получаем имя проекта из имени multibranch pipeline
  def PROJ_NAME = "${env.JOB_NAME}".split('/').first()
  // имя registry в docker hub или адрес до кастомного registry
  def imagesRepo = parameters.imagesRepo != null ? parameters.imagesRepo : "registry.example.com:5000"

  if( namespace == null ) { // единственный обязательный аргумент и проверка на его наличие
    currentBuild.result = 'FAILED'
    return
  }

  pipeline {
    agent { label 'master' }
    options { disableConcurrentBuilds() } // запрещаем параллельную сборку для пайплайна
    environment { // переменные для работы werf
      WERF_IMAGES_REPO="${imagesRepo}"
      WERF_STAGES_STORAGE=":local"
      WERF_TAG_BY_STAGES_SIGNATURE=true
      WERF_ADD_ANNOTATION_PROJECT_GIT="project.werf.io/git=${GIT_URL}"
      WERF_ADD_ANNOTATION_CI_COMMIT="ci.werf.io/commit=${GIT_COMMIT}"
      WERF_LOG_COLOR_MODE="off"
      WERF_LOG_PROJECT_DIR=1
      WERF_ENABLE_PROCESS_EXTERMINATOR=1
      WERF_LOG_TERMINAL_WIDTH=95
      PATH="$PATH:$HOME/bin"
      WERF_KUBECONFIG="$HOME/kubeconfig"
      WERF_SECRET_KEY = credentials("${werf_secret_key}")
    }
    triggers {
      // Execute weekdays every four hours starting at minute 0
      cron('H 21 * * *')
     // для werf cleanup, что будет чистить registry и хост-раннер от устаревших кэшей и образов
    }
    stages {
      stage('Checkout') {
        steps {
          sh "id"
          checkout scm // получаем код из репозитория
        }
      }

      stage('Build & Publish image') {

        when {
            not { triggeredBy 'TimerTrigger' } // чтобы stage не запускался по крону
        }
        steps {
          sh "pwd"
          script {
            // запуск нашего метода из runWerf.groovy
            //${PROJ_NAME}
            runWerf("${dockerCreds}", "build --repo ${imagesRepo}/${PROJ_NAME}")
          }
        }
      }

      stage('Deploy app') {

        when {
            not { triggeredBy 'TimerTrigger' }
          }
        environment {
          // название окружения, куда осуществляется деплой (важно для шаблонизации Helm-чарта)
          // WERF_ENV="production"
        }
        steps {
          runWerf("${dockerCreds}", "converge --repo ${imagesRepo}/${PROJ_NAME}")
        }
      }
      stage('Cleanup werf Images') {

        when {
          allOf {
            triggeredBy 'TimerTrigger'
            branch 'main' 
          }
        }
        steps {
          sh "echo 'Cleaning up werf images'"
          runWerf("${dockerCreds}", "cleanup --repo ${imagesRepo}/${PROJ_NAME}")
        }
      }
    }
  }
}