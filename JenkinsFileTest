pipeline {
    agent any
    tools {
        gradle 'Gradle8.1'
    }
    stages {
        stage('Clone repository') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'main']], userRemoteConfigs: [[url: 'https://github.com/juanse962/WebScreenplay', credentialsId: 'juanse962']]])
            }
        }
        stage('SonarQube analysis') {
            steps {
                script {
                    scannerHome = tool 'sonarScanner' // Same name as configured in Global Tools in Jenkins
                }
                withSonarQubeEnv('sonarQube') {
                    bat "${scannerHome}/bin/sonar-scanner.bat"
                }
            }
        }
        stage('Build the project') {
            steps {
                bat 'gradle clean build'
            }
        }
        stage('Publish report') {
            steps {
                publishHTML(target: [
                    reportName: 'Serenity',
                    reportDir: 'target',
                    reportFiles: 'index.html',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
                ])
            }
        }
    }
    post {
        always {
            mail to: "juansegomez40@gmail.com",
            subject: "Jenkins Build Result: ${currentBuild.currentResult} - ${env.JOB_NAME}",
            body: "Build Result: ${currentBuild.currentResult}\nJob Name: ${env.JOB_NAME}\nBuild URL: ${env.BUILD_URL}"
        }
    }
}
