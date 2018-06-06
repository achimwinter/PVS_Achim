package assignment8.data;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Message {

    private String text;
    private int votes;
    private User author;
    private LocalDateTime createdAt;

    public Message(String text) {
        this.text = text;
    }

    public int getVotes() {
        return this.votes;
    }

    public String getText() {
        return this.text;
    }

    public void incrementVotes() {
        this.votes++;
    }

    public void decrementVotes() {
        this.votes--;
    }

    public void modifyMessage(String text) {
        this.text = text;
    }

}
