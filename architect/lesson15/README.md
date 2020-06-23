## Description
В качестве кэша данных был выбран централизованный кэш на основе REDIS.  
При первом запросе продукта (по ID продукта) данные берутся из основной БД и записывается в кэш. В последствии, при запросе продукта с тем же ID, данные берутся из REDIS кэша.  
Данные в кэше хранятся бессрочно, но при обновлении данных, по ID, кэш сбрасывается.


## Installation
`$ minikube addons enable ingress`  
`$ helm dependency update ./kubernetis/lesson15-chart`  
`$ helm repo add bitnami https://charts.bitnami.com/bitnami`  
`$ helm install redis -f ./kubernetis/redis.yml bitnami/redis`  
`$ helm install lesson15 ./kubernetis/lesson15-chart`

## Test
Коллекция для теста - https://www.getpostman.com/collections/1027b80db0904e757af1  
В коде была введена искуственная задержка - 1с при запросе из БД без использования кэша.  
Отключить кэш можно указав в параметре cache_r.type: none в values.yaml