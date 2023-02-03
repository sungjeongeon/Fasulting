package com.fasulting.entity.consulting;

import com.fasulting.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "report")
public class ReportEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	/** FK setting */
	@OneToOne
	@JoinColumn(referencedColumnName = "seq", name = "consulting_seq")
	private ConsultingEntity consulting;

	/**서버에 저장된 비포 사진 경로*/
   	@Column(name = "before_img_path")
	private String beforeImgPath;

	@Column(name = "before_img_origin")
	private String beforeImgOrigin;

	/**서버에 저장된 애프터 사진 경로*/
   	@Column(name = "after_img_path")
	private String afterImgPath;

	@Column(name = "after_img_origin")
	private String afterImgOrigin;

	/**소견 작성 내용*/
   	@Column(name = "content")
	private String content;

	/**예상 견적 비용*/
   	@Column(name = "estimate")
	private String estimate;


}
