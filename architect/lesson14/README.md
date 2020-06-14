##Installing 

https://www.learningjournal.guru/article/kafka/installing-multi-node-kafka-cluster/
https://kafka.apache.org/quickstart


`$ sudo yum -y install java-11-openjdk`  
`$ wget https://apache.paket.ua/kafka/2.5.0/kafka_2.12-2.5.0.tgz`  
`$ tar -xzf kafka_2.12-2.5.0.tgz`  
`$ nano ~/.bash_profile`  
add line PATH=$PATH:$HOME/.local/bin:$HOME/bin:$HOME/kafka_2.12-2.5.0  

`$ nano ~/kafka_2.12-2.5.0/config/zookeeper.properties`  
change dataDir=  

##Start
start zookeeper  
`$ ~/kafka_2.12-2.5.0/bin/zookeeper-server-start.sh ~/kafka_2.12-2.5.0/config/zookeeper.properties`

start kafka broker
`$ ~/kafka_2.12-2.5.0/bin/kafka-server-start.sh ~/kafka_2.12-2.5.0/config/server.properties`

##Test
test zookeeper  
`$ ~/kafka_2.12-2.5.0/bin/zookeeper-shell.sh localhost  ls /brokers/ids`

test kafka  
`$ ~/kafka_2.12-2.5.0/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test01`
`$ ~/kafka_2.12-2.5.0/bin/kafka-topics.sh --list --bootstrap-server localhost:9092`

test send and read message
`$ ~/kafka_2.12-2.5.0/bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test01`

`$ ~/kafka_2.12-2.5.0/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test01 --from-beginning`


##Security
TODO!!!

##Zookeeper cluster
TODO!!!

##Kafka cluster
TODO!!!!

##Monitoring Kafka
TODO !!!!

##Monitoring Zookeeper
TODO!!!




##Code useg
###C++ Create native DLL/LIB
1. https://github.com/edenhill/librdkafka/blob/master/README.win32
OpenSSL-win32 must be installed in C:\OpenSSL-win32 and C:\OpenSSL-Win64  
2. Install Visual Studio Community.  
3. Download https://github.com/edenhill/librdkafka
4. Open win32/librdkafka.sln in VS

packages\librdkafka.redist.1.4.0\build\native\librdkafka.redist.targets
<AdditionalDependencies>$(MSBuildThisFileDirectory)lib\win\x86\win-x86-Release\v120\librdkafkacpp.lib;%(AdditionalDependencies)</AdditionalDependencies>
      