package software.baller.eventchat.controller;

import software.baller.chat.ChatMessage;
import software.baller.eventchat.domain.ChatService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellController {

    @Value("${server.port:8080}")
    private String port;

    private final ChatService chatService;

    public ShellController(ChatService chatService){
        this.chatService = chatService;
    }

    @ShellMethod(key = "say")
    public void say(
        @ShellOption(defaultValue = "hello") String arg
    ) {
        chatService.sendToBus(new ChatMessage("Shell " + port, arg.replaceAll(",", " ")));
    }
}