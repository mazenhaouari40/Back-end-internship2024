pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
                git 'https://github.com/mazenhaouari40/Backend_jenkins.git'
            }
        }

        stage('build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Run your tests
                bat 'mvn test'
            }
        }

        stage('run') {
            parallel {
                stage('Start Backend') {
                    steps {
                        bat 'java -jar target/Backend-0.0.1-SNAPSHOT.jar'
                    }
                }
                stage('Stop Backend') {
                    steps {
                        script {
                            echo 'Stopping Spring Boot project...'
                            input message: 'Finished using the backend? (Click "Proceed" to continue)'
                            bat 'npx kill-port 8081'
                        }
                    }
                }
            }
        }
    }
}
