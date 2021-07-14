pipelineJob('BF_Market_App') {
  def repo = 'https://github.com/v71n57/bf-market-app'

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
    }
  }
    configure {
     it / definition / lightweight(true)
  }
}