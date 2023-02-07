package com.fasulting.repository.token;

import com.fasulting.entity.token.TokenEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByRefreshToken(String refreshToken);

//    @Query("SELECT t " + "FROM TokenEntity t " + "WHERE t.refreshToken = :rToken")
//    Optional<TokenEntity> findByRefreshTokenForFilter(@Param("rToken") String refreshToken);
}
