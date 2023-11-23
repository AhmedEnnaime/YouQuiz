pipeline {
    agent {
        docker {
            image 'maven:3.9.5-amazoncorretto-17-al2023'
            args '-v /root/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
    }
    stages {
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "mvn -f ./YouQuiz/pom.xml sonar:sonar -Dsonar.login=sqa_311ac7e6d170c86f40dcd42cab7dd5afd9168d25"
                }
            }
        }
        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    script {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }
        stage('Build') {
            steps {
                echo "Building.."
                script {
                    sh '''
                    mvn -f ./YouQuiz/pom.xml -B -DskipTests clean package
                    '''
                }
            }
        }
        stage('Test') {
            steps {
                echo "Testing.."
                script {
                    sh '''
                    mvn -f ./YouQuiz/pom.xml test
                    '''
                }
            }
        }
        stage('Login') {
            steps {
                echo "Login..."
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Deliver') {
            steps {
                echo "Deliver..."
                sh 'docker push ahmedennaime/youquiz:1.0'
            }
        }
    }
}
