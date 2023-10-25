package software.baller.eventchat.domain;

import software.baller.eventchat.IChatEventRepository;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
public class ChatService {

    private final RabbitStreamTemplate streamTemplate;

    private final IChatEventRepository chatEventRepository;

    private final Sinks.Many<ChatMessageDTO> bus;

    public ChatService(RabbitStreamTemplate streamTemplate, IChatEventRepository chatEventRepository, Sinks.Many<ChatMessageDTO> bus) {
        this.streamTemplate = streamTemplate;
        this.chatEventRepository = chatEventRepository;
        this.bus = bus;
    }

    public void sendToBus(ChatMessage chatMessage) {
        streamTemplate.convertAndSend(chatMessage);
    }

    public void saveEvent(ChatMessage chatMessage) {
        chatEventRepository.saveEvent(chatMessage);
        bus.tryEmitNext(ChatMessageMapper.INSTANCE.toDTO(chatMessage));
    }

    public List<ChatMessage> getEvents() {
        return chatEventRepository.getAllChatEvents();
    }

}
