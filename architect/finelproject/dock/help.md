---------
# Installation

---`$ kubectl port-forward service/fusionauth-helm 9012:9012 --address 0.0.0.0`
`$ kubectl port-forward service/mongo-kye-mongodb 27017:27017 --address 0.0.0.0`
`$ kubectl port-forward service/mongo-document-mongodb 27018:27017 --address 0.0.0.0`
`$ kubectl port-forward service/redis-master 6379:6379 --address 0.0.0.0`
`$ kubectl port-forward service/kafka 9092:9092 --address 0.0.0.0`

kubectl port-forward service/api-getaway-web-chart 8000:80 --address 0.0.0.0
kubectl port-forward service/prom-grafana 9000:80  --address 0.0.0.0

kubectl port-forward service/prom-prometheus-operator-prometheus 9091:9090  --address 0.0.0.0


#### Docker
sudo service docker start

docker login --username=vadimshtukan
docker build -t vadimshtukan/api-getaway-web-chart:0.0.4 .
docker push vadimshtukan/api-getaway-web-chart:0.0.4

docker login --username=vadimshtukan
docker build -t vadimshtukan/key-chart:0.0.2 .
docker push vadimshtukan/key-chart:0.0.2

docker login --username=vadimshtukan
docker build -t vadimshtukan/document-chart:0.0.4 .
docker push vadimshtukan/document-chart:0.0.4

docker login --username=vadimshtukan
docker build -t vadimshtukan/billing-chart:0.0.1 .
docker push vadimshtukan/billing-chart:0.0.1


docker login --username=vadimshtukan
docker build -t vadimshtukan/notification-chart:0.0.1 .
docker push vadimshtukan/notification-chart:0.0.1

docker login --username=vadimshtukan
docker build -t vadimshtukan/ettn-gov-sender-chart:0.0.2 .
docker push vadimshtukan/ettn-gov-sender-chart:0.0.2

PS C:\Users\vadim> kubectl port-forward service/api-getaway-web-chart 8000:80
error: error upgrading connection: unable to upgrade connection: Authorization error (user=kube-apiserver-kubelet-client, verb=create, resource=nodes, subresource=proxy)