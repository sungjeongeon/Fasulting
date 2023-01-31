package com.fasulting.demo.common.doctor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class DoctorDto {

    private Long doctorSeq;

    private String name;

    private String profileImg;

    private List<String> subCategoryName;
}
