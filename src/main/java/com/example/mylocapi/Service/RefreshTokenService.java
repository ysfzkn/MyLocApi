package com.example.mylocapi.Service;


import com.example.mylocapi.Model.RefreshToken;
import com.example.mylocapi.Model.User;
import com.example.mylocapi.Repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("604800")
    Long expireSeconds;

    private RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository)
    {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String createRefreshToken(User user)
    {
        RefreshToken token = refreshTokenRepository.findByUserId(user.getId());

        if(token == null)
        {
            token =	new RefreshToken();
            token.setUser(user);
        }

        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        refreshTokenRepository.save(token);

        return token.getToken();
    }

    public boolean isRefreshExpired(RefreshToken token)
    {
        return token.getExpiryDate().before(new Date());
    }

    public RefreshToken getByUser(Long userId)
    {
        return refreshTokenRepository.findByUserId(userId);
    }

}