package software.baller.eventchat.domain;

import software.baller.chat.ChatMessage;
import software.baller.eventchat.IChatEventRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatEventMemoryRepository implements IChatEventRepository {
    private final List<ChatMessage> chatMessages;

    public ChatEventMemoryRepository() {
        chatMessages = new ArrayList<>();
    }

    @Override
    public void saveEvent(ChatMessage event) {
        chatMessages.add(event);
    }

    @Override
    public List<ChatMessage> getAllChatEvents() {
        return chatMessages;
    }
}
