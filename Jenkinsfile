#!/usr/bin/env groovy
import jenkins.model.Jenkins

node('master') {

stage('Checkout') {
    checkout scm
  }

stage('Configuration') {
    sh('cp jenkins.yaml /var/lib/jenkins/jenkins.yaml')

    def jcacPlugin = Jenkins.instance.getExtensionList(io.jenkins.plugins.casc.ConfigurationAsCode.class).first()
    jcacPlugin.configure()
  }

stage('Job Seeding') {
    jobDsl(targets: 'jobDSL/*.groovy', sandbox: false)
  }
}
