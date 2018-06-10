package assignment8.data;

import assignment8.linkutils.Hyperlinks;

import java.util.LinkedList;
import java.util.List;

public class MessageManager {

    private List<Message> messages = new LinkedList<>();
    private Integer id = 0;

    private static volatile MessageManager instance;
    private static Object mutex = new Object();

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


    public int addMessage(Message message){
        message.setId(this.id);
        messages.add(message);
        this.id += 2;
        return this.id--;
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
