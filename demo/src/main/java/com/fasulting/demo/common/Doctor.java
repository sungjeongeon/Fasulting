package com.fasulting.demo.common;

import lombok.Data;

import java.util.List;

@Data
public class Doctor {

    private String name;
    private String img;
    private List<String> subCategoryList;

}
