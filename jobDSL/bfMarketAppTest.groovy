def repo = 'https://github.com/v71n57/bfmarket-app.git'
def branchName = 'test'
pipelineJob('bfmarket-app-test') {
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
