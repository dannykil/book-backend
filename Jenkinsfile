pipeline {
    agent any 	// 사용 가능한 에이전트에서 이 파이프라인 또는 해당 단계를 실행
    
    // git 프로젝트 credentials 는 access token 으로 사용
    stages {
         stage('Prepare') {
             steps {
                 git branch: 'master',
                     // url: 'https://{git access token}@github.com/mooh2jj/board_vue_backend.git'
                     url: 'https://github.com/dannykil/book-backend.git'
             }

             post {
                 success {
                     sh 'echo "Successfully Cloned Repository"'
                 }
                 failure {
                     sh 'echo "Fail Cloned Repository"'
                 }
             }
         }

        stage('Build') {
            steps {
            	// gralew이 있어야됨. git clone해서 project를 가져옴.
                sh 'chmod +x gradlew'
                sh  './gradlew --warning-mode=all --stacktrace clean build -x test'


                sh 'ls -al ./build'
            }
            post {
                success {
                    echo 'gradle build success'
                }

                failure {
                    echo 'gradle build failed'
                }
            }
        }

        stage('Test') {
            steps {
                echo  '테스트 단계와 관련된 몇 가지 단계를 수행합니다.'
            }
        }

        stage('Deploy') {
            steps {
                echo  'jar파일 이동 및 실행'
                sh 'cd book-backend-container-local'
                sh 'ls -al'
            }
        }

        // stage('Prune Docker data') {
        //     steps {
        //         sh 'echo "Prune Docker data"'
        //         sh 'docker system prune -a --volumes -f'
        //     }

        //     post {
        //         success {
        //             sh 'echo "Prune Docker data Success"'
        //         }
        //         failure {
        //             sh 'echo "Prune Docker data Fail"'
        //         }
        //     }
        // }

        // stage('Docker Build'){
        //     steps{
        //         sh 'echo " Image Bulid Start"'
        //         sh 'docker build . -t mooh2jj/board_vue_backend'
        //     }
        //     post {
        //         success {
        //             sh 'echo "Bulid Docker Image Success"'
        //         }

        //         failure {
        //             sh 'echo "Bulid Docker Image Fail"'
        //         }
        //     }
        // }

        // stage('Docker Push') {
        //     steps {
        //         withCredentials([string(credentialsId: 'dockerHubPwd', variable: 'dockerHubPwd')]) {
        //             sh "docker login -u mooh2jj -p ${dockerHubPwd}"
        //         }
        //         sh 'docker push mooh2jj/board_vue_backend'
        //     }

        //     post {
        //         success {
        //             echo 'Docker Push success'
        //         }

        //         failure {
        //             echo 'Docker Push failed'
        //         }
        //     }
        // }
        
        // stage('Docker Deploy'){
        //     steps{
        //         sh 'docker-compose up -d --build'
        //         sh 'docker-compose ps'
        //     }
        //     post {
        //         success {
        //             echo 'docker-compose success'
        //         }

        //         failure {
        //             echo 'docker-compose failed'
        //         }
        //     }
        // }
    }
}