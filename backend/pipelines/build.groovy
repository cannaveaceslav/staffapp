pipeline {
    agent any

    options {
        disableConcurrentBuilds()
    }
    environment {
        def serviceName = "backend";
        def branchName = "main";
        def versionName = "";
        def dockerRegistry = "vcanna1989/backend"
        def imageName = "";
        def dockerImage = null;
        registryCredential = 'docker'
        DOCKERHUB_CREDENTIALS = credentials('docker');
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
        stage('clone') {
            steps {
                script {
                    echo 'Clone repository'

                    checkout scm
                }
            }
        }
        stage('build') {
            steps {
                script {
                    echo 'Build docker image'
                    dir('backend/') {
                        dockerImage = docker.build("vcanna1989/backend", "-f pipelines/Dockerfile .")
                    }
                }
            }
        }
        stage('add tag'){
            steps {
                script {
                    sh('docker tag vcanna1989/backend vcanna1989/backend:1.01 ')
                }
            }
        }
        stage('Deploy our image') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }
    }
    post {
        always {
            sh 'docker logout'
        }
    }
}