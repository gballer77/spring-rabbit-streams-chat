package software.baller.eventchat;

import software.baller.eventchat.domain.ChatMessage;

import java.util.List;

public interface IChatEventRepository {
    void saveEvent(ChatMessage event);

    List<ChatMessage> getAllChatEvents();
}
