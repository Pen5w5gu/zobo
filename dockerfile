# Sử dụng OpenJDK để build ứng dụng
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Sao chép file pom.xml và tải dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Sao chép toàn bộ mã nguồn và build
COPY . .
RUN mvn clean package -DskipTests

# Sử dụng OpenJDK để chạy ứng dụng
FROM openjdk:17-jdk-slim
WORKDIR /app

# Sao chép file JAR từ bước build
COPY --from=build /app/target/*.jar app.jar

# Expose cổng chạy ứng dụng
EXPOSE 8080

# Chạy ứng dụng Java
CMD ["java", "-jar", "app.jar"]
