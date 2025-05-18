package models;

public abstract class User {
    public String name;
    public String email;
    public Account account;

    public User(String name, String email, Account account) {
        this.name = name;
        this.email = email;
        this.account = account;
    }
}
