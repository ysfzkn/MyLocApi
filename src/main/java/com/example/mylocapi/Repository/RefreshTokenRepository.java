package com.example.mylocapi.Repository;

import com.example.mylocapi.Model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>
{
    RefreshToken findByUserId(Long userId);
}