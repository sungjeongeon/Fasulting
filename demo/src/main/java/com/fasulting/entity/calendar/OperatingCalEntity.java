package com.fasulting.entity.calendar;

import com.fasulting.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "operating_cal")
public class OperatingCalEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

	@Column(name="date", updatable = false)
	private LocalDateTime date;
   	@Column(name = "year")
	private Integer year;

   	@Column(name = "month")
	private Integer month;

   	@Column(name = "day")
	private Integer day;

	/** 1: 일, ~ 7:토 */
   	@Column(name = "day_of_week")
	private Integer dayOfWeek;

}
