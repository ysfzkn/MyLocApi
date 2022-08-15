package com.example.mylocapi.Service;

import com.example.mylocapi.Model.Card;
import com.example.mylocapi.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService
{
    @Autowired
    CardRepository cardRepository;

    @Override
    public Optional<Card> findCardByUserId(Long id)
    {
        return cardRepository.findCardOfUser(id);
    }
}
