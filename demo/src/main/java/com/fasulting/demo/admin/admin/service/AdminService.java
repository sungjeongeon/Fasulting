package com.fasulting.demo.admin.admin.service;

import com.fasulting.demo.admin.admin.dto.reqDto.AdminLoginReqDto;
import com.fasulting.demo.admin.admin.dto.respDto.AdminLoginRespDto;

public interface AdminService {
    AdminLoginRespDto login(AdminLoginReqDto adminLoginReq);
}
