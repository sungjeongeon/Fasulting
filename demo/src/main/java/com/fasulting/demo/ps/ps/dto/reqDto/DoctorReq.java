package com.fasulting.demo.ps.ps.dto.reqDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@ApiModel(value = "Doctor", description = "전문의 정보")
public class DoctorReq {

    @ApiModelProperty(value = "ps req", example = "1")
    private Long seq;
    @ApiModelProperty(value = "전문의 이름", example="성정언웅")
    private String name;
    @ApiModelProperty(value = "전문의 전문 분야")
    private String mainCategory;

    @ApiModelProperty(value = "전문의 이미지 파일")
    private MultipartFile img;

}
