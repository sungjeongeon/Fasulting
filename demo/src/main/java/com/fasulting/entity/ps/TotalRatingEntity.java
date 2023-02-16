package com.fasulting.entity.ps;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.ps.PsEntity;
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
	public TotalRatingEntity(PsEntity ps, BigDecimal point) {
		this.ps = ps;
		this.result = point;
		this.sum = point;
		this.count = BigDecimal.ONE;
	}

	public void updateByReg(BigDecimal point){
		BigDecimal newSum = this.sum.add(point);
		this.sum = newSum;

		BigDecimal newCnt = this.count.add(BigDecimal.ONE);
		this.count = newCnt;
		this.result = newSum.divide(newCnt, 1);
	}

	public void updateByDel(BigDecimal point){
		BigDecimal newSum = this.sum.subtract(point);
		this.sum = newSum;

		BigDecimal newCnt = this.count.subtract(BigDecimal.ONE);
		this.count = newCnt;
		this.result = newSum.divide(newCnt, 1);
	}



}
