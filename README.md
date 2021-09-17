# Agenda Quiro

### Pipeline

* [CircleCI - Agenda Quiro](https://app.circleci.com/pipelines/github/)
* [Heroku - Agenda Quiro](https://dashboard.heroku.com/apps/agenda-quiro:)

## Deploy Heroku
1) Instalar o heroku CLI https://devcenter.heroku.com/articles/heroku-command-line
2) Adicionar o remote do heroku
```shell script
git remote add heroku  https://git.heroku.com/agenda-quiro.git
```
2) Fazer o push
```shell script
git push heroku
```

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

## Iniciar backend

Em um terminal, execute o comando:

```shell script
./gradlew bootrun --args='--spring.profiles.active=dev'
```

## Iniciar frontend

```shell
cd frontend
yarn install
yarn start
```

- Abrir no browser http://localhost:3000/


### Como ver os logs da aplicação
- Precisa primeiro instalar o client no terminal https://devcenter.heroku.com/articles/heroku-cli

### Stage
```
heroku logs --app agenda-quiro --tail
```

## Tecnologias:
- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Node 12.16.2 LTS](https://nodejs.org/en/download/)
- [React](https://reactjs.org/docs/getting-started.html)
- [Postgres 12](https://www.postgresql.org/download/)
- [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/)
- [Heroku reference](https://devcenter.heroku.com/categories/reference)
- [CircleCI reference](https://circleci.com/docs/2.0/getting-started/#section=getting-started)


## Como contribuir?

### 1) Escolha uma card no Kanban

### 2) Crie um branch novo com a nomeclarura ```numero_do_card/nome_do_card```
- Exemplo: ```4/adiciona-botao-envio-email```
```
git checkout master
git pull
git checkout -b 4/adiciona-botao-envio-email
```

### 3) Faça os commits necessários seguindo nomeclatura:```numero_do_card/@nome_aluna @nome aluna: o que o commit faz  para commit```&nbsp;
- Exemplo: ```#4/ @Maria @Joao: Adiciona o CSS para o botão de envio de email```
- Exemplo: ```#4/ @Maria @Joao: Adcionar o layout do botão de envio de email```
```
git commit -m "#4/ @Maria @Joao: Adiciona o CSS para o botão de envio de email"
```

### 4) Rodar os testes locais e garantir que estão passando

- ```./gradlew buildForProduction```

### 5) Realize o push do branch
- ```git push --set-upstream origin <NOME DO BRANCH>```

### 6) Abra um Pull Request no GitHub com o Branch

### 7) Após revisado e testado pode mergear com a master
- O branch precisa estar revisado e aprovado
- O branch não pode ter conflitos
- O branch precisa rodar os testes de integração com sucesso (CircleCI)

### 8) Garantir que o build+deploy da master rodou com sucesso e pedir para outra dupla fazer o QA
- BUILD: https://app.circleci.com/pipelines/github/agenda-quiro

###  Ferramentas recomendadas (não obrigatórias)

Algumas recomendações de ferramentas para trabalhar com este template. Embora recomendadas, elas *não são obrigatórias*
para o funcionamento do projeto:

  - [Docker](https://www.docker.com/get-started)
  - [Postman](https://www.postman.com/downloads/)
  - [Visual Studio Code](https://code.visualstudio.com/download)
  - [Tmux](https://github.com/tmux/tmux/wiki)

