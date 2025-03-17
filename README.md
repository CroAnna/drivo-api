# Drivo

This is an API for driving school management dashboard system built using Quarkus, Java framework.

## ER model

![drivo-er](https://github.com/user-attachments/assets/bf549589-c35f-495a-b89f-721b5201f149)

## Architecture

![Untitled Diagram(4)](https://github.com/user-attachments/assets/88d5ac2d-5f73-49d0-a092-3df647226bf7)


## Project setup

Before starting the project you must create public and private keys:

```
cd src/main/resources/
```

Generate private key:

```
openssl genpkey -algorithm RSA -out privateKey.pem
```


Generate public key:

```
openssl rsa -in privateKey.pem -pubout -out publicKey.pem
```

## Project description
- Management system for driving schools
- Handles drivers, employees, vehicles, driving lessons, exams and categories
- Role-based access control (admin, employee, instructor)
- Database seeder included (database name = driving-school)
- JWT authentication
- Statistics endpoints ready for PrimeNG Chart integration
- API request logging middleware that stores all API calls in database

![Untitled-1](https://github.com/user-attachments/assets/1f8c86aa-1b6c-406c-8815-9447986668ca)

> **_NOTE:_**  Quarkus Dev UI, available in dev mode only at <http://localhost:8080/q/dev/>.

## Running the application in dev mode

Run application in dev mode:

```shell script
./mvnw quarkus:dev
```

Application is available on `http://localhost:8080`


## Running the application using Docker

Package the application

```shell script
./mvnw package
```

Build the Docker image

```shell script
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/drivo-jvm .
```

Start the application using Docker Compose

```shell script
docker-compose up
```

Application is available on `http://localhost:8080`
