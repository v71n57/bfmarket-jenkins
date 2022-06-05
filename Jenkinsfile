#!/usr/bin/env groovy
// import jenkins.model.Jenkins

  pipeline {
    agent { label 'master' }
    options { disableConcurrentBuilds() } // запрещаем параллельную сборку для пайплайна
    triggers {
      pollSCM('* * * * *')
    }
    stages {
      stage('Checkout') {
        steps {
          checkout scm // получаем код из репозитория
        }
      }

      stage('Configuration') {
        steps {
          sh ("echo 'Configuration'")
          sh ('cp jenkins.yaml /var/lib/jenkins/jenkins.yaml')
          sh ('sudo cp /etc/kubernetes/admin.conf /var/lib/jenkins/kubeconfig')
          sh ('sudo chown jenkins:jenkins /var/lib/jenkins/kubeconfig')
        }
      }

      stage('Jcac plugin configure') {
        steps {
          sh ("echo 'Jcac plugin configure'")
          script {
            def jcacPlugin = Jenkins.instance.getExtensionList(io.jenkins.plugins.casc.ConfigurationAsCode.class).first()
            jcacPlugin.configure()
          }
        }
      }

      stage('Job Seeding') {
        steps {
          sh ("echo 'Job Seeding'")
          jobDsl(targets: 'jobDSL/*.groovy', sandbox: false)
        }
      }
    }
  }