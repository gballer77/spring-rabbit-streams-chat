package software.baller.eventchat.controller;

import software.baller.eventchat.domain.ChatMessageDTO;
import software.baller.eventchat.domain.ChatMessageMapper;
import software.baller.eventchat.domain.ChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class WebController {

    private final ChatService chatService;

    private final Sinks.Many<ChatMessageDTO> bus;

    public WebController(ChatService chatService, Sinks.Many<ChatMessageDTO> bus) {
        this.chatService = chatService;
        this.bus = bus;
    }

    @PostMapping("/messages")
    public void writeMessage(@RequestBody ChatMessageDTO messageDTO) {
        chatService.sendToBus(ChatMessageMapper.INSTANCE.fromDTO(messageDTO));
    }

    @GetMapping("/messages")
    public List<ChatMessageDTO> readMessages() {
        return chatService.getEvents().stream().map(ChatMessageMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @GetMapping(path = "/messages-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatMessageDTO> messageFlux() {
        return bus.asFlux();
    }
}

