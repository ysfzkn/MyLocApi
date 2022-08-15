package com.example.mylocapi.Security;

import com.example.mylocapi.Model.User;
import com.example.mylocapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> user = userRepository.findByUsername(username);
        return JwtUsersDetails.create(user.get());
    }

    public UserDetails loadUserById(Long id)
    {
        Optional<User> user = userRepository.findById(id);
        return JwtUsersDetails.create(user.get());
    }
}
