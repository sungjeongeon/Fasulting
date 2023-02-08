package com.fasulting.domain.ps.ps.dto.respDto;

import com.fasulting.common.dto.respDto.MainCategoryRespDto;
import com.fasulting.common.dto.respDto.SubCategoryRespDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class CategoryListRespDto {

    private List<MainCategoryRespDto> mainCategoryList;
    private List<SubCategoryRespDto> subCategoryList;
}
