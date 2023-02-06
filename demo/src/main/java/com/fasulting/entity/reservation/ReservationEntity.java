package com.fasulting.entity.reservation;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.calendar.ReservationCalEntity;
import com.fasulting.entity.calendar.TimeEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.user.UserEntity;
import lombok.*;
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
@Table (name = "reservation")
public class ReservationEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "cal_seq")
	private ReservationCalEntity reservationCal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "time_seq")
	private TimeEntity time;

   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "ps_seq")
	private PsEntity ps;

   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "user_seq")
	private UserEntity user;

	@Column(name = "before_img_origin")
	private String beforeImgOrigin;

	@Column(name = "before_img_path")
	private String beforeImgPath;

   	@Column(name = "del_date")
	private LocalDateTime delDate;

   	@Column(name = "del_by")
	private String delBy;

   	@Column(name = "del_yn")
	private String delYn;

	@Builder
	public ReservationEntity(ReservationCalEntity reservationCal, TimeEntity time, PsEntity ps, UserEntity user, String beforeImgOrigin, String beforeImgPath) {
		this.reservationCal = reservationCal;
		this.time = time;
		this.ps = ps;
		this.user = user;
		this.beforeImgOrigin = beforeImgOrigin;
		this.beforeImgPath = beforeImgPath;
		this.delYn = "N";
	}

	public void updateByCancel(String delBy, LocalDateTime current) {
		this.delYn = "Y";
		this.delBy = delBy;
		this.delDate = current;
	}

}
