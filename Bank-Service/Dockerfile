FROM openjdk:11
ADD ./target/Bank-Service-0.0.1-SNAPSHOT.jar /usr/src/Bank-Service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "Bank-Service-0.0.1-SNAPSHOT.jar"]