package com.fasulting.entity.doctor;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.ps.PsEntity;
import lombok.*;
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
@Table (name = "doctor")
public class DoctorEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "ps_seq")
	private PsEntity ps;

   	@Column(name = "name")
	private String name;

	/**전문의 사진 경로*/
   	@Column(name = "img_path")
	private String imgPath;

	@Column(name = "img_origin")
	private String imgOrigin;

	@Builder
	public DoctorEntity(PsEntity ps, String name, String imgPath, String imgOrigin) {
		this.ps = ps;
		this.name = name;
		this.imgPath = imgPath;
		this.imgOrigin = imgOrigin;
	}
}
