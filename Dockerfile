ROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} maven-wrapper.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/maven-wrapper.jar"]