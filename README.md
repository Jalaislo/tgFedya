# TgFedyaBot 🤖

Telegram-бот, написанный на Java с использованием Spring Boot и библиотеки [TelegramBots](https://github.com/rubenlagus/TelegramBots). Бот предназначен для выполнения базовых команд и взаимодействия с пользователями.

## Текущие функции 🛠️

- **/start** — Приветственное сообщение.
- **/help** — Список доступных команд.
- **/save_attachment** — Сохранение вложений (функция в разработке).
- **/store_message** — Сохранение сообщений (функция в разработке).
- **/list_members** — Список участников (функция в разработке).
- **/transcribe_voice** — Расшифровка голосовых сообщений (функция в разработке).

## Технологический стек 🛠️

- **Java 23** — Основной язык разработки.
- **Spring Boot 3.4.1** — Фреймворк для создания приложения.
- **TelegramBots 6.9.7.1** — Библиотека для взаимодействия с Telegram API.
- **PostgreSQL** — База данных для хранения информации.
- **HikariCP** — Пул соединений для работы с базой данных.
- **Gradle** — Система сборки проекта.

## Как запустить бота 🚀

### 1. Клонируйте репозиторий
```bash
git clone https://github.com/ваш-username/ваш-репозиторий.git
cd ваш-репозиторий
```

### 2. Настройте `application.properties`
Создайте файл `src/main/resources/application.properties` и добавьте в него следующие настройки:

```properties
# Telegram Bot Configuration
telegram.bot.username=ВАШ_ЮЗЕРНЕЙМ_БОТА
telegram.bot.token=ВАШ_ТОКЕН_БОТА
telegram.bot.enabled=true

# Настройки для Yandex SpeechKit (опционально)
yandex.speechkit.api-key=ВАШ_API_KEY
yandex.speechkit.folder-id=ВАШ_FOLDER_ID

# Настройки для сохранения вложений
file.storage.path=./attachments

# Подключение к PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/tgfedya
spring.datasource.username=admin
spring.datasource.password=admin

# Настройки Hibernate (JPA)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Настройка пула соединений
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=2
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.max-lifetime=1800000

# Логирование
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
logging.level.root=INFO
logging.level.com.example.tgFedya=DEBUG
logging.level.org.telegram.telegrambots=DEBUG
```

### 3. Запустите базу данных PostgreSQL
Убедитесь, что у вас установлена и запущена PostgreSQL. Создайте базу данных с именем `tgfedya` и настройте доступ к ней через указанные в `application.properties` параметры.

### 4. Соберите и запустите проект
Используйте Gradle для сборки и запуска проекта:

```bash
./gradlew build
./gradlew bootRun
```

### 5. Начните общение с ботом
Откройте Telegram, найдите своего бота по юзернейму и начните общение с помощью команды `/start`.