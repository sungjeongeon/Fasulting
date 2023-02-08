package com.fasulting.entity.ps;

import com.fasulting.common.RoleType;
import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.token.PsTokenEntity;
import com.fasulting.entity.token.UserTokenEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "ps")
public class PsEntity extends BaseEntity implements UserDetails {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

	@OneToOne(mappedBy = "ps")
	private PsTokenEntity psToken;

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
   	@Column(name = "reg_img_path")
	private String regImgPath;

	@Column(name = "reg_img_origin")
	private String regImgOrigin;

   	@Column(name = "number")
	private String number;

   	@Column(name = "director")
	private String director;

   	@Column(name = "homepage")
	private String homepage;

	/**서버에 저장할 병원 프로필 사진 경로*/
   	@Column(name = "profile_img_path")
	private String profileImgPath;

	@Column(name = "profile_img_origin")
	private String profileImgOrigin;

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
	public PsEntity(String email, String password, String name, String address, String zipcode, String registration, String regImgPath, String regImgOrigin, String number, String director, String homepage, String profileImgPath, String profileImgOrigin, String intro) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.zipcode = zipcode;
		this.registration = registration;
		this.regImgPath = regImgPath;
		this.regImgOrigin = regImgOrigin;
		this.number = number;
		this.director = director;
		this.homepage = homepage;
		this.profileImgPath = profileImgPath;
		this.profileImgOrigin = profileImgOrigin;
		this.intro = intro;
		this.confirmYn = "N";
		this.delYn = "N";
	}

	public void updateAddress(String address) {
		this.address = address;
	}

	public void updateIntro(String intro) {
		this.intro = intro;
	}

	public void updateNumber(String number) {
		this.number = number;
	}

	public void updateHomepage(String homepage) {
		this.homepage = homepage;
	}


	public void updateByWithdrawal(String delBy, LocalDateTime delDate) {
		this.delYn = "Y";
		this.delBy = delBy;
		this.delDate = delDate;
	}

	public void resetPassword(String password) {
		this.password = password;
	}

    public void updateByConfirm(String confirmBy, LocalDateTime confirmDate) {
		   this.confirmYn = "Y";
		   this.confirmBy = confirmBy;
		   this.confirmDate = confirmDate;

    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = RoleType.PS;
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
