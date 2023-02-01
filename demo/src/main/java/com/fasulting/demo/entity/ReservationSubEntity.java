package com.fasulting.demo.entity;

import com.fasulting.demo.entity.compositeId.ReservationSubId;
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
@Table (name = "reservation_sub")
@IdClass(ReservationSubId.class)
public class ReservationSubEntity extends BaseEntity implements Serializable{

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "resrvation_seq")
	private ReservationEntity reservation;

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "sub_seq")
	private SubCategoryEntity subCategory;


}
