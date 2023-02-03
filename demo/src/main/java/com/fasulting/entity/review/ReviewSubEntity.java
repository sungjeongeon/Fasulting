package com.fasulting.entity.review;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.category.SubCategoryEntity;
import com.fasulting.entity.compositeId.ReviewSubId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(ReviewSubId.class)
@Table (name = "review_sub")
public class ReviewSubEntity extends BaseEntity implements Serializable{

	@Id
   	/** FK setting */
	@ManyToOne
	@JoinColumn(referencedColumnName = "seq", name = "review_seq")
	private ReviewEntity review;

	@Id
   	/** FK setting */
	@ManyToOne
	@JoinColumn(referencedColumnName = "seq", name = "sub_seq")
	private SubCategoryEntity subCategory;

}
