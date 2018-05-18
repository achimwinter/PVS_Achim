package assignment8.data;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class User {


    private final UUID id;
    private final List<Comment> comments = new LinkedList<>();
    private final List<Message> messages = new LinkedList<>();


    public User(UUID id) {
        this.id = id;
    }
}
