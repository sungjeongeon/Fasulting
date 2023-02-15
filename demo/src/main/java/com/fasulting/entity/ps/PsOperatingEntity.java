package com.fasulting.entity.ps;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.calendar.OperatingCalEntity;
import com.fasulting.entity.calendar.TimeEntity;
import com.fasulting.entity.compositeId.PsOperatingId;
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
	private TimeEntity time;

	@Builder
	public PsOperatingEntity(PsEntity ps, OperatingCalEntity operatingCal, TimeEntity time) {
		this.ps = ps;
		this.operatingCal = operatingCal;
		this.time = time;
	}
}
