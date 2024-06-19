package com.alex.recipe.controller;

import com.alex.recipe.config.JwtProvider;
import com.alex.recipe.model.User;
import com.alex.recipe.repository.UserRepo;
import com.alex.recipe.request.LoginRequest;
import com.alex.recipe.response.AuthResponse;
import com.alex.recipe.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();

        Optional<User> userExist = userRepo.findByEmail(email);
        if (userExist.isPresent()) throw new Exception("Email is already used");

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setPassword(passwordEncoder.encode(password));
        userRepo.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("Sign-up success");

        return res;
    }

    @PostMapping("/signin")
    public AuthResponse signinHandler(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("Sign-in success");

        return res;
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) throw new BadCredentialsException("User not found");
        if (!passwordEncoder.matches(password, userDetails.getPassword())) throw new BadCredentialsException("Invalid password");

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
