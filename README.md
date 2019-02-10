# Id as a Service
This is  REST service that generates integers incrementally for registered users.

## Endpoints
This app has 5 REST endpoints:

1 Login into the API  
Body information: e-mail, password, and the table that the ID will relate to.
```
curl --request POST \
  --url https://arcane-peak-14291.herokuapp.com/v1/login \
  --header 'content-type: application/json' \
  --data '{
	"email": "test@test.com",
	"password": "12345",
	"tableName": "name"
}'
```

2 Register user  
Body information: e-mail, password, and the table that the ID will relate to.
```
curl --request POST \
  --url https://arcane-peak-14291.herokuapp.com/v1/new \
  --header 'content-type: application/json' \
  --data '{
	"email": "test@test.com",
	"password": "12345",
	"tableName": "name"
}'
```

3 Fetch last ID retrieved  
Body information: apyKey
```
curl --request POST \
  --url https://arcane-peak-14291.herokuapp.com/v1/current \
  --header 'content-type: application/json' \
  --data '{
	"apiKey": "dGVzdEB0ZXN0LmNvbToxMjM0NTpuYW1l"
}'
```

4 Get the next available ID
Body information: apyKey
```
curl --request POST \
  --url https://arcane-peak-14291.herokuapp.com/v1/next \
  --header 'content-type: application/json' \
  --data '{
	"apiKey": "dGVzdEB0ZXN0LmNvbToxMjM0NTpuYW1l"
}'
```

4 Reset the ID to a new value  
Url: the new value should be added to the URL  
Body information: apyKey
```
curl --request PUT \
  --url 'https://arcane-peak-14291.herokuapp.com/v1/reset?newId=5' \
  --header 'content-type: application/json' \
  --data '{
	"apiKey": "dGVzdEB0ZXN0LmNvbToxMjM0NTpuYW1l"
}'
```

## Run Application

### Heroku
The application is deployed in Heroku. It can be accessed using a Rest client.

## How to run locally

### Requirements for execution
- [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [Maven](http://maven.apache.org/download.cgi)

### Steps
- After properly installing the requirements, clone this repository to a folder on your computer.
- Enter the project folder

- Execute:
```
mvn spring-boot:run
```

Replace the host in the curls above by your local URL, for example:
```
curl --request POST \
  --url http://localhost:8080/v1/new \
  --header 'content-type: application/json' \
  --data '{
	"email": "test@test.com",
	"password": "12345",
	"tableName": "name"
}'
```