FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/memed-core-latest.jar /app/

# Expose the port on which the Spring Boot application is running (adjust as needed)
EXPOSE 8080

# Set the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "memed-core-latest.jar"]
