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

    stage("Build") {
      steps {
        sh "mvn -version"
        sh "mvn clean package"
        sh "docker info"
        sh "docker build -t 192.168.254.151:9283/hello:${DOCKER_BUILD_VERSION}"
      }
    }

  }

  post {
    always {
      cleanWs() 
    }

  }

}