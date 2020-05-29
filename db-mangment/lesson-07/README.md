## Installing

`$ helm repo add bitnami https://charts.bitnami.com/bitnami`
`$ helm repo update`
`$ helm install db-lesson-07 -f ./postrgress.yaml stable/postgresql`
`kubectl port-forward service/db-lesson-07-postgresql 5432:5432`

## Screen

![Image description](https://raw.githubusercontent.com/VadimShtukan/otus_homework/master/db-mangment/lesson-07/screen/1.png)  
---
![Image description](https://raw.githubusercontent.com/VadimShtukan/otus_homework/master/db-mangment/lesson-07/screen/2.png)  
