# Fase de Construcción
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
COPY . .
# Usamos el wrapper de maven (se asume que existe en el repo)
RUN ./mvnw clean package -DskipTests

# Fase de Ejecución
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
