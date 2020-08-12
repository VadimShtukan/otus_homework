# Installation

#### Init
`$ minikube start --cpus=2 --memory=6000m --driver='kvm2'`  
`$ minikube addons enable ingress`  
`$ helm repo add bitnami https://charts.bitnami.com/bitnami`
 
#### MongoDb
`$ helm install mongo-kye -f ./kubernetes/mongodb-key.yml bitnami/mongodb`  
`$ helm install mongo-document -f ./kubernetes/mongodb-document.yml bitnami/mongodb`  

#### Redis
`$ helm install redis -f ./kubernetes/redis.yml bitnami/redis` 

#### Kafka
`$ helm install -f ./kubernetes/kafka.yml kafka bitnami/kafka`

#### APPs Install
`$ helm install api-getaway-web-chart ./kubernetes/api-getaway-web-chart`  

---`$ kubectl port-forward service/fusionauth-helm 9012:9012 --address 0.0.0.0`
`$ kubectl port-forward service/mongo-kye-mongodb 27017:27017 --address 0.0.0.0`
`$ kubectl port-forward service/mongo-document-mongodb 27018:27017 --address 0.0.0.0`
`$ kubectl port-forward service/redis-master 6379:6379 --address 0.0.0.0`
`$ kubectl port-forward service/kafka 9092:9092 --address 0.0.0.0`

kubectl port-forward service/api-getaway-web-chart 8001:80 --address 0.0.0.0

#### Docker
sudo service docker start

docker login --username=vadimshtukan
docker build -t vadimshtukan/key-chart:0.0.1 .
docker push vadimshtukan/-chart:0.0.1