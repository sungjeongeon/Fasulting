package com.fasulting.demo.ps.ps.dto.reqDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "Doctor", description = "전문의 정보")
public class Doctor {

    @ApiModelProperty(value = "전문의 이름", example="성정언웅")
    private String name;
    @ApiModelProperty(value = "전문의 카테고리 리스트")
    private List<String> subCategoryList;

    @ApiModelProperty(value = "전문의 이미지 파일")
    private MultipartFile img;

}
