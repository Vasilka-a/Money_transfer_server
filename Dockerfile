FROM openjdk:21-jdk
EXPOSE 5500
COPY build/libs/Money_transfer_server-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","app.jar"]