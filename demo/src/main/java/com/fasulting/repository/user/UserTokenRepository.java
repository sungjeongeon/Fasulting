package com.fasulting.repository.user;

import com.fasulting.entity.user.UserTokenEntity;
import com.fasulting.entity.compositeId.UserTokenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserTokenEntity, UserTokenId> {
}
