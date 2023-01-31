package com.fasulting.demo.entity.compositeId;

import com.fasulting.demo.entity.MainCategoryEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.SubCategoryEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PsMainSubId implements Serializable {

    @EqualsAndHashCode.Include
    public Long ps;

    @EqualsAndHashCode.Include
    public Long mainCategory;

    @EqualsAndHashCode.Include
    public Long subCategory;
}
