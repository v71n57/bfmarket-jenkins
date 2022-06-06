#!/usr/bin/env groovy
def repo = "https://github.com/v71n57/bfmarket-app.git"

multibranchPipelineJob("bfmarket-app") {
  displayName("my awesome job")
  description("multi branch pipeline for $repo")
  factory {
    workflowBranchProjectFactory {
      scriptPath('Jenkinsfile2')
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