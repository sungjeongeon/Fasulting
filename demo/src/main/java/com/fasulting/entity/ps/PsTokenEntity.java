package com.fasulting.entity.ps;

import javax.persistence.*;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.token.TokenEntity;
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
@Table (name = "ps_token")
public class PsTokenEntity extends BaseEntity implements Serializable{

	@Id
   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "ps_seq")
	private PsEntity ps;

	@Id
   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "token_seq")
	private TokenEntity token;


}
