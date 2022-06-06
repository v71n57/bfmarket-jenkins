#!/usr/bin/env groovy
def repo = "https://github.com/v71n57/bfmarket-app.git"

multibranchPipelineJob("bfmarket-app") {
    displayName "my awesome job"
    description "multi-branch pipeline job thingy"
    branchSources {
        git {   
            remote(repo)
        }
    }
    factory {
        workflowBranchProjectFactory {
          scriptPath('Jenkinsfile')
        }
    }
}