# Java WebSocket Example
A simple Java WebSocket chat application developed with Java, Java JSONB.

## [Download](https://github.com/yusufsefasezer/java-websocket-example/archive/master.zip)

## How to run

Maven must be installed to run this application.

If you have maven execute the below command to run.

```
mvn clean package cargo:run
```

You can access the application using `localhost:8080/java-websocket-example` in your web browser.

## Docker

**Docker must be installed.**

Build the Docker image with the tag "java-websocket-example"

```
docker build -t java-websocket-example .
```

```
docker run -p 80:8080 java-websocket-example
```

You can access the application using `localhost:80` in your web browser.

## Screenshot

- [Login](screenshot/login.png)
- [Desktop](screenshot/desktop.png)
- [Phone](screenshot/phone.png)

# License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details

Created by [Yusuf Sezer](https://www.yusufsezer.com)
