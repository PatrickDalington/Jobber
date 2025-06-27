# Use a base image with JDK
FROM openjdk:17-jdk-slim

# Add metadata (optional)
LABEL maintainer="Olumba Chidubem Patrick"
LABEL app="Jobber"

# Set working directory inside container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/jobber-0.0.1-SNAPSHOT.jar app.jar

# Expose port (match your Spring Boot server.port)
EXPOSE 8080

# Run the JAR when container starts
ENTRYPOINT ["java", "-jar", "app.jar"]