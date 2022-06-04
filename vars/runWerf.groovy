#!/usr/bin/env groovy
def call(String dockerCreds, String werfargs){
  // логин в registry
  // первый аргумент регисти - url (если пуст, используем DockerHub)
  // второй - имя Jenkins-секрета, где лежат доступы (login, password)
  docker.withRegistry("https://registry.example.com:5000", "${dockerCreds}") {
    sh '''#!/bin/bash -el
          set -o pipefail
          type trdl && source "$(trdl use werf 1.2 stable)"
          pwd
          id
          werf version
          echo 11111 ${werfargs}
          werf ${werfargs}'''.trim()
    }
}