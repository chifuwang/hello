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
        sh '''
          ssh -i /var/jenkins_home/ssh/dev chifu@192.168.254.151
          podman info
          whoami
        ''' 
      }

    }

  }

  post {
    always {
      cleanWs() 
    }

  }

}