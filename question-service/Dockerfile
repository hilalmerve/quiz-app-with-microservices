#FROM eclipse-temurin:17-jdk
#
#WORKDIR /app
#
#COPY . .
#
#RUN chmod +x mvnw
#
#CMD ["./mvnw", "spring-boot:run"]
#
#ENTRYPOINT ["java", "-jar", "app.jar"]

FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]