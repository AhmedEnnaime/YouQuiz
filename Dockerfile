FROM openjdk:17

WORKDIR /app/backend

COPY target/YouQuiz-0.0.1-SNAPSHOT.jar YouQuiz.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","/app/backend/YouQuiz.jar"]