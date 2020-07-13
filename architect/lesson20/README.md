## Description
Идемпотентность была реализована для исключения повторного добавления товар в корзину ИМ.  
Для того, что бы запрос корректно работал необходимо в заголовке указать уникальный X-RequestId в формате UUID. Если UUID повторится(будет неуникальным), то сервер вернет корзину которая была создана или в последний раз изменена с этим X-RequestId.  

## Installation
`$ minikube addons enable ingress`  
`$ helm repo add bitnami https://charts.bitnami.com/bitnami`  
`$ helm install mongo -f ./kubernetes/mongodb.yml bitnami/mongodb` 
`$ helm install lesson20 ./kubernetes/lesson20-chart`  

## Test
Postman коллекция для тестирования - https://www.getpostman.com/collections/8374ce6a2a2d91ed8c7b