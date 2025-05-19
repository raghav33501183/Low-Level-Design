package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
private String userId;
private String name;
private String email;
private List<Form> ownedForms;

public User(String name, String email) {
    this.userId = UUID.randomUUID().toString();
    this.name = name;
    this.email = email;
    this.ownedForms = new ArrayList<>();
}

public void createForm(Form form) {
    ownedForms.add(form);
}

public List<Form> getOwnedForms() {
    return ownedForms;
}

public String getUserId() { return userId; }
public String getName() { return name; }
public String getEmail() { return email; }
}