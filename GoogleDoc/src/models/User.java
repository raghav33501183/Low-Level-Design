package models;

import java.util.UUID;

public class User {
    public UUID id;
    public String username;
    public String email;
    public String passwordHash;
    public Role role;
}
