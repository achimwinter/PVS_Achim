package assignment8.data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users_table")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "message_author", cascade = {CascadeType.ALL})
    private List<Message> messages;

    @OneToMany(mappedBy = "comments_author", cascade = {CascadeType.ALL})
    private List<Comment> comments;

    public Integer getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
