package models;

public class Account {
    public String username;
    public String password;
    public AccountStatus status;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = AccountStatus.ACTIVE;
    }
}
