FROM eclipse-temurin:25-jre AS runtime
WORKDIR /app

# Expect the application JAR to be built locally (run `./mvnw clean package`)
# and present in `target/`. Docker will just package the runtime image.
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
