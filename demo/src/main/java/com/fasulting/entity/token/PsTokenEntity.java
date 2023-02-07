package com.fasulting.entity.token;

import javax.persistence.*;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.ps.PsEntity;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "ps_token")
public class PsTokenEntity extends BaseEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "ps_seq")
	private PsEntity ps;

   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "token_seq")
	private TokenEntity token;

	@Builder
	public PsTokenEntity(PsEntity ps, TokenEntity token) {
		this.ps = ps;
		this.token = token;
	}

	public void updateToken(TokenEntity token){
		this.token = token;
	}

	@Override
	public String toString() {
		return "PsTokenEntity{" +
				"seq=" + seq +
				'}';
	}

}
