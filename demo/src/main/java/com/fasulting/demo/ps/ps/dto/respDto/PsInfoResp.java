package com.fasulting.demo.ps.ps.dto.respDto;

import com.fasulting.demo.ps.ps.dto.reqDto.DoctorReq;
import lombok.Data;

import java.util.List;

@Data
public class PsInfoResp {

    private String name;
    private String profileImg;
    private String intro;
    private String address;
    private String zipcode;
    private String number;
    private String homepage;
    private String director;
    private String registration;
    private String registrationImg;

    // category 정보
    private List<String> mainCategoryList;
    private List<String> subCategoryList;

    // doctor 정보
    private List<DoctorReq> doctorList;

}
