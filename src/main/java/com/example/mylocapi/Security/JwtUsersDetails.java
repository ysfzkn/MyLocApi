package com.example.mylocapi.Security;


import com.example.mylocapi.Model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtUsersDetails implements UserDetails
{

    public Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUsersDetails(Long id, String username, String password,
                           Collection<? extends GrantedAuthority> authorities)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    public static JwtUsersDetails create(User user)
    {
        List<GrantedAuthority>authoritiesList=new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("users"));

        return new JwtUsersDetails(user.getId(),
                user.getUsername(),
                user.getPassword(),
                authoritiesList);
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}