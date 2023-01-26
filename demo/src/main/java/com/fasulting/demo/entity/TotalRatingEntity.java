package com.fasulting.demo.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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
	// @ManyToOne
	// @OneToMany
	// @JsonBackReference
	// @JoinColumn(name = "", updatable = false, insertable = false)
	private Long psSeq;

   	@Column(name = "result")
	private BigDecimal result;

   	@Column(name = "sum")
	private BigDecimal sum;

   	@Column(name = "count")
	private Integer count;


}
