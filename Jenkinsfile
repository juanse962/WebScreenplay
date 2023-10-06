pipeline() {
  agent any
  tools {
    gradle 'Gradle8.1'
  }
  stages {
    stage('Clone repository') {
      steps {
        git branch: 'main', credentialsId: 'juanse962', url: 'https://github.com/juanse962/WebScreenplay'          
      }
    }
    stage('SonarQube analysis') {
      steps {
        script {
          scannerHome = tool 'sonarScanner' //mismo nombre del servidor configurado en las Global Tools Jenkins
        }
        withSonarQubeEnv('sonarQube') //mismo nombre del servidor configurado en la configuracion del sistema jenkins
        {
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
      mail to: "juansegomez40@gmail.com", //configure mail in Glogal system and add your email
        subject: "jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
        body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}"
    }
  }
}
