package com.fasulting.demo.customer.user.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(false)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    // String userEmail, String userPassword, Date userBirth, String userGender, String userName, String userPhone, String userNation, NationCode")
    @Test
    public void testFindUserByUserEmail() {
        User user = new User("mingazetbb@naver.com", "1234", "19980613", "M", "min", "010", "korea", "+82");
        User savedUser = userRepository.save(user);

        Optional<User> findUser = userRepository.findUserByUserEmail("mingmingming@g");

        System.out.println(findUser.get().getUserId());

    }

    // 회원 수정
    @Test
    public void testEditUserInfo() {

        int id = 1;

        if(userRepository.findById(id).isPresent()) {
            User user = new User();
            user = userRepository.findById(id).get();

            user.setUserName("태희");
            user.setUserPhone("010111111");

        }

    }

    // 비밀번호 재설정
    @Test
    public void testResetPassword() {

        int id = 1;

        if(userRepository.findById(id).isPresent()) {
            User user = new User();
            user = userRepository.findById(id).get();

            user.setUserPassword("88888");

        }

    }

    // 회원 정보 조회
    @Test
    public void testGetUserInfo() {

        int id = 1;

        if(userRepository.findById(id).isPresent()) {
            User user = new User();
            user = userRepository.findById(id).get();

            System.out.println(user.toString());
        }

    }

    // 회원 탈퇴
    @Test
    public void testUserWithdrawl() {
        int id = 1;
        if(userRepository.findById(id).isPresent()) {
            User user = new User();
            user = userRepository.findById(id).get();

            user.setUserValidation("N");

            System.out.println(user.getUserValidation());
            System.out.println(userRepository.getById(2).getUserValidation());
        }
    }

    // 비밀 번호 확인
    @Test
    public void testCheckPass() {
        int id = 1;
        if(userRepository.findById(id).isPresent()) {
            User user = new User();
            user = userRepository.findById(id).get();

            String userPass = user.getUserPassword();

            Assertions.assertThat(userPass).isEqualTo("88888");
        }
    }

}