#!/usr/bin/env groovy

node('master') {

stage('Checkout') {
  // Clean workspace and checkout shared library repository on the jenkins master
  // cleanWs()
    checkout scm
  }

stage('Say') {
    echo 'Hello'
  }

stage('copy1') {
    sh('cp README.md /tmp')
  }

stage('copy2') {
    sh('cp /var/lib/jenkins/workspace/SeedJob/README.md /var/lib/jenkins')
  }
//  stage('Configuration') {
// set config file in master
//    sh('cp /var/jenkins_home/workspace/Admin/Configure/resources/config/configuration-as-code-plugin/jenkins.yaml /var/jenkins_home/jenkins.yaml')

// run configuration from config file
//   load('resources/config/groovy/triggerConfigurationAsCodePlugin.groovy')
// }
//  stage('Job Seeding') {
//    jobDsl(targets: 'resources/jobDSL/*.groovy', sandbox: false)
//  }
}
