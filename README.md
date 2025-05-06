# melbourne-weather-service

# Build the JAR
./mvnw clean package

# Run the app
java -jar target/melbourne-weather-service-0.0.1-SNAPSHOT.jar

# Or run with Docker
docker build -t melbourne-weather-service .
docker run -p 8080:8080 melbourne-weather-service
