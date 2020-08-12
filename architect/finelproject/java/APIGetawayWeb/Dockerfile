FROM openjdk:14

ADD ./target/APIGetawayWeb-0.0.2.jar /usr/local/svm/APIGetawayWeb-0.0.2.jar

ENTRYPOINT java -XX:+UseContainerSupport -XX:MaxRAMPercentage=50 -jar /usr/local/svm/APIGetawayWeb-0.0.2.jar
EXPOSE 8000
