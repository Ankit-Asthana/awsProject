package com.example.AMS.service;

//import com.example.AMS.dto.LoginDTO;
import com.example.AMS.entity.Users;
import com.example.AMS.repository.UserDao;
import com.example.AMS.security.AuthenticationRequest;
import com.example.AMS.security.AuthenticationResponse;
import com.example.AMS.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDao repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
//    public AuthenticationResponse register(RegisterRequest request) {
//        var users= User.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .build();
//        repository.save(user);
//        var jwtToken=jwtService.generateToken(users);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }

    public AuthenticationResponse authenticate(@NonNull AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user= repository.findByEmail(request.getEmail());
        //.orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


//    @PostMapping("/signup")
//    public ResponseEntity<String> createUser(@RequestBody Users user) {
//        // Set createdDate to the current date
//        user.setCreatedDate(new Date());
//        // Set status to active
//        user.setStatus(true);
//        // Save the user to the database
//        repository.save(user);
//        // Return a success response
//        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
//    }
}
