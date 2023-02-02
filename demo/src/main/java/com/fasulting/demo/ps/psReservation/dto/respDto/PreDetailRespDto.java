package com.fasulting.demo.ps.psReservation.dto.respDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PreDetailRespDto {

    private String userName;
    private String userBirth;
    private String userNumber;
    private String date;

    private List<String> subCategoryName;
    private String content;
    private String estimate;

}
