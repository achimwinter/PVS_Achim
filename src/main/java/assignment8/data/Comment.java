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

    private Long id;

    private String text;

    private User comments_author;

    private int votes;

    private LocalDateTime createdAt;

    private Message comment_message;

    @InjectLink(style = InjectLink.Style.ABSOLUTE, value = "/messages/${instance.messageId}/comments/${instance.id}", rel = "self", type = "application/json")
    private Link self;

    public Comment() {
    }

    @Transient
    @JsonConverter(ServerLinkConverter.class)
    public Link getSelf() {
        return self;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "comments_author_id")
    public User getComments_author() {
        return comments_author;
    }

    public void setComments_author(User comments_author) {
        this.comments_author = comments_author;
    }

    @Column(name = "votes")
    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Column(name = "createdAt")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "comment_message_id")
    public Message getComment_message() {
        return comment_message;
    }

    public void setComment_message(Message comment_message) {
        this.comment_message = comment_message;
    }


    public void incrementVotes() {
        this.votes++;
    }

    public void decrementVotes() {
        this.votes--;
    }

}
