# Installation
`$ minikube addons enable ingress`  
`$ helm repo add bitnami https://charts.bitnami.com/bitnami`  
#### MongoDb
`$ helm install mongo -f ./kubernetes/mongodb.yml bitnami/mongodb`  




`$ kubectl port-forward service/mongo-mongodb 27017:27017`