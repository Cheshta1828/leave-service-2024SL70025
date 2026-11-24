pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Cheshta1828/leave-service-2024SL70025.git'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Code Inspection') {
            steps {
                withCredentials([string(
                    credentialsId: 'sonar-token',
                    variable: 'SONAR_TOKEN'
                )]) {
                    sh '''
                         mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.11.0.3922:sonar \
                        -Dsonar.projectKey=leave-service \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.token=$SONAR_TOKEN
                    '''
                }
            }
        }
    }
}
