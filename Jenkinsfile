pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat '''
                    if not exist out mkdir out

                    rem Compile main sources
                    dir /s /b src\\main\\java\\*.java > sources.txt

                    javac -encoding UTF-8 ^
                      -cp lib\\jSerialComm-2.11.2.jar ^
                      -d out @sources.txt

                    jar cfe eeprom-app.jar eeprom.EepromGUI -C out .
                '''
            }
        }

        stage('Test') {
            steps {
                bat '''
                    rem Compile test sources
                    dir /s /b src\\test\\java\\*.java > test-sources.txt

                    javac -encoding UTF-8 ^
                      -cp "lib\\jSerialComm-2.11.2.jar;lib\\junit-4.13.2.jar;lib\\hamcrest-core-1.3.jar;out" ^
                      -d out @test-sources.txt

                    rem Run JUnit tests
                    java -cp "lib\\jSerialComm-2.11.2.jar;lib\\junit-4.13.2.jar;lib\\hamcrest-core-1.3.jar;out" ^
                      org.junit.runner.JUnitCore eeprom.NvmManagerTest
                '''
            }
        }

        stage('Build Docker Image') {
            when {
                expression { currentBuild.currentResult == 'SUCCESS' }
            }
            steps {
                echo 'Building Docker Image...'
                // docker build logic here
            }
        }

        stage('Run in Docker') {
            when {
                expression { currentBuild.currentResult == 'SUCCESS' }
            }
            steps {
                echo 'Running inside Docker...'
                // docker run logic here
            }
        }
    }
}
