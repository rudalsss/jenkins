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

        stage('Build Image & Push to ECR') {
            steps {
                script {
                    docker.withRegistry("https://${ECR_REPO}/", 'f9a0baba-b98c-4c1f-b8a2-119d23049e32') {
                            myapp = docker.build('jenkins-images')  // ECR URL 포함
                            myapp.push("${IMAGE_TAG}")  // 태그 명시
                    }
                }
            }
        }
    }
}

// webhook-test!!!
