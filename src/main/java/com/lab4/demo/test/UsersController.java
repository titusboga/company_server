package com.lab4.demo.test;

import company.Hello;
import company.Hello.LoginResponse;
import company.Hello.LoginResponse.Status;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class UsersController {

    @PostMapping(value = "/users", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public ResponseEntity<LoginResponse> createUser(@RequestBody Hello.LoginRequest loginRequest) {
        // Assuming userService.createUser(loginRequest) returns a boolean indicating success or failure
       // boolean isSuccess = userService.createUser(loginRequest);
        boolean isSuccess = true;
        LoginResponse response = isSuccess
                ? LoginResponse.newBuilder().setStatus(Status.SUCCESS).setMessage("User created successfully.").build()
                : LoginResponse.newBuilder().setStatus(Status.FAILURE).setMessage("Failed to create user.").build();

        return ResponseEntity.ok(response);
    }
}
