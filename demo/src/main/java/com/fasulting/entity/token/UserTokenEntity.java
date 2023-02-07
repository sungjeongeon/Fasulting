package com.fasulting.entity.token;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Builder
@Getter
@Setter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "user_token")
public class UserTokenEntity extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;


   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "user_seq")
	private UserEntity user;


   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "token_seq")
	private TokenEntity token;

	@Builder
	public UserTokenEntity(UserEntity user, TokenEntity token) {
		this.user = user;
		this.token = token;
	}

	public void updateToken(TokenEntity token){
		this.token = token;
	}


	@Override
	public String toString() {
		return "UserTokenEntity{" +
				"seq=" + seq +
				'}';
	}

}
