package com.example.mylocapi.response;


import com.example.mylocapi.Model.User;
import lombok.Data;

@Data
public class UserResponse
{
    Long id;
    String userName;

    public UserResponse(User user)
    {
        this.id = user.getId();

        this.userName = user.getUsername();
    }
}