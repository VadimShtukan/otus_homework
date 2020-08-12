FROM openjdk:14

ADD ./target/key-0.0.1.jar /usr/local/svm/key-0.0.1.jar

ENTRYPOINT java -XX:+UseContainerSupport -XX:MaxRAMPercentage=50 -jar /usr/local/svm/key-0.0.1.jar
EXPOSE 8001
