pipeline {

  agent {
    image "maven:3.8.3-openjdk-17"
    label "docker"
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