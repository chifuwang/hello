pipeline {

  agent {
    docker {
      image "docker.io/library/maven:3.8.3-openjdk-17"
    }
  }

  stages {

    stage("Build") {
      steps {
        sh "mvn -version"
        sh "mvn clean package"
      }
    }

  }

  post {
    always {
      deleteDir()
    }

  }

}