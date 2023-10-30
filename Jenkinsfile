pipeline {
    agent {
        docker {
            image 'maven:3.9.5-amazoncorretto-17-al2023'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "mvn sonar:sonar -Dsonar.login=sqa_a93eda929ec3ab8d8c6a9b4cc50329ed18021076"
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