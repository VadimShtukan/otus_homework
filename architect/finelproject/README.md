# О проекте Система провайдера электронных транспортных накладных.  
#### --TODO

# Пользовательская история.    
###  Участник системы:  
- Администратор - сотрудник договорного отдела. Его задача создавать/редактировать информацию о пользователях системы, и иметь возможность их блокировать/разблокировать.      
- Отправитель - пользователь системы, представитель компании отправителя груза. Он создает еТТН, имеет возможность редактировать и отозвать ее на определенных стадиях доставки груза.  
- Перевозчик - пользователь системы, представитель компании перевозчика груза. Должен иметь возможность вносить дополнения к еТТН (номер автомобиля, ФИО водителя).  
- Получатель - пользователь системы, представитель компании получателя груза. Должен иметь возможность вносить дополнения к еТТН (информацию о получении груза, претензии к перевозке или получаемому грузу).  
- ЦБД - Государственный орган, которое получает информацию об создании и изменении еТТН.  

### Регистрация пользователей   
Доступ к системе предоставляется только авторизованным пользователям, у которых есть договор на предоставление услуг "Провайдеру"  
Администратор системы входит в систему при помощи ЭЦП, переходит в пункт меню "Создать нового пользователя" и вводи следующие данные пользователя:  
- Тип пользователя (Отправитель, получатель, перевозчик)  
- ФИО пользователя.  
- Номер телефона.  
- e-mail.   
- Компания из списка (если ее нет, то он создает ее).  
- Серийный номер ключа пользователя, под которым он будет входить в систему.  
Кнопка создать пользователя, по нажатию которой должен создаться пользователь, пользователь должен будет проинформирован по электронной почет, что для него создана учетная запись с инструкцией как войти в систему при помощи своей ЭЦП.  
 
