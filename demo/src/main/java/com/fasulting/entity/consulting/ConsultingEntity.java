package com.fasulting.entity.consulting;

import javax.persistence.*;

import com.fasulting.entity.BaseEntity;
import com.fasulting.entity.reservation.ReservationEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.user.UserEntity;
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
@Table (name = "consulting")
public class ConsultingEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "reservation_seq")
	private ReservationEntity reservation;

   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "ps_seq")
	private PsEntity ps;

   	/** FK setting */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "seq", name = "user_seq")
	private UserEntity user;


	@Builder
	public ConsultingEntity(ReservationEntity reservation, PsEntity ps, UserEntity user) {
		this.reservation = reservation;
		this.ps = ps;
		this.user = user;
	}
}
