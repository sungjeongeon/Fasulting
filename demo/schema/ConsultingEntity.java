package C:.Users.SSAFY.Desktop.S08P12E106.demo.schema;

import javax.persistence.*;
import lombok.*;

/** FK setting
* @ManyToOne
* @OneToMany
* @JoinColumn(name = , updatable = false, insertable = false)
* @JsonBackReference
*/
@Entity
@Builder
@Getter
@Setter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "consulting")
public class ConsultingEntity extends BaseTimeEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Integer seq;

   	@Column(name = "reservation_seq")
	private Integer reservationSeq;

   	@Column(name = "ps_seq")
	private Integer psSeq;

   	@Column(name = "user_seq")
	private Integer userSeq;

	/**참조하는 회원 seq와 동일할 것
해당 컬럼에 값이 입력되는 것은 상담 전 
공지 및 동의가 이루어진 것*/
   	@Column(name = "reg_user_seq")
	private Integer regUserSeq;

	/**참조하는 병원 seq와 동일할 것
해당 컬럼에 값이 입력되는 것은 상담 전 
공지 및 동의가 이루어진 것*/
   	@Column(name = "reg_ps_seq")
	private Integer regPsSeq;


}
