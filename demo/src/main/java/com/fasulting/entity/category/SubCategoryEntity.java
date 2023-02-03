package com.fasulting.entity.category;

import com.fasulting.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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
	@ManyToOne
	@JoinColumn(referencedColumnName = "seq", name = "main_seq")
	private MainCategoryEntity mainCategory;

	/**- 쌍커풀, 눈매교정, 트임, 눈밑성형, 재수술
- 콧대, 코끝 성형, 콧불 축소, 재수술
- 입꼬리, 입술축소술, 입술확대수술
- 사각턱, 광대, 턱 끝, 이마, 양약, 재수술
- 절개, 비절개
- 여드름 치료, 화상치료, 흉터 치료*/
   	@Column(name = "name")
	private String name;


}
