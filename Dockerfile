# ---------- Build Stage ----------
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/secure-ecommerce-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]