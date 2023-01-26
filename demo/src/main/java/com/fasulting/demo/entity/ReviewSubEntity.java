package com.fasulting.demo.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "review_sub")
public class ReviewSubEntity extends BaseEntity implements Serializable {

	@Id
   	/** FK setting */
	// @ManyToOne
	// @OneToMany
	// @JsonBackReference
	// @JoinColumn(name = "", updatable = false, insertable = false)
	private Long reviewSeq;

	@Id
   	/** FK setting */
	// @ManyToOne
	// @OneToMany
	// @JsonBackReference
	// @JoinColumn(name = "", updatable = false, insertable = false)
	private Long subSeq;


}
