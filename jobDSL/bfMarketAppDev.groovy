def repo = 'https://github.com/v71n57/bfmarket-app.git'
def branchName = 'dev'
pipelineJob('bfmarket-app-dev') {
  description("Pipeline for $repo")
  definition {
    cpsScm {
      scm {
        git {
          remote { url(repo) }
          branch(branchName)
          scriptPath('Jenkinsfile')
        }
      }
      lightweight()
    }
  }
}
