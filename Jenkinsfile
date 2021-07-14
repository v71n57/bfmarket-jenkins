node('master') {

stage('Checkout') {
    checkout scm
  }

 stage('Configuration') {
 sh('cp jenkins.yaml /var/lib/jenkins/jenkins.yaml')
 load('init/triggerConfigurationAsCodePlugin.groovy')
  }    

//    sh 'cp /var/lib/jenkins/workspace/SeedJob/README.md /var/lib/jenkins'

//  stage('Job Seeding') {
//    jobDsl(targets: 'resources/jobDSL/*.groovy', sandbox: false)
//  }
}
