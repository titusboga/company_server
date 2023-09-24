package com.lab4.demo.security;

import com.lab4.demo.security.dto.SignupRequest;
import com.lab4.demo.user.RoleRepository;
import com.lab4.demo.user.UserRepository;
import com.lab4.demo.user.model.ERole;
import com.lab4.demo.user.model.Role;
import com.lab4.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByCode(String code) {
        return userRepository.existsByCode(code);
    }

    private String generateCode()
    {
        // Define the characters from which the passphrase will be generated
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:,.<>?";

        // Length of the passphrase
        int length = 50;

        // Create a random object
        Random random = new Random();

        // Initialize a StringBuilder to store the passphrase
        StringBuilder passphrase = new StringBuilder();

        // Generate the passphrase by randomly selecting characters from the set
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            passphrase.append(randomChar);
        }
        return passphrase.toString();
    }

    public void register(SignupRequest signUpRequest) {

        String code = generateCode();
        while(existsByCode(code))
        {
            code = generateCode();
        }

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .code(code)
                .build();

        Set<String> rolesStr = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (rolesStr == null) {
            Role defaultRole = roleRepository.findByName(ERole.CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Cannot find CUSTOMER role"));
            roles.add(defaultRole);
        } else {
            rolesStr.forEach(r -> {
                Role ro = roleRepository.findByName(ERole.valueOf(r))
                        .orElseThrow(() -> new RuntimeException("Cannot find role: " + r));
                roles.add(ro);
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
    }
}
