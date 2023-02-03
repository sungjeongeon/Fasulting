package com.fasulting.domain.ps.psReservation.dto.respDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PreReservationRespDto {

    private Long consultingSeq;
    String userName;
    String estimate;
    List<String> subCategoryName;
    String date;

}
