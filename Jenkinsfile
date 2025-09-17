pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat '''
                dir /s /b src\\*.java > sources.txt
                javac -cp lib\\jSerialComm-2.11.2.jar -d out @sources.txt
                jar cfe eeprom-app.jar eeprom.EepromGUI -C out .
                '''
            }
        }

        stage('Test') {
            steps {
                bat '''
                javac -cp "lib\\jSerialComm-2.11.2.jar;lib\\junit-4.13.2.jar;lib\\hamcrest-core-1.3.jar;out" -d out test\\*.java
                java -cp "lib\\jSerialComm-2.11.2.jar;lib\\junit-4.13.2.jar;lib\\hamcrest-core-1.3.jar;out" org.junit.runner.JUnitCore eeprom.NvmManagerTest
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t eeprom-app .'
            }
        }

        stage('Run in Docker') {
            steps {
                bat 'docker run --rm eeprom-app'
            }
        }
    }
}
