package com.fasulting.demo.ps.ps.dto.reqDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "PsSeqReq", description = "병원 계쩡 사용 시 필요한 정보 (로그인 상태)")
public class PsSeqReq {

    @ApiModelProperty(value = "회원 seq", example="1")
    private Long seq;

    @ApiModelProperty(value = "회원 이메일", example="hotsix@naver.com")
    private String email;
    @ApiModelProperty(value = "회원 비밀번호", example="1234")
    private String password;
    @ApiModelProperty(value = "병원 이름", example="미림 병원")
    private String name;
    @ApiModelProperty(value = "병원 주소", example="부산광역시 어쩌구")
    private String address;
    @ApiModelProperty(value = "병원 주소 우편 번호", example="1234566")
    private String zipcode;
    @ApiModelProperty(value = "병원 사업자 등록 번호", example="111-111-11-")
    private String registration;
    @ApiModelProperty(value = "병원 전화번호", example="010-2222-3333")
    private String number;
    @ApiModelProperty(value = "병원 대표 원장 이름", example="권성현")
    private String director;
    @ApiModelProperty(value = "병원 홈페이지 주소", example="www.hompage.com")
    private String homepage;
    @ApiModelProperty(value = "병원 소개글", example="반가워요^^ 미림 병원입니다^^")
    private String intro;

    @ApiModelProperty(value = "병원 사업증 이미지 파일")
    private MultipartFile registrationImg;
    @ApiModelProperty(value = "병원 사업증 이미지 파일")
    private MultipartFile profileImg;


    // category 정보
    @ApiModelProperty(value = "병원 메인 카테고리 리스트")
    private List<String> mainCategoryList = new ArrayList<>();
    @ApiModelProperty(value = "병원 서브 카테고리 리스트")
    private List<String> subCategoryList = new ArrayList<>();

    // doctor 정보
    @ApiModelProperty(value = "전문의 정보 리스트")
    private List<DoctorReq> doctorList = new ArrayList<>();


}
