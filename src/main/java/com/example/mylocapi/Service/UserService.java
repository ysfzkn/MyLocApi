package com.example.mylocapi.Service;

import com.example.mylocapi.Model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    Optional<User> findByUsername(String username);

    boolean passwordValidation(User user);

    List<User> getAllUsers();

    User saveOneUser(User user);

    User getOneUserById(Long userId);

    User updateOneUser(Long userId, User newUser);

    void deleteById(Long userId);

    Optional<User> getOneUserByUserName(String userName);

    void updateCardBalance(Long user_id, long price);

    void addToCard(Long user_id);
}
