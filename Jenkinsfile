pipeline(){
    agent any

 

    tools{
        gradle 'Gradle8.1'
    }
    stages{
        stage('Clone repository'){
            steps{
                git 'https://github.com/juanse962/WebScreenplay.git'

 

            }
        }
   stage('SonarQube analysis'){
            steps {
               script {
                        scannerHome = tool 'sonarScanner'//mismo nombre del servidor configurado en las Global Tools Jenkins
               }
               withSonarQubeEnv('sonarQube')//mismo nombre del servidor configurado en la configuracion del sistema jenkins
               {
                 bat "${scannerHome}/bin/sonar-scanner.bat"
               }
            }
        }
        stage('Build the project'){
            steps{
                bat 'gradle clean build'
            }
        }
            stage('Publish report'){
                steps{
                    publishHTML(target: [
                    reportName : 'Serenity',
                    reportDir:   'target',
                    reportFiles: 'index.html',
                    keepAll:     true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
                    ])
                }
        } 
    }           
}
