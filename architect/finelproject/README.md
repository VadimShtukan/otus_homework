# Installation

#### Init
----`$ minikube addons enable ingress`  
----`$ helm dependency update .\lesson-09-chart`  
`$ helm repo add bitnami https://charts.bitnami.com/bitnami`
----`$ helm repo add fusionauth https://fusionauth.github.io/charts`

#### Fusion App
---`$ helm install fusionauth-db -f .\fusionauth-db.yml bitnami/postgresql`  
----`$ helm install fusionauth-helm -f .\fusionauth.yml fusionauth/fusionauth`  

#### MongoDb
`$ helm install mongo-kye -f ./kubernetes/mongodb-key.yml bitnami/mongodb`  
`$ helm install mongo-document -f ./kubernetes/mongodb-document.yml bitnami/mongodb`  

#### Redis
`$ helm install redis -f ./kubernetes/redis.yml bitnami/redis` 

#### Kafka
`$ helm install -f ./kubernetes/kafka.yml kafka bitnami/kafka`


---`$ kubectl port-forward service/fusionauth-helm 9012:9012 --address 0.0.0.0`
`$ kubectl port-forward service/mongo-kye-mongodb 27017:27017 --address 0.0.0.0`
`$ kubectl port-forward service/mongo-document-mongodb 27018:27017 --address 0.0.0.0`
`$ kubectl port-forward service/redis-master 6379:6379 --address 0.0.0.0`
`$ kubectl port-forward service/kafka 9092:9092 --address 0.0.0.0`