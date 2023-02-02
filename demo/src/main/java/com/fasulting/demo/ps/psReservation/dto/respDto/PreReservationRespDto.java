package com.fasulting.demo.ps.psReservation.dto.respDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PreReservationRespDto {

    private Long conultingSeq;
    String username;
    String estimate;
    List<String> subCategoryName;
    String date;

}
