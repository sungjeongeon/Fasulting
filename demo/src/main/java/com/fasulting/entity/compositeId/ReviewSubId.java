package com.fasulting.entity.compositeId;

import com.fasulting.entity.review.ReviewEntity;
import com.fasulting.entity.category.SubCategoryEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ReviewSubId implements Serializable {

    @EqualsAndHashCode.Include
    public ReviewEntity review;

    @EqualsAndHashCode.Include
    public SubCategoryEntity subCategory;
}
