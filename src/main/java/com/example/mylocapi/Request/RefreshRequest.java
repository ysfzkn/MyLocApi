package com.example.mylocapi.Request;

import lombok.Data;

@Data
public class RefreshRequest
{
    Long userId;
    String refreshToken;
}