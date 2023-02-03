package com.fasulting.entity.user;

import javax.persistence.*;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.TokenEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "user_token")
public class UserTokenEntity extends BaseEntity implements Serializable {

	@Id
   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "user_seq")
	private UserEntity user;

	@Id
   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "token_seq")
	private TokenEntity token;


}
