package com.example.AMS.controller;

import com.example.AMS.repository.UserDao;
import com.example.AMS.security.AuthenticationRequest;
import com.example.AMS.security.AuthenticationResponse;
import com.example.AMS.security.JwtService;
import com.example.AMS.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final UserDao userDao;

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final UserDetailsService userDetailsService;

    //private final UserService userService;
//    @PostMapping("/signup")
//    public ResponseEntity<AuthenticationResponse> signup(@RequestBody RegisterRequest request){
//        return ResponseEntity.ok(authenticationService.register(request));
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}