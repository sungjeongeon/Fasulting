package com.fasulting.repository.token;

import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.token.PsTokenEntity;
import com.fasulting.entity.token.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PsTokenRepository extends JpaRepository<PsTokenEntity, Long> {

    Optional<PsTokenEntity> findByToken(TokenEntity token);

    Optional<PsTokenEntity> findByPs(PsEntity ps);
}
