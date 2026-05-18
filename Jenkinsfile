pipeline {
    agent any

    environment {
        PATH = "/usr/local/bin:/usr/bin:/bin:/usr/libexec/docker/cli-plugins:$PATH"
    }

    stages {

        stage('Check Docker') {
            steps {
                sh 'docker --version'
                sh 'docker compose version'
            }
        }

        stage('Build') {
            steps {
                sh 'docker compose build'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'docker compose up --abort-on-container-exit'
            }
        }
    }

    post {
        always {
            sh 'docker compose down -v || true'
        }
    }
}
