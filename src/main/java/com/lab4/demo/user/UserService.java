package com.lab4.demo.user;

import com.lab4.demo.user.model.User;
import company.Hello;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean createUser(Hello.RegisterRequest registerRequest) {
        try {
            // Create a new user
            User user = new User(0L, registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getPassword());

            // Save the user to the repository
            User savedUser = userRepository.save(user);

            // Check if the user was saved successfully
            // Assuming that a successful save will generate a non-zero ID
            return savedUser.getId() != 0L;
        } catch (Exception e) {
            // Log the exception, handle it, or rethrow as appropriate
            // For example:
            // log.error("Error saving user", e);
            return false;
        }
    }

    public boolean loginUser(Hello.LoginRequest loginRequest) {
        User user = null;
        if (loginRequest.getCredentialCase() == Hello.LoginRequest.CredentialCase.EMAIL) {
            user = userRepository.findByEmail(loginRequest.getEmail());
        } else if (loginRequest.getCredentialCase() == Hello.LoginRequest.CredentialCase.USERNAME) {
            user = userRepository.findByUsername(loginRequest.getUsername());
        }

        if (user != null /* && bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword()) */) {
            // Authentication successful
            return true;
        } else {
            // Authentication failed
            return false;
        }
    }

}
