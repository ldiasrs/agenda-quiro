# Agenda Quiro

### Pipeline
* [Heroku - Agenda Quiro](https://dashboard.heroku.com/apps/agenda-quiro)

## Setup do projeto

### 1) Instalar JDK 11
- https://jdk.java.net/archive/

### 2) Configure o banco de dados volatil
Primeiro você precisa ter o docker server instalado na maquina local e rodando
Em um terminal, execute o comando:

```shell script
./docker-db.sh run-volatile
./docker-db.sh  create
```

### 3) Heroku config
- Instalar o heroku CLI https://devcenter.heroku.com/articles/heroku-command-line
- Adicionar o remote do heroku
```shell script
git remote add heroku  https://git.heroku.com/agenda-quiro.git
```

## Boot do projeto

### 1) Backend
- Em um terminal, execute o comando:
```shell script
./gradlew bootrun --args='--spring.profiles.active=dev'
```

### 2) Frontend

```shell
cd frontend
yarn install
yarn start
```

- Abrir no browser http://localhost:3000/

## Deploy no Heroku
- executar em um terminal a linha abaixo
- O build será executado e depois o deploy realizado
```shell script
git push heroku
```

### Como ver os logs da aplicação

### Stage
```
heroku logs --tail
```

## Links:
- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Node 12.16.2 LTS](https://nodejs.org/en/download/)
- [React](https://reactjs.org/docs/getting-started.html)
- [Postgres 12](https://www.postgresql.org/download/)
- [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/)
- [Heroku reference](https://devcenter.heroku.com/categories/reference)
- [CircleCI reference](https://circleci.com/docs/2.0/getting-started/#section=getting-started)
- [Docker](https://www.docker.com/get-started)
- [Postman](https://www.postman.com/downloads/)
- [Visual Studio Code](https://code.visualstudio.com/download)
- [Tmux](https://github.com/tmux/tmux/wiki)
- [react router](https://reactrouter.com/web/guides/quick-start)
- [react login session](https://github.com/rocketseat-content/blog-adonis-reactjs-react-native-airbnb-web/tree/parte-8)
- [datetime-jpa-converters](https://github.com/perceptron8/datetime-jpa/tree/master/src/main/java/com/github/perceptron8/datetime/jpa)
- [yarn network error](https://stackoverflow.com/questions/52135815/yarn-is-having-troubles-with-the-network-connection)
- [form style](https://codepen.io/naikjavaid/pen/XPrpjr)
- [react-hook-form-crud-example](https://github.com/cornflourblue/react-hook-form-crud-example)
- [entity to DTO](https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application)
- [model mapper](http://modelmapper.org/getting-started/)


