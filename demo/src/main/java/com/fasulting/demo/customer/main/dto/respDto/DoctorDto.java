package com.fasulting.demo.customer.main.dto.respDto;

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
