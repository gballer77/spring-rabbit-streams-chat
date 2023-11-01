# Rabbit Messenger

## Introduction
![img_1.png](img_1.png)

There are 4 different "applications" with code in this repo.  They are:

- Chat app backend: RabbitMQ Client that publishes and consumes messages from RabbitMQ Streams
- Chat app frontend: React client for Chat app with a chat window and map
- Random message producer: Spring Cloud Streams Source that produces random text messages that contain GPS lat lon
- Geographic message processor: Spring Cloud Streams Processor that reads text messages and produces JSON "Geo messages"

There are also 2 "out of the box" applications that are deployed via docker compose

- RabbitMQ with Streams enabled
- Spring Cloud Streams websocket sink

## Getting started

1. Clone the repository
2. Run `docker-compose up -d` to start RabbitMQ and the Websocket Sink for Geos
3. Run the applications in the following order using the Intellij services window (Command + 8)

    - Random Message Producer
    - Geographic Message Processor
    - Chat App Backend
    - Chat App frontend (Using `yarn dev` from frontend folder)

4. Open the necessary applications

    - RabbitMQ console at [localhost:15672](http://localhost:15672)

## Tasks