pipeline {

  agent any 

  tools {
    maven 'maven387'
    jdk 'jdk-17'
  }

  environment {
    DOCKER_BUILD_VERSION = "v1"
    PASS = credentials('nexus_secret')
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
        script {
          sh """
            #!/bin/bash
            ssh -i /var/jenkins_home/ssh/dev chifu@192.168.254.151 << EOF 
            cd /home/chifu/dev-podman/jenkins_home/workspace/hello
            podman login -u admin -p ${PASS}
            podman build -t 192.168.254.151:9283/hello:${DOCKER_BUILD_VERSION} . 
            exit 0
            <<EOF
            """
        } 
      }

    }

    stage("Push Docker image") {
      steps {
        script {
          sh """
            #!/bin/bash
            ssh -i /var/jenkins_home/ssh/dev chifu@192.168.254.151 << EOF 
            podman push 192.168.254.151:9283/hello:${DOCKER_BUILD_VERSION}  
            exit 0
            <<EOF
            """
        } 
      }
    }
    
    stage("Stop Running Container") {
      steps {
        script {
          sh """
            #!/bin/bash
            ssh -i /var/jenkins_home/ssh/dev chifu@192.168.254.151 << EOF 
            podman container stop hello
            podman container rm hello 
            exit 0
            <<EOF
            """
        } 
      }
    }
    
    stage("Start Container") {
      steps {
        script {
          sh """
            #!/bin/bash
            ssh -i /var/jenkins_home/ssh/dev chifu@192.168.254.151 << EOF 
            podman run -d --name hello -p 8080:8080 --rm 192.168.254.151:9283/hello:${DOCKER_BUILD_VERSION}
            podman container rm hello 
            exit 0
            <<EOF
            """
        } 
      }
    }
  }

  post {
    always {
      cleanWs() 
    }

  }

}