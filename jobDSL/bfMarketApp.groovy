#!/usr/bin/env groovy
def repo = "https://github.com/v71n57/bfmarket-app.git"
multibranchPipelineJob("bfmarket-app") {
    branchSources {
        branchSource {
            source {
                git {
                    remote(repo)
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
    configure {
        def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
        traits << 'jenkins.plugins.git.traits.BranchDiscoveryTrait' {}
    }
    triggers {
        periodic(2) // Trigger every 2 min.
    }
    orphanedItemStrategy { discardOldItems { numToKeep(-1) } }
}