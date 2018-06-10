package assignment8.data;

public class VoteType {

    private boolean vote;


    public VoteType(){

    }

    public VoteType(boolean vote){
        this.vote = vote;
    }

    public boolean isVotePositive() {
        return vote;
    }

    public void setVote(final boolean vote) {
        this.vote = vote;
    }
}
