package com.fasulting.domain.user.main.service;

import com.fasulting.common.dto.respDto.MainCategoryRespDto;
import com.fasulting.common.dto.respDto.SubCategoryRespDto;
import com.fasulting.domain.user.main.dto.respDto.PsDetailRespDto;
import com.fasulting.domain.user.main.dto.respDto.PsListRespDto;

import java.util.List;

public interface MainService {

    List<MainCategoryRespDto> getMainCategoryList();

    List<SubCategoryRespDto> getSubcategoryList(Long mainSeq);

    List<PsListRespDto> getPsList(Long mainSeq);

    PsDetailRespDto getPsDetail(Long userSeq, Long psSeq);
}
