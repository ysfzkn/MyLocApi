package com.example.mylocapi.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cards")
public class Card
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "balance", nullable = false)
    private long balance;

    // A user can only have one card
    @OneToOne(mappedBy = "card")
    private User user;
}
