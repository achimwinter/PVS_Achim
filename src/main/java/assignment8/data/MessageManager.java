package assignment8.data;

import java.util.LinkedList;
import java.util.List;

public class MessageManager {

    private static final Object mutex = new Object();
    private static volatile MessageManager instance;
    private final List<Message> messages = new LinkedList<>();
    private Integer id = 0;

    private MessageManager() {
    }

    public static MessageManager getInstance() {
        MessageManager result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new MessageManager();
            }
        }
        return result;
    }

    public void modifyComment(final int oldMessageId, final Message newMessage) {
        messages.remove(oldMessageId);
        newMessage.setId(oldMessageId);
        messages.add(newMessage);
    }


    public int addMessage(final Message message) {
        message.setId(this.id);
        messages.add(message);
        this.id++;
        return this.id;
    }

    public Message getMessage(final int id){
        return messages.get(id);
    }

    public List<Message> getAllMessages(){
        return this.messages;
    }

    public void deleteMessage(final int id){
        messages.remove(id);
    }
}
