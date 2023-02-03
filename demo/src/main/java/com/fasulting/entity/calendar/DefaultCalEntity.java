package com.fasulting.entity.calendar;

import com.fasulting.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

// 필요한 테이블일깡
@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "default_cal")
public class DefaultCalEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

	/** 1: 일, ~ 7:토 */
	@Column(name = "day_of_week")
	private Integer dayOfWeek;

	@Builder
	public DefaultCalEntity(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
}
