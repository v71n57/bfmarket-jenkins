pipelineJob('bfmarket-app-dev') {
  def repo = 'https://github.com/v71n57/bfmarket-app.git'
  description("Pipeline for $repo")
  definition {
    cpsScm {
      scm {
        git {
          remote { url(repo) }
          branch('dev')
          scriptPath('Jenkinsfile')
        }
      }
      lightweight()
    }
  }
}
