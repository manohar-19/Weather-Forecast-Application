FROM openjdk:21
ADD ./docker-weather-app.jar docker-weather-app.jar
ENTRYPOINT ["java","-jar","docker-weather-app.jar"]