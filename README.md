# Telegram Recommendation Bot

## 📌 Описание проекта

Проект представляет собой backend-систему банковского приложения с базовыми операциями и 
системой рекомендаций, интегрированной в Telegram-бота.  
Система анализирует поведение пользователей и формирует персонализированные рекомендации на основе динамических правил.

---

## ⚙️ Технологический стек

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Telegram Bot API
- Swagger

---

## 🧠 Основной функционал

- Управление пользователями и аккаунтами
- Банковские операции (депозит, вывод, транзакции)
- Генерация динамических правил рекомендаций
- Rule Engine для проверки условий
- Telegram уведомления и взаимодействие
- Статистика срабатывания правил

---

## 👥 Участники

- Team Lead / Backend Developer: Кирилл Майбо 
- Backend Developer: Ольга Прохорова
- Backend Developer: Вирсавия Дышекова

---

## Запуск проекта

```bash
java -jar target/telegrambot-0.0.1-SNAPSHOT.jar
```

## 📦 Сборка проекта

```bash
./mvnw clean package
```

## Запуск проекта

## Системные требования

- Java 17+
- Maven 3.8+
- PostgreSQL 13+

Для работы проекта обязательно создать базы данных
Проект работает с 3-мя базами данных
1 бд хранит информацию о пользователях приложения и их аккаунтах
2 бд хранит информация правила рекомендаций 
3 бд хранит информацию о пользователях телеграмм бота

spring.datasource.primary.url=jdbc:postgresql://localhost:5432/ваша бд 
spring.datasource.primary.username=postgres
spring.datasource.primary.password=postgres
spring.datasource.primary.driver-class-name=org.postgresql.Driver

spring.datasource.secondary.url=jdbc:postgresql://localhost:5432/ваша бд 
spring.datasource.secondary.username=postgres
spring.datasource.secondary.password=postgres
spring.datasource.secondary.driver-class-name=org.postgresql.Driver

spring.datasource.telegram.url=jdbc:postgresql://localhost:5432/ваша бд 
spring.datasource.telegram.username=postgres
spring.datasource.telegram.password=postgres
spring.datasource.telegram.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update

./mvnw clean package

## Запуск приложения

java -jar target/telegrambot-0.0.1-SNAPSHOT.jar

## Быстрый запуск
./mvnw clean package && java -jar target/telegrambot-0.0.1-SNAPSHOT.jar


## Проверка работы

После запуска:

API: http://localhost:8080
Telegram bot: активен при корректном BOT_TOKEN
PostgreSQL: подключение через DB_URL

## Возможные проблемы
- БД не подключается
проверь PostgreSQL
проверь DB_URL
- Bot не отвечает
проверь BOT_TOKEN
проверь интернет доступ

## Swagger/OpenAPI

Документация API доступна:

http://localhost:8080/swagger-ui/index.html
