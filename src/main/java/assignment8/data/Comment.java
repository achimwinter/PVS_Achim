package assignment8.data;

public class Comment {

    private final String text;
    private int votes = 0;

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
