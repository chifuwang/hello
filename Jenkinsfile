pipeline {

  agent {
    docker {
      image "docker.io/library/maven:3.8.3-openjdk-17"
      args "-u root -v /var/run/docker.sock:/var/run/docker.sock -e DOCKER_HOST='unix:///var/run/docker.sock' "
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
      cleanWs()
    }

  }

}