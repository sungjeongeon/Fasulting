package com.fasulting.demo.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "user")
public class UserEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	@Column(name = "email")
	private String email;

   	@Column(name = "password")
	private String password;

   	@Column(name = "birth")
	private String birth;

	/**M or F*/
   	@Column(name = "gender")
	private String gender;

   	@Column(name = "number")
	private String number;

   	@Column(name = "nation")
	private String nation;

   	@Column(name = "nation_code")
	private String nationCode;

   	@Column(name = "name")
	private String name;

   	@Column(name = "del_date")
	private LocalDateTime delDate;

   	@Column(name = "del_by")
	private String delBy;

   	@Column(name = "del_yn")
	private String delYn;

	@Builder
	public UserEntity(String email, String password, String birth, String gender, String number, String nation, String nationCode, String name, LocalDateTime delDate, String delBy, String delYn) {
		this.email = email;
		this.password = password;
		this.birth = birth;
		this.gender = gender;
		this.number = number;
		this.nation = nation;
		this.nationCode = nationCode;
		this.name = name;
		this.delDate = delDate;
		this.delBy = delBy;
		this.delYn = delYn;
	}

	public void updateUserEntity(String name, String number) {
		this.name = name;
		this.number = number;
	}

	public void withdrawlUser(String delYn, String delBy, LocalDateTime delDate) {
		this.delYn = delYn;
		this.delBy = delBy;
		this.delDate = delDate;
	}

	public void resetPassword(String password) {
		this.password = password;
	}
}
