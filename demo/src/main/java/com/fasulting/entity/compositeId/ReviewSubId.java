package com.fasulting.entity.compositeId;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ReviewSubId implements Serializable {

    @EqualsAndHashCode.Include
    public Long review;

    @EqualsAndHashCode.Include
    public Long subCategory;

    @Builder
    public ReviewSubId(Long review, Long subCategory) {
        this.review = review;
        this.subCategory = subCategory;
    }
}
