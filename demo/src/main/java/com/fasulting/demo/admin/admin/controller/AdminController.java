package com.fasulting.demo.admin.admin.controller;

import com.fasulting.demo.admin.admin.dto.reqDto.AdminLoginReq;
import com.fasulting.demo.admin.admin.service.AdminService;
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
    public ResponseEntity<?> login(@RequestBody AdminLoginReq adminLoginReq) {
        return null;
    }

    /**
     * 2. 로그아웃
     * @return success or fail
     */
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return null;
    }

}
