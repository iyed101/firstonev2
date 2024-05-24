package com.animeproj.firstone.services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.animeproj.firstone.auth.AuthenticationResponse;
import com.animeproj.firstone.config.jwt.JwtService;
import com.animeproj.firstone.dto.loginDto;
import com.animeproj.firstone.dto.registreDto;
import com.animeproj.firstone.models.User;
import com.animeproj.firstone.repo.UserRepo;
import com.animeproj.firstone.models.Role;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder,
            JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found: " + username));
    }

    public AuthenticationResponse register(registreDto request) {

        var user = User.builder()
                .nom(request.getNom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();

        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(loginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public List<User> getAllRecruiters() {
        return userRepo.findAll();
    }

    public User getRecruiterById(Integer id) {
        return userRepo.findById(id).orElseThrow();
    }

    public User createRecruiter(User recruiter) {
        return userRepo.save(recruiter);
    }

    public User updateRecruiter(User recruiter) {
        return userRepo.save(recruiter);
    }

    public void deleteRecruiter(Integer id) {
        userRepo.deleteById(id);
    }
    
}
