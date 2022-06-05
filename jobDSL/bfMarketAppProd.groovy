pipelineJob('bfmarket-app-prod') {
  def repo = 'https://github.com/v71n57/bfmarket-app.git'
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
