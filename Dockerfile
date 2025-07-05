# Use OpenJDK 17 base image (compatible with most Spring Boot apps)
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the compiled JAR (make sure to build it first with mvn or gradle)
COPY target/*.jar app.jar

# Expose port if needed
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
