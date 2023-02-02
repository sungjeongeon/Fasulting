package com.fasulting.demo.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "total_rating")
public class TotalRatingEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "ps_seq")
	private PsEntity ps;

   	@Column(name = "result")
	private BigDecimal result;

   	@Column(name = "sum")
	private BigDecimal sum;

   	@Column(name = "count")
	private BigDecimal count;
	@Builder
	public TotalRatingEntity(PsEntity ps) {
		this.ps = ps;
		this.result = BigDecimal.ZERO;
		this.sum = BigDecimal.ZERO;
		this.count = BigDecimal.ZERO;
	}

	public void updateByReg(BigDecimal point){
		this.sum = this.sum.add(point);
		this.count = this.sum.add(BigDecimal.ONE);
		this.result = this.sum.divide(this.count, 1);
	}

	public void updateByDel(BigDecimal point){
		this.sum = this.sum.subtract(point);
		this.count = this.sum.subtract(BigDecimal.ONE);
		this.result = this.sum.divide(this.count, 1);
	}



}
