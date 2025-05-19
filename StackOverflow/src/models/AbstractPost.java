package models;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPost implements  Post {
    protected String content;
    protected User author;
    protected List<Comment> comments = new ArrayList<>();
    protected List<Vote> votes = new ArrayList<>();

    public AbstractPost(String content, User author) {
        this.content = content;
        this.author = author;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addVote(Vote vote) {
        votes.add(vote);
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public int getVoteCount() {
        int count = 0;
        for (Vote v : votes) {
            count += v.getType() == VoteType.UPVOTE ? 1 : -1;
        }
        return count;
    }
}
