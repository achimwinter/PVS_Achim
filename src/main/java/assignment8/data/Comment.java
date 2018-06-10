package assignment8.data;

import assignment8.linkutils.ServerLinkConverter;
import com.owlike.genson.annotation.JsonConverter;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.Link;
import java.time.LocalDateTime;

public class Comment {

    private int id;
    private String text;
    private User author;
    private int votes;
    private LocalDateTime createdAt;
    private Message message;

    @InjectLink( style = InjectLink.Style.ABSOLUTE, value = "/comments/{instance.id}", rel = "self", type = "application/json")
    private Link self;

    @JsonConverter(ServerLinkConverter.class)
    public Link getSelf(){
        return self;
    }


    public Comment(final String text, final Message message) {
        this.message = message;
        this.text = text;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(final Message message) {
        this.message = message;
    }

    public int getVotes() {
        return votes;
    }

    public void incrementVotes() {
        this.votes++;
    }

    public void decrementVotes() {
        this.votes--;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(final User author) {
        this.author = author;
    }

    public void setVotes(final int votes) {
        this.votes = votes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
