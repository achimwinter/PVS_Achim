package assignment8.data;


import javax.persistence.*;

@Entity
@Table(name = "users_table")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Integer getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
