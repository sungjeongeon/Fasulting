package com.fasulting.domain.user.reservation.dto.reqDto;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class RegReservationReqDto {

    private Long userSeq;
    private Long psSeq;
    private int year;
    private int month;
    private  int day;
    private int dayOfWeek;
    private int time;

    private MultipartFile beforeImg;

    private List<Long> subCategory = new ArrayList<>();
}
