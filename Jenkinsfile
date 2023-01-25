pipeline {

  agent any 

  tools {
    maven 'maven387'
    jdk 'jdk-17'
  }

  environment {
    DOCKER_BUILD_VERSION = "v1"
  }

  stages {

    stage("Build jar") {
      steps {
        sh "mvn -version"
        sh "mvn clean package"
      }
    }

    stage("Build Docker image") {
      steps {
        sh "./jenkins-script/build-docker.sh"
      }

    }

  }

  post {
    always {
      cleanWs() 
    }

  }

}