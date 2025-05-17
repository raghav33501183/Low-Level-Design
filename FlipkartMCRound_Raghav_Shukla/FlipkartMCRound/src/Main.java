import exceptions.ValidationException;
import service.LibraryService;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LibraryService library = new LibraryService();

        String raghavId = library.registerUser("Raghav Shukla");
        String ashishId = library.registerUser("Ashish Aggarwal");

        String hpBookId = library.addBook("Harry Potter", "J.K. Rowling", 1);
        String lotrBookId = library.addBook("Lord of the Rings", "J.R.R. Tolkien", 2);

        System.out.println("Raghav borrows HP: " + library.borrowBook(raghavId, hpBookId));
        System.out.println("Ashish borrows HP: " + library.borrowBook(ashishId, hpBookId));

        Thread.sleep(1000); // simulate time
        try {
            int fine = library.returnBook(raghavId, hpBookId);
            System.out.println("Raghav returns HP with fine: " + fine);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Found unexpected error while fine calcution");
        }

        System.out.println("Ashish borrows HP again: " + library.borrowBook(ashishId, hpBookId));

        System.out.println("Books borrowed by Ashish: " + library.getBooksByUser(ashishId));
        System.out.println("Users who had HP: " + library.getUsersByBook(hpBookId));

        boolean success = library.unregisterUser(ashishId);
        System.out.println(success ? "User unregistered" : "UnRegistration failed");
    }
}