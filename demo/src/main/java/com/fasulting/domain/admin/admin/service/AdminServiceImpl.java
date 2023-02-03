package com.fasulting.domain.admin.admin.service;

import com.fasulting.domain.admin.admin.dto.reqDto.AdminLoginReqDto;
import com.fasulting.domain.admin.admin.dto.respDto.AdminLoginRespDto;
import com.fasulting.repository.user.UserRepository;
import com.fasulting.entity.user.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AdminLoginRespDto login(AdminLoginReqDto adminLoginReq) {
        if(userRepository.findAdminByEmailAndPassword(adminLoginReq.getEmail(), adminLoginReq.getPassword()).isPresent()) {

            UserEntity admin = userRepository.findAdminByEmailAndPassword(adminLoginReq.getEmail(), adminLoginReq.getPassword()).get();

            log.info(admin.toString());

            AdminLoginRespDto adminInfoRespDto = AdminLoginRespDto.builder()
                    .adminSeq(admin.getSeq())
                    .adminName(admin.getName())
                    .build();

            return adminInfoRespDto;

        }

        return null;
    }
}
