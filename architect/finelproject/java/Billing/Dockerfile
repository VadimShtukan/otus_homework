FROM openjdk:14

ADD ./target/Billing-0.0.1.jar /usr/local/svm/Billing-0.0.1.jar

ENTRYPOINT java -XX:+UseContainerSupport -XX:MaxRAMPercentage=50 -jar /usr/local/svm/Billing-0.0.1.jar
EXPOSE 8003