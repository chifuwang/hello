def docker_version = v1

pipeline {

  agent any 

  tools {
    maven 'maven387'
    jdk 'jdk-17'
  }

  stages {

    stage("Build") {
      steps {
        sh "mvn -version"
        sh "mvn clean package"
        sh "docker info"
        sh "docker build -t 192.168.254.151:9283/hello:${docker_version}"
      }
    }

  }

  post {
    always {
      cleanWs() 
    }

  }

}