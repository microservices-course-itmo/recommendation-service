FROM adoptopenjdk/openjdk11:ubi
ADD target/recommendation-service.jar /app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=docker","/app.jar"]
