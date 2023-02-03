package com.fasulting.domain.admin.admin.controller;

import com.fasulting.domain.admin.admin.dto.reqDto.AdminLoginReqDto;
import com.fasulting.domain.admin.admin.dto.respDto.AdminLoginRespDto;
import com.fasulting.domain.admin.admin.service.AdminService;
import com.fasulting.common.resp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 1. 로그인
     * @param adminLoginReq (id, password)
     * @return seq
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginReqDto adminLoginReq) {
        log.info("admin Login - Call");

        AdminLoginRespDto ps = adminService.login(adminLoginReq);

        if(ps != null) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", ps));
        }
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));

    }

    /**
     * 2. 로그아웃
     * @return success or fail
     */
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        
        // ㅂㅂ
        return null;
    }

}
