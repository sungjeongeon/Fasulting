package com.fasulting.entity.token;

import com.fasulting.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "token")
public class TokenEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	@Column(name = "refresh_token")
	private String refreshToken;

	@OneToOne(mappedBy = "token")
	private UserTokenEntity userToken;

	@Builder
	public TokenEntity(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
