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
      }
    }

  }

  post {
    always {
      cleanWs() 
    }

  }

}