# melbourne-weather-service

# Build the JAR
./mvnw clean package

# Run the app
java -jar target/melbourne-weather-service-0.0.1-SNAPSHOT.jar

# Or run with Docker
docker build -t melbourne-weather-service .
docker run -p 8080:8080 melbourne-weather-service


# Swagger UI
Local Link : http://localhost:8181/swagger-ui/index.html
![image](https://github.com/user-attachments/assets/cf903d7c-ae6d-4db5-a6df-9fc1abc3b6a5)
![image](https://github.com/user-attachments/assets/e18c2da3-60e6-4b25-975b-e494eef89df6)
![image](https://github.com/user-attachments/assets/64fe7cde-f367-47b6-bcbe-f116a550fc30)
![image](https://github.com/user-attachments/assets/720b100d-07df-4449-9604-8782391cc2a2)
