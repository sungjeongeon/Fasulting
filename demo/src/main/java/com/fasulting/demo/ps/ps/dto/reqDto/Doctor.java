package com.fasulting.demo.resp;

import lombok.Data;

import java.util.List;

@Data
public class Doctor {

    private String name;
    private String img;
    private List<String> subCategoryList;

}
