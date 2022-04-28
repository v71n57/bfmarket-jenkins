pipelineJob('bf-market-app') {
  def repo = 'https://github.com/v71n57/hellowerf.git'
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
