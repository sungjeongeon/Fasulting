package com.fasulting.demo.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "doctor")
public class DoctorEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	/** FK setting */
	// @ManyToOne
	// @OneToMany
	// @JsonBackReference
	// @JoinColumn(name = "", updatable = false, insertable = false)
	private Long psSeq;

   	@Column(name = "name")
	private String name;

	/**전문의 사진 경로*/
   	@Column(name = "img")
	private String img;


}
