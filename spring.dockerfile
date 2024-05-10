FROM openjdk:17
LABEL authors="KrasnovM"
COPY . /app
WORKDIR /app
ENTRYPOINT ["java", "-jar", "target/interview-task-0.0.1-SNAPSHOT.jar"]