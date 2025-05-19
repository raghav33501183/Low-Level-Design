package models;

import java.util.List;

public interface Post {
    void addComment(Comment comment);
    void addVote(Vote vote);
    String getContent();
    User getAuthor();
    int getVoteCount();
    List<Comment> getComments();
}
