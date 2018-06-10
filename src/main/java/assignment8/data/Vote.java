package assignment8.data;

import java.time.LocalDateTime;

public class Vote {

    private User author;
    private VoteType voteType;
    private LocalDateTime createdAt;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(final User author) {
        this.author = author;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(final VoteType voteType) {
        this.voteType = voteType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
