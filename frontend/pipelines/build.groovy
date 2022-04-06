pipeline {
    agent any

    options {
        disableConcurrentBuilds()
    }
    environment {
        def serviceName = "frontend";
        def branchName = "main";
        def versionName = "";
        def dockerRegistry = "vcanna1989/frontend"
        def imageName = "";
        def dockerImage = null;
        registryCredential = 'docker'
    }

    stages {
        stage('Init') {
            steps {
                script {
                    echo 'Init variables'

                    branchName = env.BRANCH_NAME
                    versionName = env.BRANCH_NAME
                    imageName = "${serviceName}:${versionName}"
                }

            }
        }
        stage('clone repo') {
            steps {
                script {
                    echo 'Clone repository'

                    checkout scm
                }
            }
        }
        stage('build image') {
            steps {
                script {
                    echo 'Build docker image'
                    dir('frontend/') {
                      dockerImage = docker.build("vcanna1989/frontend", "-f pipelines/Dockerfile .")
                    }

                }
            }
        }
      stage('add tag'){
        steps {
          script {
            sh('docker tag vcanna1989/frontend vcanna1989/frontend:1.01 ')
          }
        }
      }
      stage('push image to dockerhub') {
        steps {
          script {
            docker.withRegistry( '', registryCredential ) {
              dockerImage.push()
            }
          }
        }
      }
    }
}
