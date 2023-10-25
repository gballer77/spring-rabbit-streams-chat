package software.baller.eventchat.domain;

public class ChatMessageMapper {
    public static ChatMessageMapper INSTANCE = new ChatMessageMapper();
    public ChatMessageDTO toDTO(ChatMessage event) {
        return new ChatMessageDTO(event.name(), event.message());
    }
    public ChatMessage fromDTO(ChatMessageDTO dto){
        return new ChatMessage(dto.name(), dto.message());
    }
}
