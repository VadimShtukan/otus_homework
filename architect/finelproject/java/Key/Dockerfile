FROM openjdk:14

ADD ./target/Key-10.0.1.jar /usr/local/svm/Key-0.0.1.jar

ENTRYPOINT java -XX:+UseContainerSupport -XX:MaxRAMPercentage=50 -jar /usr/local/svm/Key-0.0.1.jar
EXPOSE 8001
