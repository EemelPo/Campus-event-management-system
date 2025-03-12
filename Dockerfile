FROM maven:latest
WORKDIR /app
COPY . /app/
RUN mvn package
ENTRYPOINT ["java", "-jar", "target/mainapp.jar"]