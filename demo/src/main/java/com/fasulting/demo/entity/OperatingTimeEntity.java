package com.fasulting.demo.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "operating_time")
public class OperatingTimeEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

	@Column(name = "num")
	private Integer num;
   	@Column(name = "start_hour")
	private Integer startHour;

   	@Column(name = "start_min")
	private Integer startMin;

   	@Column(name = "end_hour")
	private Integer endHour;

   	@Column(name = "end_min")
	private Integer endMin;


}