### Создание еТТН
Отправитель заходит в систему при помощи своей ЭЦП, переходи в пункт меню "Создать еТТН".  
Вводит все необходимые данные форме (номенклатура товара, количество, вес, цена, перевозчик груза, получатель груза, и т.д.) и кнопка "Создать еТТН"   
При нажатии кнопки "Создать еТТН" система должна сформировать XML с веденными данными, подписанный ЭЦП. Этот XML должен быть отправлен на сервер для проверки введенных данных, сохранении, и регистрации eTTN в ЦБД. Если в процессе произошла ошибка, об это необходимо сообщить пользователю.     
Необходимо оповести перевозчика и получателя (по электронной почте и СМС) о том, что для них есть документ, с инструкциями что они должны сделать.  
Отправитель имеет право вносить изменения в еТТН пока Получатель не подтвердит получение груза по еТТН.  
После успешной регистрации в ЦБД для компании Отправителя добавляется кредиторская задолженность на определенную сумму. Администратор должен иметь возможность просмотреть эту задолженность.     
![Создание еТТН](https://raw.githubusercontent.com/VadimShtukan/otus_homework/master/architect/finelproject/dock/img/s1.png)  

  
### Добавление информации о перевозчике
Перевозчик, до начала перевозки груза, должен внести свою информацию (номер авто и ФИО водителя). Для этого он входит в систему под своим ЭЦП, переходит в пункт меню "Указать данные перевозки", находит нужный еТТН в списке, открывает его и вводит необходимую информацию.   
При нажатии на кнопку "Сохранить", введенная информация должна подписаться ЭЦП Перевозчика и отправиться на сервер. Сервер должен проверить валидность введенной информации, обновить еТТН и отправить его в ЦБД.  
При возникновении ошибки необходимо оповестить пользователя.  
Перевозчик имеет право вносить изменения в еТТН пока Получатель не подтвердит получение груза по еТТН.  

### Добавление информации о получении  
Получатель, при получении, и проверки груза заходит в систему под своим ЭЦП и переходит в пункт меню "Получить груз по еТТН". Вводит необходимую информацию (ФИО принявшего груз, притензии грузу или к доставке) и нажимает кнопку "Сохранить".  
После нажатия кнопки "Сохранить" данные Получателя подписываются ЭЦП и отправляются на сервер, где они проверяются и отправляются в ЦБД.  
На этом этапе считается, что груз получен и вносится запрет изменение данных Отправителем и Перевозчиком.   
![Редактирование еТТН](https://raw.githubusercontent.com/VadimShtukan/otus_homework/master/architect/finelproject/dock/img/s2.png)  

### Взаимодействие с ЦБД  
Необходимо информировать ЦБД о создании и изменении каждого еТТН. Информация при изменении должна быть минимальной и не хранить в себе данные участников процессов.   
ЦБД может, с использованием своего JWT, запрашивать расширенную, текущую информацию о каждом еТТН.  

# Архитектурное решение  
### Общая схема архитектурного решения:  
![Архитектурное решение](https://raw.githubusercontent.com/VadimShtukan/otus_homework/master/architect/finelproject/dock/img/arch_schem.png)  

### Взаимодействие с фронтендом  
Бэкэнд с фронтэндом взаимодействует по средствам REST  
API: https://petstore.swagger.io/?url=https://github.com/VadimShtukan/otus_homework/blob/master/architect/finelproject/dock/swagger/api.yaml  

### Межсервисное взаимодействие  
Синхронное - REST API  
Синхронное - через Kafka. Для того, что бы всегда получать актуальную информацию при отправке сообщений в очередь отправляется только событие и идентификатор без доп. данных. Если сервису нужно получить больше данных, то он по идентификатору запрашивает данные с нужного сервису.  

### Аутентификация/авторизация пользователей.
Вместо логина и пароля используется ЭЦП - подписанный код компании. В качестве идентификатора пользователя в БД Key используется регистрационный ключ пользователя, который получается в результате успешного снятия подписи. Если аутентификация прошла успешно, Key сервис генерирует JWT с зашитой внутри информацией о пользователе со списком групп, в которую входит пользователь. По этому списку групп, дальше проходит авторизация пользователя в сервисах.   
Проверка JWT проходит на API Gateway, если JWT валидный, то  API Gateway записывает в header информацию о пользователе и перенаправляет запрос на нужный сервис.  
![Последоватьльность аутентификации](https://github.com/VadimShtukan/otus_homework/blob/master/architect/finelproject/dock/img/autoidentification.png)  

### Кэширование
В качестве кэша используется централизованный кэш на Redis. Кэшируются данные в сервисах Key(получение информации о пользователе) и Document(Получение информации о eTTN)  


#### -- TODO
4. Паттерн сага

# Развертывание
Для развертывания используется kubernetes с шаблонизированным helm   

#### Init
`$ minikube start --cpus=6 --memory=7000m --driver='kvm2'`  
`$ minikube start --cpus=6 --memory=7000m --driver='docker'`  
`$ minikube addons enable ingress`  
`$ helm repo add bitnami https://charts.bitnami.com/bitnami`
`$ helm repo add stable https://kubernetes-charts.storage.googleapis.com`
`$ helm repo update`
 
#### MongoDb
`$ helm install mongo-kye -f ./kubernetes/mongodb-key.yml bitnami/mongodb`  
`$ helm install mongo-document -f ./kubernetes/mongodb-document.yml bitnami/mongodb`  

#### Redis
`$ helm install redis -f ./kubernetes/redis.yml bitnami/redis` 

#### Kafka
`$ helm install -f ./kubernetes/kafka.yml kafka bitnami/kafka`

#### Monitoring
`$ helm install prom stable/prometheus-operator -f ./kubernetes/prometheus.yaml --atomic`
`$ kubectl apply -f ./kubernetes/dashboard.yaml`


#### APPs Install
`$ helm install api-getaway-web-chart ./kubernetes/api-getaway-web-chart`  
`$ kubectl port-forward service/api-getaway-web-chart 8000:80 --address 0.0.0.0`
`$ helm install key-chart ./kubernetes/key-chart`  
`$ helm install document-chart ./kubernetes/document-chart`  
`$ helm install notification-chart ./kubernetes/notification-chart`  
`$ helm install billing-chart ./kubernetes/billing-chart`  
`$ helm install ettn-gov-sender-chart ./kubernetes/ettn-gov-sender-chart`  


# Тесты в посмене
#### -- TODO дать ссылку

# Метрики
#### -- TODO

# Нагрузочное тестирование
#### -- TODO

# Ограничения по проекту
Данный проект является демонстрацией архитектурного решения, и только.  
В нем не реализована, в полной мере, бизнес логика проекта еТТН, и намеренно искажены процесс этапов перевозки груза, для того что бы не раскрывать детали данного проекта.  
Так же не реализованы обработки ошибок и не реализованы все механизмы по обеспечению безопасности.  
Данный проект нельзя использовать, без доработок, в реальном проекте!  


---------
# Installation

---`$ kubectl port-forward service/fusionauth-helm 9012:9012 --address 0.0.0.0`
`$ kubectl port-forward service/mongo-kye-mongodb 27017:27017 --address 0.0.0.0`
`$ kubectl port-forward service/mongo-document-mongodb 27018:27017 --address 0.0.0.0`
`$ kubectl port-forward service/redis-master 6379:6379 --address 0.0.0.0`
`$ kubectl port-forward service/kafka 9092:9092 --address 0.0.0.0`

kubectl port-forward service/api-getaway-web-chart 8000:80 --address 0.0.0.0


kubectl port-forward service/notification-chart  8005:8005 --address 0.0.0.0

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
docker build -t vadimshtukan/document-chart:0.0.3 .
docker push vadimshtukan/document-chart:0.0.3

docker login --username=vadimshtukan
docker build -t vadimshtukan/billing-chart:0.0.1 .
docker push vadimshtukan/billing-chart:0.0.1


docker login --username=vadimshtukan
docker build -t vadimshtukan/notification-chart:0.0.1 .
docker push vadimshtukan/notification-chart:0.0.1


PS C:\Users\vadim> kubectl port-forward service/api-getaway-web-chart 8000:80
error: error upgrading connection: unable to upgrade connection: Authorization error (user=kube-apiserver-kubelet-client, verb=create, resource=nodes, subresource=proxy)