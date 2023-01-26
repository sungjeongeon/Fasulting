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
@Table (name = "sub_category")
public class SubCategoryEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	/** FK setting */
	// @ManyToOne
	// @OneToMany
	// @JsonBackReference
	// @JoinColumn(name = "", updatable = false, insertable = false)
	private Long mainSeq;

	/**- 쌍커풀, 눈매교정, 트임, 눈밑성형, 재수술
- 콧대, 코끝 성형, 콧불 축소, 재수술
- 입꼬리, 입술축소술, 입술확대수술
- 사각턱, 광대, 턱 끝, 이마, 양약, 재수술
- 절개, 비절개
- 여드름 치료, 화상치료, 흉터 치료*/
   	@Column(name = "name")
	private String name;


}
