
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

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t leave-service:2024SL70025 .'
            }
        }

        stage('Deploy to Staging') {
            steps {
                sh '''
                    docker rm -f leave-service-staging 2>/dev/null || true

                    docker run -d \
                        --name leave-service-staging \
                        -e APP_ENV=staging \
                        -p 8085:8081 \
                        leave-service:2024SL70025

                    for i in $(seq 1 15); do
                        if curl -fsS http://localhost:8085; then
                            exit 0
                        fi
                        sleep 2
                    done

                    docker logs leave-service-staging
                    exit 1
                '''
            }
        }

        stage('Approval for Production') {
            steps {
                input message: 'Approve deployment to production?', ok: 'Deploy'
            }
        }

        stage('Deploy to Production') {
            steps {
                sh '''
                    docker rm -f leave-service-production 2>/dev/null || true

                    docker run -d \
                        --name leave-service-production \
                        -e APP_ENV=production \
                        -p 8086:8081 \
                        leave-service:2024SL70025

                    for i in $(seq 1 15); do
                        if curl -fsS http://localhost:8086; then
                            exit 0
                        fi
                        sleep 2
                    done

                    docker logs leave-service-production
                    exit 1
                '''
            }
        }
    }
}
