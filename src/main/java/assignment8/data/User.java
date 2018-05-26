package assignment8.data;

import java.util.LinkedList;
import java.util.List;

public class User {


    private final Integer id;
    private final List<Comment> comments = new LinkedList<>();
    private final List<Message> messages = new LinkedList<>();


    public User(Integer id) {
        this.id = id;
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public void deleteMessage(Message message){
        messages.remove(message);
    }

}
