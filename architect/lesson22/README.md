## Description
Для шардирования продуктов запущены две базы монго, с одинаковыми параметрами. Для выбора шарда используется hash функция 


## Installation
`$ minikube addons enable ingress`  
`$ helm repo add bitnami https://charts.bitnami.com/bitnami`  
`$ helm install mongo01 -f ./kubernetes/mongodb.yml bitnami/mongodb` 
`$ helm install mongo02 -f ./kubernetes/mongodb.yml bitnami/mongodb` 
`$ helm install lesson22 ./kubernetes/lesson22-chart`  

## Test
Postman коллекция для тестирования - https://www.getpostman.com/collections/620217601d37c366ec57