package com.example.mylocapi.request;

import lombok.Data;

@Data
public class RefreshRequest
{
    Long userId;
    String refreshToken;
}