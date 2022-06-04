#!/usr/bin/env groovy
def call(String dockerCreds, String werfargs){
  // логин в registry
  // первый аргумент регисти - url (если пуст, используем DockerHub)
  // второй - имя Jenkins-секрета, где лежат доступы (login, password)
  docker.withRegistry("https://registry.example.com:5000", "${dockerCreds}") {
    sh """#!/bin/bash -el
          set -xeo pipefail
          trdl add werf https://tuf.werf.io 1 b7ff6bcbe598e072a86d595a3621924c8612c7e6dc6a82e919abe89707d7e3f468e616b5635630680dd1e98fc362ae5051728406700e6274c5ed1ad92bea52a2
          type trdl && source "\$(trdl use werf 1.2 stable)"
          pwd
          id
          werf version
          werf ${werfargs}""".trim()
    }
}