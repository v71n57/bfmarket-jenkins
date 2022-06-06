#!/usr/bin/env groovy
def repo = "https://github.com/v71n57/hellowerf.git"
def branchName = "main"
pipelineJob("hellowerf-${branchName}") {
  description("Pipeline for $repo branch ${branchName}")
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
