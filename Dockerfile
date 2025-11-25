FROM eclipse-temurin:24-jre

WORKDIR /app
USER nobody
COPY target/project-1.0.jar app.jar
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]
