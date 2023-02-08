package com.fasulting.common.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class DoctorRespDto {

    private Long doctorSeq;

    private String name;

    private String profileImg;

    private String mainCategoryName;
}
