package assignment8.data;

import java.time.LocalDateTime;

public class Comment {

    private String text;
    private User author;
    private int votes;
    private LocalDateTime createdAt;


    public Comment(String text) {
        this.text = text;
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

}
