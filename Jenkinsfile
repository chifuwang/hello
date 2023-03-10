pipeline {

  agent any 

  tools {
    maven 'maven387'
    jdk 'jdk-17'
  }

  environment {
    DOCKER_BUILD_VERSION = "v2"
    PASS = credentials('nexus_secret')
  }

  triggers {
    pollSCM('* * * * *')
  }

  stages {

    stage("Build jar") {
      steps {
        sh "mvn -version"
        sh "mvn clean package"
      }
    }

    stage("Stop Running Container") {
      steps {
        script {
          sh """
            #!/bin/bash
            ssh -i /var/jenkins_home/ssh/dev chifu@192.168.254.151 << EOF 
            podman container stop hello 
            exit 0
            <<EOF
            """
        } 
      }
    }

    stage("Remove Existing Image") {
      steps {
        script {
          sh """
            #!/bin/bash
            ssh -i /var/jenkins_home/ssh/dev chifu@192.168.254.151 << EOF 
            podman rmi 192.168.254.151:9283/hello:${DOCKER_BUILD_VERSION}
            exit 0
            <<EOF
            """
        } 
      }
    }

    stage("Build Docker image") {
      steps {
        script {
          sh """
            #!/bin/bash
            ssh -i /var/jenkins_home/ssh/dev chifu@192.168.254.151 << EOF 
            cd /home/chifu/dev-podman/jenkins_home/workspace/hello
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
            podman login -u admin -p ${PASS}
            podman push 192.168.254.151:9283/hello:${DOCKER_BUILD_VERSION}  
            exit 0
            <<EOF
            """
        } 
      }
    }

    stage("Delete Local Image") {
      steps {
        script {
          sh """
            #!/bin/bash
            ssh -i /var/jenkins_home/ssh/dev chifu@192.168.254.151 << EOF 
            podman rmi 192.168.254.151:9283/hello:${DOCKER_BUILD_VERSION}  
            exit 0
            <<EOF
            """
        } 
      }
    }

    stage("Pull Docker image") {
      steps {
        script {
          sh """
            #!/bin/bash
            ssh -i /var/jenkins_home/ssh/dev chifu@192.168.254.151 << EOF 
            podman pull 192.168.254.151:9283/hello:${DOCKER_BUILD_VERSION}  
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
            podman run -d --name hello -p 9999:8080 --rm 192.168.254.151:9283/hello:${DOCKER_BUILD_VERSION}
            exit 0
            <<EOF
            """
        } 
      }
    }
    
    stage("Cleanup Dangling Images") {
      steps {
        script {
          sh '''
            #!/bin/bash
            ssh -i /var/jenkins_home/ssh/dev chifu@192.168.254.151 << EOF 
            podman rmi -f $(podman images -f "dangling=true" -q)
            exit 0
            <<EOF
            '''
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