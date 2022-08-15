package com.example.mylocapi.Controller;

import com.example.mylocapi.Model.User;
import com.example.mylocapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("register") //user/register
    public ResponseEntity<?> register(@RequestBody User user)
    {
        if (userService.findByUsername(user.getUsername()).isPresent())
        {
            System.out.println("User is present!");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(userService.saveOneUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)
    {
        if (userService.findByUsername(user.getUsername()).isPresent())
        {
            System.out.println("User found!");
            if (userService.passwordValidation(user))
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(userService.saveOneUser(user), HttpStatus.UNAUTHORIZED);
    }


}

