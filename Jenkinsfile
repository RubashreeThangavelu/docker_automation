pipeline {
    agent any

    stages {

        stage('Cleanup') {
            steps {
                sh '''
                docker rm -f tomcat-app || true
                docker rm -f firefox || true
                docker compose down --remove-orphans || true
                '''
            }
        }

        stage('Run Selenium Framework') {
            steps {
                sh '''
                docker compose up --build --abort-on-container-exit
                '''
            }
        }
    }
}
