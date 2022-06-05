def repo = 'https://github.com/v71n57/bfmarket-app.git'
pipelineJob('bfmarket-app-prod') {
  description("Pipeline for $repo")
  definition {
    cpsScm {
      scm {
        git {
          remote { url(repo) }
          branch('main')
          scriptPath('Jenkinsfile')
        }
      }
      lightweight()
    }
  }
}
