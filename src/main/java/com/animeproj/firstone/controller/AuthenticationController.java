package com.animeproj.firstone.controller;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.animeproj.firstone.auth.AuthenticationResponse;
import com.animeproj.firstone.dto.loginDto;
import com.animeproj.firstone.dto.registreDto;

import com.animeproj.firstone.services.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody registreDto request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody loginDto request) {

        System.out.println(request);

        AuthenticationResponse response = userService.authenticate(request);

        System.out.println(response);

        return ResponseEntity.ok(response);
    }
    

}
