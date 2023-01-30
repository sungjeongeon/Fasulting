package com.fasulting.demo.entity;

import javax.persistence.*;

import com.fasulting.demo.entity.compositeId.PsMainSubId;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

// 필요한 테이블일깡
@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@IdClass(PsMainSubId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "ps_main_sub")
public class PsMainSubEntity extends BaseEntity implements Serializable {

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "ps_seq")
	private PsEntity ps;

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "main_seq")
	private MainCategoryEntity mainCategory;

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "sub_seq")
	private SubCategoryEntity subCategory;

	@Builder
	public PsMainSubEntity(PsEntity ps, MainCategoryEntity mainCategory, SubCategoryEntity subCategory) {
		this.ps = ps;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
	}
}
