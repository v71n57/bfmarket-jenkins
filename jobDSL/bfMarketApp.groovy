pipelineJob('DSL_Pipeline') {
  def repo = 'https://github.com/v71n57/bf-market-app'

  description("Pipeline for $repo")

  definition {
    cpsScm {
      scm {
        git {
          remote { url(repo) }
          branch('main')
          scriptPath('Jenkinsfile')
          //extensions { }  // required as otherwise it may try to tag the repo, which you may not want
        }
      }
    }
  }
}