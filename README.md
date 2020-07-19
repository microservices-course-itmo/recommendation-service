# Service template

Для того чтобы запустить сервис на локальной машине вам понадобятся kafka и postgres.

Чтобы запустить kafka и postgres на локальной машине в Docker:
 - выполните `minimal-Infrastructure.yml` файл, используя Ide или с помощью команды `docker-compose -f profiles/minimal-Infrastructure.yml up` после чего запустить приложение через IDEA

Если вы хотите запустить сервис в составе docker-compose выполните следующее:
- выполните `mvn spring-boot:build-image`, это запустит сборку докер образа самого сервиса
- выполните `docker-compose -f profiles/minimal-Infrastructure.yml -f profiles/service-app.yml up`
 
 При изменении версии в сервиса в pom файле нужно изменить версию приложения в service-app.yml
