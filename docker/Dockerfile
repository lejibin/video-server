FROM openjdk:8-jre
RUN mkdir /app
COPY video-server-0.0.1-SNAPSHOT.jar /app/
ENV SPRING_PROFILES_ACTIVE prod
CMD java -jar /app/video-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE}
EXPOSE 8088