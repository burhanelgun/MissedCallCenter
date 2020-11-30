FROM openjdk:15
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} missedcallcenterdocker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/missedcallcenterdocker.jar"]