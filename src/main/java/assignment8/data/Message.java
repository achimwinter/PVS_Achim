package assignment8.data;

import java.time.LocalDateTime;

public class Message {

    private int id;
    private String text;
    private int votes;
    private User author;
    private LocalDateTime createdAt;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setVotes(final int votes) {
        this.votes = votes;

    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(final User author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setText(String text){
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
