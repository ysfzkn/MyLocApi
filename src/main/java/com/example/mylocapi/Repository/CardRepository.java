package com.example.mylocapi.Repository;

import com.example.mylocapi.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>
{
    // to do......
    @Query("select c.balance as balance " +
            "from Card c " +
            "where c.user.id = :userId")
    Optional<Card> findCardOfUser(@Param("userId") Long userId);
}
