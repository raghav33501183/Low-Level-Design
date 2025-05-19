import models.*;

public class Main {
    public static void main(String[] args) {
        User alice = new User("Alice");
        User bob = new User("Bob");

        Question q1 = new Question("What is SOLID in OOP?", alice);
        q1.addTag(new Tag("OOP"));
        q1.addTag(new Tag("Design Principles"));

        Answer a1 = new Answer("SOLID is an acronym for five design principles...", bob);
        q1.addAnswer(a1);

        q1.addVote(new Vote(VoteType.UPVOTE, bob));
        a1.addVote(new Vote(VoteType.UPVOTE, alice));

        a1.addComment(new Comment("Great answer!", alice));
        q1.addComment(new Comment("Please elaborate with examples.", bob));

        System.out.println("Question: " + q1.getContent());
        System.out.println("Tags: " + q1.getTags().stream().map(Tag::getName).toList());
        System.out.println("Votes: " + q1.getVoteCount());
        System.out.println("Answers: ");
        for (Answer ans : q1.getAnswers()) {
            System.out.println(" - " + ans.getContent() + " | Votes: " + ans.getVoteCount());
        }
    }
}