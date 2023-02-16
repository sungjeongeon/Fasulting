package com.fasulting.entity.doctor;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.category.MainCategoryEntity;
import com.fasulting.entity.compositeId.DoctorMainId;
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
@IdClass(DoctorMainId.class)
@Table (name = "doctor_main")
public class DoctorMainEntity extends BaseEntity implements Serializable{

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "doctor_seq")
	private DoctorEntity doctor;

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "main_seq")
	private MainCategoryEntity mainCategory;

	@Builder
	public DoctorMainEntity(DoctorEntity doctor, MainCategoryEntity mainCategory) {
		this.doctor = doctor;
		this.mainCategory = mainCategory;
	}
}
