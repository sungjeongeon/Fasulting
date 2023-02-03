package com.fasulting.domain.user.main.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class PsDefaultDto {

    private int dayOfWeek;

    private String offYn;

    private List<Integer> time;
}
