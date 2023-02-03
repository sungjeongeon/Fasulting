package com.fasulting.domain.user.reservation.dto.respDto;

import com.fasulting.common.dto.respDto.PsOperatingRespDto;
import com.fasulting.domain.user.main.dto.respDto.MainCategoryRespDto;
import com.fasulting.domain.user.main.dto.respDto.SubCategoryRespDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class ReservationTableRespDto {

    private List<PsOperatingRespDto> operatingTime;
    private List<MainCategoryRespDto> mainCategory;
    private List<SubCategoryRespDto> subCategory;
}
