package com.example.mylocapi.Service;

import com.example.mylocapi.Model.Card;

import java.util.Optional;
import java.util.OptionalInt;

public interface CardService
{
    Optional<Card> findCardByUserId(Long id);
}
