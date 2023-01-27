package com.fasulting.demo.entity;

import javax.persistence.*;
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
@Table (name = "role")
public class RoleEntity extends BaseEntity implements Serializable {

	@Id
   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "user_seq")
	private UserEntity userSeq;

	/**admin or user*/
   	@Column(name = "authority")
	private String authority;


}
