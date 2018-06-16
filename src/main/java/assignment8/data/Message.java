package assignment8.data;

import assignment8.util.ServerLinkConverter;
import com.owlike.genson.annotation.JsonConverter;
import org.glassfish.jersey.linking.InjectLink;

import javax.persistence.*;
import javax.ws.rs.core.Link;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "messages_table")
public class Message implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "text")
    private String text;

    @Column(name = "votes")
    private int votes;

    @Column(name = "authorId")
    private int authorID;

    @Column(name = "createdAt")
    private Date createdAt;

    @InjectLink(style = InjectLink.Style.ABSOLUTE, value = "/messages/${instance.id}", rel = "self", type = "application/json")
    @Transient
    private Link self;

    @JsonConverter(ServerLinkConverter.class)
    public Link getSelf() {
        return self;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(final int authorID) {
        this.authorID = authorID;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getVotes() {
        return this.votes;
    }

    public void setVotes(final int votes) {
        this.votes = votes;
    }

    public String getText() {
        return this.text;
    }

    public void setText(final String text) {
        this.text = text;

    }

    public void incrementVotes() {
        this.votes++;
    }

    public void decrementVotes() {
        this.votes--;
    }

    public void modifyMessage(final String text) {
        this.text = text;
    }

}
