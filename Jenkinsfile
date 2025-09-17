pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat '''
                    if not exist out mkdir out
                    dir /s /b Seat-Controller-EEPROM_APP\\src\\*.java > sources.txt
                    javac -encoding UTF-8 -cp Seat-Controller-EEPROM_APP\\lib\\jSerialComm-2.11.2.jar -d out @sources.txt
                    jar cfe eeprom-app.jar eeprom.EepromGUI -C out .
                '''
            }
        }

        stage('Test') {
            steps {
                bat '''
                    dir /s /b Seat-Controller-EEPROM_APP\\eeprom\\*.java > test-sources.txt
                    javac -encoding UTF-8 -cp "Seat-Controller-EEPROM_APP\\lib\\jSerialComm-2.11.2.jar;Seat-Controller-EEPROM_APP\\lib\\junit-4.13.2.jar;Seat-Controller-EEPROM_APP\\lib\\hamcrest-core-1.3.jar;out" -d out @test-sources.txt
                    java -cp "Seat-Controller-EEPROM_APP\\lib\\jSerialComm-2.11.2.jar;Seat-Controller-EEPROM_APP\\lib\\junit-4.13.2.jar;Seat-Controller-EEPROM_APP\\lib\\hamcrest-core-1.3.jar;out" org.junit.runner.JUnitCore eeprom.NvmManagerTest
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
