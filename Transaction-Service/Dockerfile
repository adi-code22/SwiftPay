FROM openjdk:11
ADD ./target/Transaction-Service-0.0.1-SNAPSHOT.jar /usr/src/Transaction-Service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "Transaction-Service-0.0.1-SNAPSHOT.jar"]