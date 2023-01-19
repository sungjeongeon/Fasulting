package com.fasulting.demo.customer.user.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

// 새로 추가할 때 save
// 아니면 그냥 setter

@Entity
@Getter
@Setter
@DynamicInsert // 변경된 필드만 적용
@DynamicUpdate // 변경된 필드만 적용
@NoArgsConstructor
@ToString
@Table(name = "user")
public class User {

    public User(String userEmail, String userPassword, String userBirth, String userGender, String userName, String userPhone, String userNation, String userNationCode) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userNation = userNation;
        this.userNationCode = userNationCode;
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_birth")
    private String userBirth;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_phone")
    private String userPhone;


    // 수정
    @Column(name = "user_validation")
    private String userValidation;


    @Column(name = "user_regist_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private String userRegistTime;

    @Column(name = "user_nation")
    private String userNation;

    @Column(name = "user_nation_code")
    private String userNationCode;

    // 가입 날짜?


    // 외래키 매핑
    // @ManyToOne
    // @JoinColumn(name = "manager_code", updatable = false, insertable = false)
    // @JsonBackReference
    // private Example example;

}
