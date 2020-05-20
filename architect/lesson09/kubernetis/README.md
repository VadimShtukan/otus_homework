
/$ minikube addons enable ingress
$ helm dependency update .\lesson-09-chart
$ helm install lesson-09 .\lesson-09-chart
$ helm repo add fusionauth https://fusionauth.github.io/charts
$ helm install fusionauth-helm -f .\fusionauth.yml fusionauth/fusionauth