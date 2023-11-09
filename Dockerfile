# Use the official Maven image as a parent image
FROM maven:3.8.4-openjdk-17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the POM file to the container
COPY pom.xml .

# Download the project dependencies and create a cached layer
RUN mvn dependency:go-offline

# Copy the project source code to the container
COPY src src

# Build the application
RUN mvn package

# Use the AdoptOpenJDK 17 JRE HotSpot image as the final base image
FROM adoptopenjdk:17-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage to the container
COPY --from=builder /app/target/backend-1.jar app.jar

# Expose the port your application will run on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]

