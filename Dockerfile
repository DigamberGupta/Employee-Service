FROM openjdk:8-jdk-alpine
MAINTAINER digambergupta
VOLUME /tmp
ADD /target/*.jar employee_service.jar

#Java execution
CMD ["java", "-Xmx200m", "-jar", "/employee_service.jar"]
EXPOSE 8080