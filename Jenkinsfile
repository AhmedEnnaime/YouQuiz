pipeline {
    agent {
        docker {
            image 'maven:3.9.5-amazoncorretto-17-al2023'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                echo "Building.."
                script {
                    sh '''
                    mvn -B -DskipTests clean package
                    '''
                }
            }
        }
        stage('Test') {
            steps {
                echo "Testing.."
                script {
                    sh '''
                    mvn test
                    '''
                }
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                echo 'Deliver....'
                script {
                    sh '''
                    echo "doing delivery stuff.."
                    '''
                }
            }
        }
    }
}
