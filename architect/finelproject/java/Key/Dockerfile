FROM openjdk:14

ADD ./target/Key-0.0.2.jar /usr/local/svm/Key-0.0.2.jar

ENTRYPOINT java -XX:+UseContainerSupport -XX:MaxRAMPercentage=50 -jar /usr/local/svm/Key-0.0.2.jar
EXPOSE 8001
