pipeline {

  agent any

  stages {

    stage("Build") {
      sh "mvn -version"
      sh "mvn clean install"
    }

  }

  post {
    always {
      cleanWs()
    }

  }

}