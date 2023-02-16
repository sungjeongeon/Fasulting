package com.fasulting.repository.role;

import com.fasulting.entity.user.RoleEntity;
import com.fasulting.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByUser(UserEntity user);
}