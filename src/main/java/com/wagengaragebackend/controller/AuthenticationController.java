package com.wagengaragebackend.controller;

import com.wagengaragebackend.dto.payload.AuthenticationRequest;
import com.wagengaragebackend.dto.payload.AuthenticationResponse;
import com.wagengaragebackend.service.UserAuthenticationService;
import com.wagengaragebackend.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {


    UserAuthenticationService userAuthenticationService;

    @Autowired
    public AuthenticationController(UserAuthenticationService userAuthenticationService){
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        AuthenticationResponse authenticationResponse = userAuthenticationService.authenticateUser(username, password);

        return ResponseEntity.ok(authenticationResponse);
    }




}