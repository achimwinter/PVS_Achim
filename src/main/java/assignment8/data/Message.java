package assignment8.data;

import assignment8.linkutils.ServerLinkConverter;
import com.owlike.genson.annotation.JsonConverter;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.Link;
import java.time.LocalDateTime;

public class Message {

    private int id;
    private String text;
    private int votes;
    private User author;
    private LocalDateTime createdAt;

    @InjectLink( style = InjectLink.Style.ABSOLUTE, value = "/messages/${instance.id", rel = "self", type = "application/json")
    private Link self;

    @JsonConverter(ServerLinkConverter.class)
    public Link getSelf(){
        return self;
    }

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
