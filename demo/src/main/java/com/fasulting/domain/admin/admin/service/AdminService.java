package com.fasulting.domain.admin.admin.service;

import com.fasulting.domain.admin.admin.dto.reqDto.AdminLoginReqDto;
import com.fasulting.domain.admin.admin.dto.respDto.AdminLoginRespDto;

public interface AdminService {
    AdminLoginRespDto login(AdminLoginReqDto adminLoginReq);
}
