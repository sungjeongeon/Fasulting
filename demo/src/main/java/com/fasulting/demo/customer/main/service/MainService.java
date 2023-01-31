package com.fasulting.demo.customer.main.service;

import com.fasulting.demo.customer.main.dto.respDto.MainCategoryRespDto;
import com.fasulting.demo.customer.main.dto.respDto.PsListRespDto;
import com.fasulting.demo.customer.main.dto.respDto.SubCategoryListRespDto;

import java.util.List;

public interface MainService {

    List<MainCategoryRespDto> getMainCategoryList();

    List<SubCategoryListRespDto> getSubcategoryList(Long mainSeq);

    List<PsListRespDto> getPsList(Long mainSeq);
}
