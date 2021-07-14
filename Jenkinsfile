node('master') {

stage('Checkout') {
    checkout scm
  }

stage('Say') {
    echo 'Hello'
    echo "env.PATH=${env.PATH}"
    echo "WORKSPACE=${env.WORKSPACE}"
  }

stage('Faile') {
    sh '/run/current-system/sw/bin/id'
  }  

//    sh 'cp /var/lib/jenkins/workspace/SeedJob/README.md /var/lib/jenkins'
//  stage('Configuration') {
//    sh('cp /var/jenkins_home/workspace/Admin/Configure/resources/config/configuration-as-code-plugin/jenkins.yaml /var/jenkins_home/jenkins.yaml')

//   load('resources/config/groovy/triggerConfigurationAsCodePlugin.groovy')
// }
//  stage('Job Seeding') {
//    jobDsl(targets: 'resources/jobDSL/*.groovy', sandbox: false)
//  }
}
