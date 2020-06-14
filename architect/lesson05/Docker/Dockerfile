#FROM ubuntu:18.04
FROM openjdk:8
#RUN apt-get -y update && apt-get -y upgrade \
#	&& apt-get install -y openjdk-8-jre
ADD lesson5-0.0.1.jar /usr/local/svm/lesson5-0.0.1.jar

#CMD ["java", "-jar", "-DDATABASE_PASSWORD=123456", "-DDATABASE_USER=root", "-DDATABASE_URL=jdbc:mysql://localhost:3306/otus_vadim_shtukan_db", "/usr/local/svm/lesson5-0.0.1.jar"]
ENTRYPOINT java -jar -DDATABASE_PASSWORD=${DATABASE_PASSWORD} -DDATABASE_USER=${DATABASE_USER} -DDATABASE_URL=${DATABASE_URL} /usr/local/svm/lesson5-0.0.1.jar
EXPOSE 8000
