package services;

public class SearchFactory {
    public static SearchStrategy getStrategy(String type) {
        return switch (type.toLowerCase()) {
            case "title" -> new SearchByTitle();
            case "author" -> new SearchByAuthor();
            case "isbn" -> new SearchByISBN();
            default -> throw new IllegalArgumentException("Invalid search type");
        };
    }
}
