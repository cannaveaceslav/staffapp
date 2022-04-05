pipeline {
    agent any

    options {
        disableConcurrentBuilds()
    }
    environment {
        def serviceName = "backend";
        def branchName = "main";
        def versionName = "";
        def dockerRegistry = "europe-west1-docker.pkg.dev/neat-environs-343619/backend"
        def imageName = "";
        def dockerImage = null;
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
        stage('docker publish') {
            steps {
                script {
                    echo 'Publish docker image'
                    sh('echo $dockerHub_PWS | docker login -u $dockerHub_USR --password-stdin')
                    sh 'docker push vcanna1989/backend:1.01 '

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