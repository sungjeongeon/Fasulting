package com.fasulting.demo.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "operating")
public class OperatingEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "ps_seq")
	private PsEntity ps;

   	@Column(name = "year")
	private Integer year;

   	@Column(name = "month")
	private Integer month;

   	@Column(name = "day")
	private Integer day;

	/**1 : 월
2 : 화
3 : 수
4 : 목
5 : 금
6 : 토
7 : 일*/
   	@Column(name = "day_of_week")
	private Integer dayOfWeek;

	/**0 ~ 24 시간, 30분 단위 1 ~ 48*/
   	@Column(name = "hour")
	private Integer hour;


}
