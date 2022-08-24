package com.example.mylocapi.Service;

import com.example.mylocapi.Model.User;
import com.example.mylocapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean passwordValidation(User user)
    {
        if (user.equals(userRepository.findByUsername(user.getUsername())))
        {
            return true;
        }

        return false;
    }

    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public User saveOneUser(User newUser)
    {
        newUser.setCardBalance(500); // Default balance 500TL
        return userRepository.save(newUser);
    }

    @Override
    public User getOneUserById(Long userId)
    {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User updateOneUser(Long userId, User newUser)
    {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent())
        {
            User foundUser = user.get();

            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());

            userRepository.save(foundUser);

            return foundUser;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void deleteById(Long userId)
    {
        try
        {
            userRepository.deleteById(userId);
        }
        catch(EmptyResultDataAccessException e)
        {
            System.out.println("User "+userId+" doesn't exist");
        }
    }

    @Override
    public Optional<User> getOneUserByUserName(String userName)
    {
        return userRepository.findByUsername(userName);
    }


    @Override
    public Long getCardBalance(Long user_id)
    {
        try
        {
            User user = userRepository.findById(user_id).get();
            long currentBalance = user.getCardBalance();
            return currentBalance;
        }
        catch(EmptyResultDataAccessException e)
        {
            System.out.println("User "+user_id+" doesn't exist");
            return null;
        }
    }

    @Override
    public void updateCardBalance(Long user_id, long price)
    {
        try
        {
            User user = userRepository.findById(user_id).get();
            long currentBalance = user.getCardBalance();
            user.setCardBalance(currentBalance-price);
            System.out.println("User "+user_id+" new balance : "+user.getCardBalance());
        }
        catch(EmptyResultDataAccessException e)
        {
            System.out.println("User "+user_id+" doesn't exist");
        }
    }

    @Override
    public void addToCard(Long user_id)
    {
        try
        {
            long amount = 50;
            User user = userRepository.findById(user_id).get();
            long currentBalance = user.getCardBalance();

            user.setCardBalance(currentBalance+amount);

            System.out.println("User "+user_id+" new balance : "+user.getCardBalance());
        }
        catch(EmptyResultDataAccessException e)
        {
            System.out.println("User "+user_id+" doesn't exist");
        }
    }
}
