# Account Balance API

Простое Spring Boot REST API для управления счетом и транзакциями с конвертацией валют.

## Функционал
- Создание баланса с уникальным именем (USD)
- Депозит/снятие (deposit/withdraw) средств в разных валютах
- С автоматической конвертацией валют(USD, EUR, RUB, BYN)
- Пересчет баланса в USD
- История транзакций по балансу

## Как запустить
1. Склонировать репозиторий:
    ```
    git clone 
    cd account-balance
    ```

2. Собрать и запустить:
    ```
    ./mvnw spring-boot:run
    ```
   или
    ```
    mvn clean install
    java -jar target/account-balance-0.0.1-SNAPSHOT.jar
    ```

3. API будет доступно по адресу:  
   `http://localhost:8080`

## Примеры запросов (Postman)

### 1. Создать баланс
POST /api/balances
Content-Type: application/json

{
"name": "main-account"
} 

### 2. Получить баланс
GET /api/balances/main-account

### 3. Депозит (EUR)
POST /api/balances/main-account/transactions
Content-Type: application/json

{
"type": "deposit",
"amount": 100,
"currency": "EUR"
}

### 4. Снятие (RUB)
POST /api/balances/main-account/transactions
Content-Type: application/json

{
"type": "withdraw",
"amount": 2000,
"currency": "RUB"
}

### 5. Получить все транзакции
GET /api/balances/main-account/transactions


## Валюты и курсы

- USD: 1.00
- EUR: 1.07
- BYN: 0.31
- RUB: 0.011

## Требования

- Java 17
- Maven
- PostgreSQL

## Swagger/OpenAPI

После запуска перейдите на  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

