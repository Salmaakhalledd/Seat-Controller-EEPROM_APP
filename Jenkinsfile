pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'javac -cp lib/jSerialComm-2.11.2.jar -d out $(find src -name "*.java")'
                sh 'jar cfe eeprom-app.jar eeprom.EepromGUI -C out .'
            }
        }

        stage('Test') {
            steps {
                sh 'javac -cp "lib/jSerialComm-2.11.2.jar:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:out" -d out test/*.java'
                sh 'java -cp "lib/jSerialComm-2.11.2.jar:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:out" org.junit.runner.JUnitCore eeprom.NvmManagerTest'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t eeprom-app .'
            }
        }

        stage('Run in Docker') {
            steps {
                sh 'docker run --rm eeprom-app'
            }
        }
    }
}
