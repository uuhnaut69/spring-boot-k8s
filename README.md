# spring-boot-k8s

Spring boot deploy k8s example

## Prerequisites

- `Java 17`
- `Docker / Docker-compose`
- `Minikube`

## Get Started

- [x] Init sample application
- [x] Deploy sample application k8s
- [x] CRUD app sample
- [ ] Deploy Postgres k8s
- [ ] Deploy oauth2 service k8s (Keycloak)

### 1. Install minikube

Install minikube

```shell
brew install minikube
```

Show minikube dashboard

```shell
minikube dashboard
```

### 2. Build app docker image

Build docker image

```shell
./mvnw spring-boot:build-image
```

Tag docker image

```shell
docker tag demo:0.0.1-SNAPSHOT uuhnaut69/spring-demo
```

Push image

```shell
docker push uuhnaut69/spring-demo
```

### 3. Generate k8s yaml files

Make directory to store all yaml files

```shell
mkdir deployment
```

Generate deployment yaml

```shell
kubectl create deployment spring-boot-app --image uuhnaut69/spring-demo -o yaml --dry-run=client > deployment/app-deployment.yaml
```

Generate service yaml

```shell
kubectl create service clusterip spring-boot-app --tcp 80:8080 -o yaml --dry-run=client > deployment/app-service.yaml
```

### 4. Deploy

Apply deployment

```shell
kubectl apply -f ./deployment
```

Config port forward

```shell
kubectl port-forward svc/spring-boot-k8s 9090:80
```

Test

```shell
curl http://localhost:9090/greeter; echo
```