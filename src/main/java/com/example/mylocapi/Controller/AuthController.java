package com.example.mylocapi.Controller;


import com.example.mylocapi.Model.User;
import com.example.mylocapi.Security.JwtTokenProvider;
import com.example.mylocapi.Request.UserRequest;
import com.example.mylocapi.Response.AuthResponse;
import com.example.mylocapi.Service.RefreshTokenService;
import com.example.mylocapi.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthController
{

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider,
                          RefreshTokenService refreshTokenService)
    {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
    }

    // Refactoring needed
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserRequest loginRequest)
    {
        AuthResponse authResponse = new AuthResponse();

        try
        {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword());
            Authentication auth = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(auth);

            String jwtToken = jwtTokenProvider.generateJwtToken(auth);
            Optional<User> user = userService.getOneUserByUserName(loginRequest.getUsername());

            authResponse.setMessage("Login Successful !");
            authResponse.setAccessToken("Bearer " + jwtToken);
            authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user.get()));
            authResponse.setUserId(user.get().getId());

            return new ResponseEntity<>(authResponse, HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            authResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(authResponse, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest)
    {
        AuthResponse authResponse = new AuthResponse();

        if(userService.getOneUserByUserName(registerRequest.getUsername()).isPresent())
        {
            authResponse.setMessage("Username already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setName(registerRequest.getName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User userRes = userService.saveOneUser(user);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getUsername(), registerRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);

        authResponse.setMessage("User successfully registered.");
        authResponse.setAccessToken(jwtToken);
        authResponse.setUserId(userRes.getId());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }




}