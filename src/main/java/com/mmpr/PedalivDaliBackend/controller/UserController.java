package com.mmpr.PedalivDaliBackend.controller;

import com.mmpr.PedalivDaliBackend.model.User;
import com.mmpr.PedalivDaliBackend.payload.LoginSignupRequest;
import com.mmpr.PedalivDaliBackend.repository.UserRepository;
import com.mmpr.PedalivDaliBackend.service.CurrentUser;
import com.mmpr.PedalivDaliBackend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginSignupRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody LoginSignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException();
        }
        if (userRepository.existsByName(signUpRequest.getLogin())) {
            throw new RuntimeException();
        }

        User user = new User();
        user.setName(signUpRequest.getLogin());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public CustomUserDetailsService.UserPrincipal getCurrentUser(@CurrentUser CustomUserDetailsService.UserPrincipal userPrincipal) {
        return userPrincipal;
    }
}
