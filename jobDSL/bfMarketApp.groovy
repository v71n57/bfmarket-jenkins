#!/usr/bin/env groovy
def repo = "https://github.com/v71n57/bfmarket-app.git"

multibranchPipelineJob("bfmarket-app") {
  displayName("bfmarket-app")
  description("Multi branch pipeline for $repo")
  factory {
    workflowBranchProjectFactory {
      scriptPath('Jenkinsfile')
        }
    }

    branchSources {
        branchSource {
            source {
                git {
                    remote(repo)
                    traits {
                        gitBranchDiscovery()
                        //gitTagDiscovery() // if you need tag discovery
                    }
                }
            }
            strategy {
                defaultBranchPropertyStrategy {
                    props {
                        noTriggerBranchProperty()
                    }
                }
            }
        }
    }
}