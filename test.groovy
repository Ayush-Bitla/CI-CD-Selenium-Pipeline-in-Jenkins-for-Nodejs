pipeline {
    agent any

    tools {
        jdk 'java21'  // Use the JDK you configured
        maven 'Maven-3.9.9'
    }

    stages {
        stage('Check Java Version') {
            steps {
                bat 'java -version'  // Ensure JDK 21 is being used
            }
        }

        stage('Get Source Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Ayush-Bitla/CI-CD-Selenium-Pipeline-in-Jenkins-for-Nodejs.git'
            }
        }

        stage('Build Code') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test -Dbrowser=localchrome'
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML([
                    allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, 
                    reportDir: 'target/surefire-reports', 
                    reportFiles: 'Extent*.html', 
                    reportName: 'Test Report'
                ])
            }
        }
    }
}
