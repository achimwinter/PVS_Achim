package assignment8.data;

import assignment8.util.ServerLinkConverter;
import com.owlike.genson.annotation.JsonConverter;
import org.glassfish.jersey.linking.InjectLink;

import javax.persistence.*;
import javax.ws.rs.core.Link;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "messages_table")
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @OneToMany(mappedBy = "comment_message", cascade = {CascadeType.ALL})
    @Transient
    private List<Comment> comments;

    @Column(name = "text")
    private String text;

    @Column(name = "votes")
    private int votes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "message_author_id")
    private User message_author;

    @Column(name = "createdAt")
    private Date createdAt;

    @InjectLink(style = InjectLink.Style.ABSOLUTE, value = "/messages/${instance.id}", rel = "self", type = "application/json")
    @Transient
    private Link self;

    @JsonConverter(ServerLinkConverter.class)
    public Link getSelf() {
        return self;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getMessage_author() {
        return message_author;
    }

    public void setMessage_author(User author) {
        this.message_author = author;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
