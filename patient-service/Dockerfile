
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy file pom.xml và cài dependency (cache hiệu quả)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy mã nguồn và build ứng dụng
COPY src ./src
RUN mvn clean package -DskipTests

# --- Giai đoạn runtime ---
FROM openjdk:21-jdk AS runner

WORKDIR /app

# Copy file jar đã build từ giai đoạn trước
COPY --from=builder ./app/target/patient-service-0.0.1-SNAPSHOT.jar ./app.jar


EXPOSE 4000


ENTRYPOINT ["java", "-jar", "app.jar"]  
