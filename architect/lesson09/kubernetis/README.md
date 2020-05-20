###Description  
![Image description](https://)  
В качестве сервиса авторизации был выбран Fusionauth (https://fusionauth.io). Данный сервис берет на себя все функции управления пользователями (регистрация, авторизация и т.п.) Есть бесплатная self-hosting Community версия и возможность использовать облачное решение(платное). В данной реализации был установлен self-hosting. 

Роутинг и фильтрацию обеспечивает Spring Cloud Gataway. Spring Cloud Gataway предназначен для маршрутизации запросов в микросерверной среде, а так же в нем можно добавлять кастемные фильтры, н.п. проверка JWT токена или преобразовывать JSON запросы. 

Принцип работы. Для логина клиент передает свои данные Spring Cloud Gataway, который перенаправляет запрос на Fusionauth сервис. Fusionauth сервис, в случае успеха, выдает JWT токен и устанавливает cookie. Далее, для авторизированного доступа, клиент должен использовать JWT в cookie или в bearer authentication. Все авторизированные запросы проходят через Spring Cloud Gataway, который проверяет у себя JWT. Проверка производится с использованием закрытого ключа Fusionauth. При успешной аутентификации Spring Cloud Gataway добавляет в HTTP заголовок информацию о пользователе, удостоверя таким образомего, и перенаправляет запрос сервису согласно ройтин-правил.

##Installing  
`$ minikube addons enable ingress`  
`$ helm dependency update .\lesson-09-chart`  
`$ helm install lesson-09 .\lesson-09-chart`  
`$ helm repo add fusionauth https://fusionauth.github.io/charts`  
`$ helm install fusionauth-helm -f .\fusionauth.yml fusionauth/fusionauth`  
##Postman collection  
`https://www.getpostman.com/collections/148c08e6f4144a214a64`
