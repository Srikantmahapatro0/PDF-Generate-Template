FROM openjdk:17-jdk-slim
WORKDIR /app
COPY pdfgeneration/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
