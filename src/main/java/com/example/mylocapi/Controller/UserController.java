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

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("register") //user/register
    public ResponseEntity<?> signUp(@RequestBody User user)
    {
        if (userService.findByUsername(user.getUsername()).isPresent())
        {
            System.out.println("User is present!");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        System.out.println("Controller working!");
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody User user) throws Exception {

        Authentication authObject = null;
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authObject);
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("Invalid credentials");
        }

        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
    /*
    @PostMapping("sign-in") //user/sign-in
    public ResponseEntity<?> singIn(@RequestBody User user)
    {
        return new ResponseEntity<>(authService.signInReturnJWT(user), HttpStatus.OK);
    }
    */

}

