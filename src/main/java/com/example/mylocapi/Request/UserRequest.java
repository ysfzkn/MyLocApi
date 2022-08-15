package com.example.mylocapi.request;

import lombok.Data;

@Data
public class UserRequest
{
    String username;
    String name;
    String password;

    public String getUsername()
    {
        return username;
    }
    public String getName()
    {
        return name;
    }


    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}