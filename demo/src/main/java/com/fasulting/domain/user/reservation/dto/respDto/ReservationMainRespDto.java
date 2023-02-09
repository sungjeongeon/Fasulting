package com.fasulting.domain.user.reservation.dto.respDto;

import com.fasulting.common.dto.respDto.SubCategoryRespDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class ReservationMainRespDto {

    private Long mainSeq;
    private String mainName;
    private List<SubCategoryRespDto> subCategoryList;
}
