package com.fasulting.demo.customer.user.db.repository;

import com.fasulting.demo.customer.user.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DB에 접근하여 쿼리 수행하는 등의 역할하는 Interface

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
