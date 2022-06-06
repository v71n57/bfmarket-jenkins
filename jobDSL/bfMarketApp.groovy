#!/usr/bin/env groovy
def repo = "https://github.com/v71n57/bfmarket-app.git"
// def branchName = "main"
// pipelineJob("bfmarket-app-${branchName}") {
pipelineJob("bfmarket-app") {
  description("Pipeline for $repo")
  definition {
    cpsScm {
      scm {
        git {
          remote { url(repo) }
          // branch(branchName)
          scriptPath('Jenkinsfile')
        }
      }
      lightweight()
    }
  }
}
