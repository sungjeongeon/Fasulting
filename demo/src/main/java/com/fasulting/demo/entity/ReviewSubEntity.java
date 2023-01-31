package com.fasulting.demo.entity;

import javax.persistence.*;

import com.fasulting.demo.entity.compositeId.PsMainSubId;
import com.fasulting.demo.entity.compositeId.ReviewSubId;
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
