package com.fasulting.entity.user;

import com.fasulting.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "role")
public class RoleEntity extends BaseEntity {

	@Id
	@Column(name = "user_seq")
	private Long userSeq;

	@MapsId
   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "user_seq")
	private UserEntity user;

	/**admin or user*/
   	@Column(name = "authority")
	private String authority;


	@Builder
	public RoleEntity(UserEntity user, String authority) {
		this.user = user;
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "RoleEntity{" +
				"userSeq=" + userSeq +
				", authority='" + authority + '\'' +
				'}';
	}
}
