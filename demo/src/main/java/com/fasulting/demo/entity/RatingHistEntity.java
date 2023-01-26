package com.fasulting.demo.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "rating_hist")
public class RatingHistEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	/** FK setting */
	// @ManyToOne
	// @OneToMany
	// @JsonBackReference
	// @JoinColumn(name = "", updatable = false, insertable = false)
	private Long consultingSeq;

   	/** FK setting */
	// @ManyToOne
	// @OneToMany
	// @JsonBackReference
	// @JoinColumn(name = "", updatable = false, insertable = false)
	private Long psSeq;

   	/** FK setting */
	// @ManyToOne
	// @OneToMany
	// @JsonBackReference
	// @JoinColumn(name = "", updatable = false, insertable = false)
	private Long userSeq;

	/**최초 평가 후 평점 적용
평가마다 평점 업데이트*/
   	@Column(name = "point")
	private BigDecimal point;

   	@Column(name = "del_date")
	private LocalDateTime delDate;

   	@Column(name = "del_by")
	private String delBy;

   	@Column(name = "del_yn")
	private String delYn;


}
