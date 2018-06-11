package assignment8.data;

import com.owlike.genson.annotation.JsonDateFormat;

import java.util.Date;

public class Vote {

    private int authorId;
    private VoteType voteType;

    @JsonDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    private Date createdAt;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(final int authorId) {
        this.authorId = authorId;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(final VoteType voteType) {
        this.voteType = voteType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }
}
