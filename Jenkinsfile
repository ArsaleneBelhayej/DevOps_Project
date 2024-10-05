pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    environment {
        SCANNER_HOME = tool 'sonar-scanner'
    }

    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'OuerfelliSami_5ARCRIC1_G2', credentialsId: 'github-cred', url: 'https://github.com/samiouerfelli/DEVOPS_PROJECT_G2.git'
            }
        }

        stage('Clean') {
            steps {
                sh "mvn clean"
            }
        }

        stage('Compile') {
            steps {
                sh "mvn compile"
            }
        }

        stage('Setup MySQL') {
                   steps {
                       script {
                           sh 'docker-compose up -d'
                       }
                   }
               }
         stage('Test') {
            steps {
                 sh "mvn test"
                   }
           }

        stage('SonarQube Analysis') {
            steps {
                script {
                        withSonarQubeEnv('sonar') {
                            sh '''
                                $SCANNER_HOME/bin/sonar-scanner \
                                -Dsonar.projectName=FoyerProject \
                                -Dsonar.projectKey=FoyerProject \
                                -Dsonar.java.binaries=.
                            '''
                        }
                    }
                }

        }



        stage('Quality Gate') {
            steps {
                script {
                    waitForQualityGate abortPipeline: false, credentialsId: 'sonar-token'
                }
            }
        }

        stage('Build') {
            steps {
                sh "mvn package"
            }
        }

        stage('Publish To Nexus') {
                    steps {
                       withMaven(globalMavenSettingsConfig: 'global-settings', jdk: 'JDK17', maven: 'Maven3', mavenSettingsConfig: '', traceability: true) {
                            sh "mvn deploy"
                        }
                    }
                }
    }
}
