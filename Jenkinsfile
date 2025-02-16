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

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'which docker'
                    // Docker 이미지 빌드
                    def app = docker.build("backend:${IMAGE_TAG}")
                }
            }
        }

        stage('Push to ECR') {
            steps {
                script {
                    // ECR에 로그인
                    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'f1092972-d562-4db4-be31-ca975527944b']]) {
                        // ECR에 이미지 push
                        echo "aws제발"
                        sh "aws sts get-caller-identity"
                        withDockerRegistry([credentialsId: "f1092972-d562-4db4-be31-ca975527944b", url: "${ECR_REPO}"]) {
                            app.push("${IMAGE_TAG}")
                        }
                    }

                    // 푸시 후 이미지 확인 (ouptut 콘솔)
                    sh "aws ecr describe-images --repository-name jenkins-images --region ${AWS_REGION}"
                }
            }
        }
    }
}
