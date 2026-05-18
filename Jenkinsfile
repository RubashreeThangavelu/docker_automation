pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/RubashreeThangavelu/docker_automation.git'
            }
        }

        stage('Build Containers') {
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
            junit '**/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'reports/**/*', allowEmptyArchive: true
            sh 'docker compose down -v'
        }
    }
}
