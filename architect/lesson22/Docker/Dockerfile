#FROM ubuntu:18.04
FROM openjdk:14
ADD lesson22-0.0.1.jar /usr/local/svm/lesson22-0.0.1.jar

ENTRYPOINT java -XX:+UseContainerSupport -XX:MaxRAMPercentage=50 -jar /usr/local/svm/lesson22-0.0.1.jar
EXPOSE 9090
