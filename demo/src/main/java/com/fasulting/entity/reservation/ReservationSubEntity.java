package com.fasulting.entity.reservation;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.category.SubCategoryEntity;
import com.fasulting.entity.compositeId.ReservationSubId;
import lombok.*;
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
	@JoinColumn(referencedColumnName = "seq", name = "reservation_seq")
	private ReservationEntity reservation;

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "sub_seq")
	private SubCategoryEntity subCategory;

	@Builder
	public ReservationSubEntity(ReservationEntity reservation, SubCategoryEntity subCategory) {
		this.reservation = reservation;
		this.subCategory = subCategory;
	}
}
