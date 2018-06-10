package assignment8.data;

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
        messages.add(message);
        this.id++;
        return this.id - 1;
    }

    public List<Message> getAllMessages(){
        return this.messages;
    }
}
