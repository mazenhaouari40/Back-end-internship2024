pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
                git 'https://github.com/mazenhaouari40/Backend_jenkins.git'
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    bat 'mvn clean package'
                }
            }
        }


    // stage('Deploy') {
    //             steps {
    //                 script {
    //                     def artifact = 'target/Backend-0.0.1-SNAPSHOT.jar'
    
    //                     // Define the Render API URL and headers
    //                     def renderApiUrl = 'https://api.render.com/deploy/srv-cqf6jb88fa8c73elcg3g?key=viFKCda2dC0'
    //                     def headers = [
    //                         'Content-Type': 'application/json',
    //                         'Authorization': "Bearer ${RENDER_API_KEY}"
    //                     ]
    
    //                     // Define the JSON payload for the Render deployment API
    //                     def payload = [
    //                         branch: 'main',
    //                         commitMessage: 'Automated deployment from Jenkins'
    //                     ]
    
    //                     // Make the API call to trigger the deployment on Render
    //                     httpRequest(
    //                         url: renderApiUrl,
    //                         httpMode: 'POST',
    //                         requestBody: new groovy.json.JsonBuilder(payload).toString(),
    //                         customHeaders: headers.collect { key, value -> [name: key, value: value] }
    //                     )
    //                 }
    //             }
    //  }








        
        // stage('build') {
        //     steps {
        //         bat 'mvn clean install'
        //     }
        // }

        // stage('Test') {
        //     steps {
        //         // Run your tests
        //         bat 'mvn test'
        //     }
        // }

        // stage('run') {
        //     parallel {
        //         stage('Start Backend') {
        //             steps {
        //                 bat 'java -jar target/Backend-0.0.1-SNAPSHOT.jar'
        //             }
        //         }
        //         stage('Stop Backend') {
        //             steps {
        //                 script {
        //                     echo 'Stopping Spring Boot project...'
        //                     input message: 'Finished using the backend? (Click "Proceed" to continue)'
        //                     bat 'npx kill-port 8081'
        //                 }
        //             }
        //         }
        //     }
        // }
    }
}
