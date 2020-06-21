`$ minikube addons enable ingress`  
`$ helm dependency update .\lesson15-chart`
`$ helm repo add bitnami https://charts.bitnami.com/bitnami`
`$ helm install redis -f ./redis.yml bitnami/redis`  
`$ helm install lesson15 .\lesson15-chart`


    kubectl port-forward --namespace default svc/redis-master 6379:6379
    kubectl port-forward service/lesson15-mysql 3306:3306