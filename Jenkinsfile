pipeline {
    agent any
    
    tools {
        maven "Maven-3.9.9"
    }
    
    stages {
        stage('project build') {
            steps {
            	// gralew이 있어야됨. git clone해서 project를 가져옴.
                // sh 'chmod +x gradlew'
                // sh  './gradlew --warning-mode=all --stacktrace clean build -x test'
                echo '########## project build ##########'
                sh 'mvn -Dmaven.test.failure.ignore=true -N -f pom.xml clean package'
            }
        }

        stage('image build') {
            steps {
                echo '########## image build ##########'
                sh 'sudo docker build -t dannielkil/book-backend .'
            }
        }

        stage('image push') {
            steps {
                echo '########## image push ##########'
                sh 'sudo docker push dannielkil/book-backend'
            }
        }
    }
}