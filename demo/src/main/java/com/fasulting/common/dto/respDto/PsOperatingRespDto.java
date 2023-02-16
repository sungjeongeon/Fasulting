package com.fasulting.common.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class PsOperatingRespDto {
    private int year;
    private int month;
    private int day;
    private int dayOfWeek;
    List<Integer> time;

    public void addTime(int num){
        this.time.add(num);
    }
}
