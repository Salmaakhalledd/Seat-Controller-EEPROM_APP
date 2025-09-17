FROM openjdk:17-slim

WORKDIR /app

# انسخ الكود كله
COPY . .

# Compile Java sources
RUN javac -cp lib/jSerialComm-2.11.2.jar -d out $(find src -name "*.java")

# Package into a runnable jar
RUN jar cfe eeprom-app.jar eeprom.EepromGUI -C out .

CMD ["java", "-cp", "eeprom-app.jar:lib/jSerialComm-2.11.2.jar", "eeprom.EepromGUI"]
