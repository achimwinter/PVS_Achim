package assignment8.data;

import assignment8.util.ServerLinkConverter;
import com.owlike.genson.annotation.JsonConverter;
import org.glassfish.jersey.linking.InjectLink;

import javax.persistence.*;
import javax.ws.rs.core.Link;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments_table")
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "comments_author_id")
    private User comments_author;

    @Column(name = "votes")
    private int votes;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "comment_message_id")
    private Message comment_message;

    @InjectLink(style = InjectLink.Style.ABSOLUTE, value = "/messages/${instance.messageId}/comments/${instance.id}", rel = "self", type = "application/json")
    @Transient
    private Link self;

    public Comment() {
    }

    @JsonConverter(ServerLinkConverter.class)
    public Link getSelf() {
        return self;
    }

    public Message getMessage() {
        return comment_message;
    }

    public void setMessage(final Message message) {
        this.comment_message = message;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(final int votes) {
        this.votes = votes;
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

    public User getComments_author() {
        return comments_author;
    }

    public void setComments_author(final User author) {
        this.comments_author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
