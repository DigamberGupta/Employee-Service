FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG employee_service
ADD /target/*.jar employee_service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/employee_service.jar"]