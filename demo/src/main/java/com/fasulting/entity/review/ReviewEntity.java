package com.fasulting.entity.review;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.consulting.ConsultingEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "review")
public class ReviewEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "consulting_seq")
	private ConsultingEntity consulting;


   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "ps_seq")
	private PsEntity ps;

   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "user_seq")
	private UserEntity user;

   	@Column(name = "content")
	private String content;

	@Column(name = "point")
	private BigDecimal point;

   	@Column(name = "dec_date")
	private LocalDateTime decDate;

   	@Column(name = "dec_by")
	private String decBy;

   	@Column(name = "dec_yn")
	private String decYn;

   	@Column(name = "del_date")
	private LocalDateTime delDate;

   	@Column(name = "del_by")
	private String delBy;

   	@Column(name = "del_yn")
	private String delYn;

	public void updateByDec(String decBy, LocalDateTime decDate) {
		this.decYn = "Y";
		this.decBy = decBy;
		this.decDate = decDate;
	}

	public void updateByDel(String delBy, LocalDateTime delDate) {
		this.delYn = "Y";
		this.delBy = delBy;
		this.delDate = delDate;
	}

	@Builder
	public ReviewEntity(ConsultingEntity consulting, PsEntity ps, UserEntity user, String content, BigDecimal point) {
		this.consulting = consulting;
		this.ps = ps;
		this.user = user;
		this.content = content;
		this.point = point;
		this.decYn = "N";
		this.delYn = "N";
	}
}
