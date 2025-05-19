package models;

public class Vote {
    private VoteType type;
    private User user;

    public Vote(VoteType type, User user) {
        this.type = type;
        this.user = user;
    }

    public VoteType getType() {
        return type;
    }

    public User getUser() {
        return user;
    }
}
