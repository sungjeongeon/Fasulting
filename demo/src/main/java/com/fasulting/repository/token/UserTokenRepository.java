package com.fasulting.repository.token;

import com.fasulting.entity.compositeId.UserTokenId;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.entity.user.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, UserTokenId> {

    Optional<UserTokenEntity> findByUser(UserEntity user);
}
