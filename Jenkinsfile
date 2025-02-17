pipeline {
    agent any

    environment {
        AWS_REGION = 'ap-northeast-2'
        ECR_REPO = '605134473022.dkr.ecr.ap-northeast-2.amazonaws.com/jenkins-images'
        IMAGE_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build JAR') { 
            steps {
                sh 'chmod +x gradlew' // gradlew에 실행 권한을 부여
                sh './gradlew clean build'  // Gradle 빌드 수행
            }
        }

        // 이미지 빌드 및 푸시
        stage('Build Image') {
            steps {
                script {
                    docker.withRegistry("https://${ECR_REPO}/", 'f9a0baba-b98c-4c1f-b8a2-119d23049e32') {
                        myapp = docker.build('jenkins-images')
                    }
                }
            }
        }
        
        stage('Push Image to ECR') {
            steps {
                script {
                    docker.withRegistry("https://${ECR_REPO}/", 'f9a0baba-b98c-4c1f-b8a2-119d23049e32') {
                        myapp.push("${IMAGE_TAG}")
                    }
                }
            }
        }
        
        // 이미지 스캔
        stage('Scan Image with Trivy') {
            steps {
                script {
                    // Trivy로 이미지 스캔하고 HTML 리포트 생성
                    sh '''
                        trivy image --format html --output trivy-report.html 605134473022.dkr.ecr.ap-northeast-2.amazonaws.com/jenkins-images:latest
                    '''
                }
            }
        }

        stage('Publish HTML Report') {
            steps {
                // Jenkins HTML Publisher Plugin을 이용하여 HTML 리포트 출력
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: false,
                    reportDir: '',
                    reportFiles: 'trivy-report.html',  // 리포트 파일 경로
                    reportName: 'Trivy Vulnerability Report'
                ])
            }
        }
    }
}

// webhook-test!!!
