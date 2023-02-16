package com.fasulting.entity.ps;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.calendar.DefaultCalEntity;
import com.fasulting.entity.calendar.TimeEntity;
import com.fasulting.entity.compositeId.PsDefaultId;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "ps_default")
@IdClass(PsDefaultId.class)
public class PsDefaultEntity extends BaseEntity implements Serializable {

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "ps_seq")
	private PsEntity ps;

	@Id
	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "cal_seq")
	private DefaultCalEntity defaultCal;

	@Id
   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "time_seq")
	private TimeEntity time;


	@Builder
	public PsDefaultEntity(PsEntity ps, DefaultCalEntity defaultCal, TimeEntity time) {
		this.ps = ps;
		this.defaultCal = defaultCal;
		this.time = time;
	}

}
