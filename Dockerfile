FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR  /app
COPY . .
RUN mvn install

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/YouQuiz-0.0.1-SNAPSHOT.jar YouQuiz.jar
EXPOSE 8082
ENTRYPOINT [ "java", "-jar",  "/app/YouQuiz.jar" ]