package com.fasulting.repository.token;

import com.fasulting.entity.token.TokenEntity;
import com.fasulting.entity.token.UserTokenEntity;
import com.fasulting.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {

    Optional<UserTokenEntity> findByUser(UserEntity user);

    Optional<UserTokenEntity> findByToken(TokenEntity token);

    void deleteByUser(UserEntity user);
}
