FROM openjdk:11-jre

EXPOSE 8000

ARG API_PATH=/usr/share/youdo-api

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
ADD target/lib /usr/share/youdo-api/lib
# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/youdo-api/youdo-api.jar

ENTRYPOINT ["java", "-jar", "/usr/share/youdo-api/youdo-api.jar"]