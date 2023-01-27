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
@Table (name = "default_operating")
public class DefaultOperatingEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "ps_seq")
	private PsEntity ps;

	/**1 : 월
2 : 화
3 : 수
4 : 목
5 : 금
6 : 토
7 : 일*/
   	@Column(name = "day_of_week")
	private Integer dayOfWeek;

	/**1 ~ 48 로 표현(0시부터 24시까지 30분 단위)*/
   	@Column(name = "am_start")
	private Integer amStart;

	/**1 ~ 48 로 표현(0시부터 24시까지 30분 단위)*/
   	@Column(name = "am_end")
	private Integer amEnd;

	/**1 ~ 48 로 표현(0시부터 24시까지 30분 단위)*/
   	@Column(name = "pm_start")
	private Integer pmStart;

	/**1 ~ 48 로 표현(0시부터 24시까지 30분 단위)*/
   	@Column(name = "pm_end")
	private Integer pmEnd;

   	@Column(name = "off_yn")
	private String offYn;


}
