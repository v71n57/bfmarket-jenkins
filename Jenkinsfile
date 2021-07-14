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

//    sh 'cp /var/lib/jenkins/workspace/SeedJob/README.md /var/lib/jenkins'

//stage('Job Seeding') {
//    jobDsl(targets: 'resources/jobDSL/*.groovy', sandbox: false)
//  }
}
