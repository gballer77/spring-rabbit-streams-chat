# Rabbit Messenger

## Getting started

1. Clone the repository
2. Run `docker-compose up -d` to start RabbitMQ
3. Run `./gradlew copyFrontend`
4. You'll need two Spring Boot run configurations. You should have one by default. Add a second one with Active Profiles
   set to `instance-two`

> We're using run configurations instead of gradle because the intellij run console plays better with the
> ShellController than terminal

5. Run you one of your confurations through intellij
6. The run console should open and you should see the typical spring startup logs
7. Click into the console and type `say hi` and press `enter`. You should see your message logged in the console.
8. Leave this configuration running and run the other configuration through intellij
6. Open two browser tabs to [http://localhost:8080/](http://localhost:8080/) and [http://localhost:8081/](http://localhost:8081/)