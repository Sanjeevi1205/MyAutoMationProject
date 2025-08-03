# Base image with Maven and Java 17 (Temurin)
FROM maven:3.8.7-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy Maven project files (pom.xml and all source code)
COPY . .

# Pre-download dependencies to cache Maven layer
RUN mvn dependency:go-offline

# Run tests if needed when container starts (optional override)
CMD ["mvn", "clean", "test", "-Dtest=runner.RunnerClass2"]
