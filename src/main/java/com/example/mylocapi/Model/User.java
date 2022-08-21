package com.example.mylocapi.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username",unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "card_balance", nullable = false, updatable = true)
    private long cardBalance;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user")
    private Set<LocationHistory> locationHistory;
}
