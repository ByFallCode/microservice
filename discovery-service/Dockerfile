#FROM eclipse-temurin:21-jdk-jammy
#WORKDIR /app
#COPY target/billing-server-0.0.1-SNAPSHOT.jar /app

# Étape 1: Construire l'application avec Maven
FROM maven:3.9.9-eclipse-temurin-21-jammy AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Étape 2: Créer une image plus légère pour exécuter l'application
FROM openjdk:21-jdk-slim

# Installer curl (nécessaire pour healthcheck)
RUN apt-get update && apt-get install -y curl && apt-get clean


WORKDIR /app

# Copier le fichier JAR depuis l'étape de build
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
