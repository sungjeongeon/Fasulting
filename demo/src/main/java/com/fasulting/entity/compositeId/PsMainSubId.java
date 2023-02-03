package com.fasulting.entity.compositeId;

import com.fasulting.entity.category.MainCategoryEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.category.SubCategoryEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PsMainSubId implements Serializable {

    @EqualsAndHashCode.Include
    public PsEntity ps;

    @EqualsAndHashCode.Include
    public MainCategoryEntity mainCategory;

    @EqualsAndHashCode.Include
    public SubCategoryEntity subCategory;
}
