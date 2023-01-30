package com.fasulting.demo.entity;

import javax.persistence.*;

import com.fasulting.demo.entity.compositeId.PsMainId;
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
@IdClass(PsMainId.class)
@Table (name = "ps_main")
public class PsMainEntity extends BaseEntity implements Serializable{

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

	@Builder
	public PsMainEntity(PsEntity ps, MainCategoryEntity mainCategory) {
		this.ps = ps;
		this.mainCategory = mainCategory;
	}
}
