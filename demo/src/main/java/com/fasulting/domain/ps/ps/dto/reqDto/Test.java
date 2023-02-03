package com.fasulting.domain.ps.ps.dto.reqDto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Test {

    String name;
    MultipartFile file;

}
