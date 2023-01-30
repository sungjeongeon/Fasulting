package com.fasulting.demo.entity;

import javax.persistence.*;

import com.fasulting.demo.ps.ps.dto.reqDto.PsSeqReq;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;

@Entity
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "ps")
public class PsEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	@Column(name = "email")
	private String email;

   	@Column(name = "password")
	private String password;

   	@Column(name = "name")
	private String name;

   	@Column(name = "address")
	private String address;

   	@Column(name = "zipcode")
	private String zipcode;

   	@Column(name = "registration")
	private String registration;

	/**서버에 저장한 이미지 경로*/
   	@Column(name = "registration_img")
	private String registrationImg;

   	@Column(name = "number")
	private String number;

   	@Column(name = "director")
	private String director;

   	@Column(name = "homepage")
	private String homepage;

	/**서버에 저장할 병원 프로필 사진 경로*/
   	@Column(name = "profile_img")
	private String profileImg;

   	@Column(name = "intro")
	private String intro;

	/**가입 승인 여부에 따른 Y / N*/
   	@Column(name = "confirm_yn")
	private String confirmYn;

   	@Column(name = "confirm_date")
	private LocalDateTime confirmDate;

   	@Column(name = "confirm_by")
	private String confirmBy;

   	@Column(name = "del_date")
	private LocalDateTime delDate;

   	@Column(name = "del_by")
	private String delBy;

	/**병원 계정의 삭제는 탈퇴 기능*/
   	@Column(name = "del_yn")
	private String delYn;

	@Builder
	public PsEntity(String email, String password, String name, String address, String zipcode, String registration, String registrationImg, String number, String director, String homepage, String profileImg, String intro, String confirmYn, LocalDateTime confirmDate, String confirmBy, LocalDateTime delDate, String delBy, String delYn) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.zipcode = zipcode;
		this.registration = registration;
		this.registrationImg = registrationImg;
		this.number = number;
		this.director = director;
		this.homepage = homepage;
		this.profileImg = profileImg;
		this.intro = intro;
		this.confirmYn = confirmYn;
		this.confirmDate = confirmDate;
		this.confirmBy = confirmBy;
		this.delDate = delDate;
		this.delBy = delBy;
		this.delYn = delYn;
	}

	public void updatePsEntity(PsSeqReq psInfo, String profileImg) {
//		this.profileImg = psInfo.getProfileImg();
		this.number = psInfo.getNumber();
		this.intro = psInfo.getIntro();
		this.homepage = psInfo.getHomepage();
		this.profileImg = profileImg;
		// doctor
		// main, sub
	}

	public void withdrawlPs(String delYn, String delBy, LocalDateTime delDate) {
		this.delYn = delYn;
		this.delBy = delBy;
		this.delDate = delDate;
	}

	public void resetPassword(String password) {
		this.password = password;
	}
}
