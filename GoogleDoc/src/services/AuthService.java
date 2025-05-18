package services;

import models.User;

import java.util.Optional;

public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordMatches(password, user.passwordHash));
    }

    private boolean passwordMatches(String raw, String hashed) {
        // Implement hash comparison
        return true;
    }
}
