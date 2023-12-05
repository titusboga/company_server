package company.user;

import company.UrlMapping;
import company.Hello;
import company.Hello.LoginResponse;
import company.Hello.LoginResponse.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = UrlMapping.REGISTER, consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public ResponseEntity<LoginResponse> createUser(@RequestBody Hello.RegisterRequest registerRequest) {
        // Assuming userService.createUser(registerRequest) returns a boolean indicating success or failure
         boolean isSuccess = userService.createUser(registerRequest);
       // boolean isSuccess = true;
        LoginResponse response = isSuccess
                ? LoginResponse.newBuilder().setStatus(Status.SUCCESS).setMessage("User created successfully.").build()
                : LoginResponse.newBuilder().setStatus(Status.FAILURE).setMessage("Failed to create user.").build();

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = UrlMapping.LOGIN, consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public ResponseEntity<Hello.LoginResponse> loginUser(@RequestBody Hello.LoginRequest loginRequest) {
        boolean isSuccess = userService.loginUser(loginRequest);

        Hello.LoginResponse response = isSuccess
                ? Hello.LoginResponse.newBuilder().setStatus(Hello.LoginResponse.Status.SUCCESS).setMessage("Login successful.").build()
                : Hello.LoginResponse.newBuilder().setStatus(Hello.LoginResponse.Status.FAILURE).setMessage("Invalid credentials.").build();

        return ResponseEntity.ok(response);
    }

}
