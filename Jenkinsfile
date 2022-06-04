#!/usr/bin/env groovy
import jenkins.model.Jenkins

node('master') {

stage('Checkout') {
    checkout scm
  }

stage('Configuration') {
    sh('cp jenkins.yaml /var/lib/jenkins/jenkins.yaml')
    sh ('sudo cp /etc/kubernetes/admin.conf /var/lib/jenkins/kubeconfig')
    sh ('sudo chown jenkins:jenkins /var/lib/jenkins/kubeconfig')
    def jcacPlugin = Jenkins.instance.getExtensionList(io.jenkins.plugins.casc.ConfigurationAsCode.class).first()
    jcacPlugin.configure()
  }

stage('Job Seeding') {
    jobDsl(targets: 'jobDSL/*.groovy', sandbox: false)
  }
}
