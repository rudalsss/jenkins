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

        // stage('Build Docker Image') {
        //     steps {
        //         script {
        //             sh 'which docker'
        //             // Docker 이미지 빌드
        //             def app = docker.build("backend:${IMAGE_TAG}")
        //         }
        //     }
        // }

        stage('Build Image & Push to ECR') {
            steps {
                script {
                    // ECR에 로그인
                    // docker.withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: '2502160524']]) {
                    //     // ECR에 이미지 push
                    //     docker.withRegistry([credentialsId: "2502160524", url: "${ECR_REPO}"]) {
                    //         app.push("${IMAGE_TAG}")
                    //     }
                    // }
                    docker.withRegistry('https://${ECR_REPO}', 'f9a0baba-b98c-4c1f-b8a2-119d23049e32') {
                            myapp = docker.build("${ECR_REPO}:${IMAGE_TAG}")  // ECR URL 포함
                            myapp.push("${IMAGE_TAG}")  // 태그 명시
                    }

                    // 푸시 후 이미지 확인 (ouptut 콘솔)
                    sh "aws ecr describe-images --repository-name jenkins-images --region ${AWS_REGION}"
                }
            }
        }
    }
}
