package com.fasulting.demo.customer.reservation.dto.respDto;

import com.fasulting.demo.common.psOperating.dto.respDto.PsOperatingRespDto;
import com.fasulting.demo.customer.main.dto.respDto.MainCategoryRespDto;
import com.fasulting.demo.customer.main.dto.respDto.SubCategoryRespDto;
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
