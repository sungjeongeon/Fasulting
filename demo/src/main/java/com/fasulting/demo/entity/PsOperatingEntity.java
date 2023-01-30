package com.fasulting.demo.entity;

import com.fasulting.demo.entity.compositeId.PsMainSubId;
import com.fasulting.demo.entity.compositeId.PsOperatingId;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

// 필요한 테이블일깡
@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@IdClass(PsOperatingId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "ps_operating")
public class PsOperatingEntity extends BaseEntity implements Serializable {

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "ps_seq")
	private PsEntity ps;

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "cal_seq")
	private OperatingCalEntity operatingCal;

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "time_seq")
	private OperatingTimeEntity operatingTime;

	@Builder
	public PsOperatingEntity(PsEntity ps, OperatingCalEntity operatingCal, OperatingTimeEntity operatingTime) {
		this.ps = ps;
		this.operatingTime = operatingTime;
		this.operatingTime = operatingTime;
	}
}
