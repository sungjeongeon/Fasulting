package com.fasulting.domain.admin.account.service;

import com.fasulting.common.RoleType;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.admin.account.dto.reqDto.ConfirmPsReqDto;
import com.fasulting.domain.admin.account.dto.respDto.PsWaitRespDto;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.user.RoleEntity;
import com.fasulting.repository.ps.PsMainRepository;
import com.fasulting.repository.ps.PsMainSubRepository;
import com.fasulting.repository.ps.PsRepository;
import com.fasulting.repository.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasulting.common.util.FileManage.domain;
import static com.fasulting.common.util.LogCurrent.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final PsRepository psRepository;
    private final PsMainRepository psMainRepository;
    private final PsMainSubRepository psMainSubRepository;
    private final RoleRepository roleRepository;

    /**
     * 병원 승인 대기 계정 조회
     * @return List<PsWaitRespDto>
     */
    @Transactional
    @Override
    public List<PsWaitRespDto> getPsWaitList() {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        List<PsEntity> psList = psRepository.findAllByConfirmYn("N");

        List<PsWaitRespDto> psWaitList = new ArrayList<>();
        for (PsEntity ps : psList) {
            PsWaitRespDto psWait = PsWaitRespDto.builder()
                    .psSeq(ps.getSeq())
                    .email(ps.getEmail())
                    .name(ps.getName())
                    .address(ps.getAddress())
                    .zipcode(ps.getZipcode())
                    .registration(ps.getRegImgPath())
                    .number(ps.getNumber())
                    .director(ps.getDirector())
                    .homepage(ps.getHomepage())
                    .intro(ps.getIntro())
                    .mainCategoryList(psMainRepository.getMainNameByPsSeq(ps.getSeq()))
                    .subCategoryList(psMainSubRepository.getSubNameByPsSeq(ps.getSeq()))
                    .registrationImg(domain + ps.getRegImgPath())
                    .profileImg(domain + ps.getProfileImgPath())
                    .build();

            psWaitList.add(psWait);
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return psWaitList;
    }

    /**
     * 병원 계정 가입 승인
     * @param confirmPsReqDto
     * @return true or false
     */
    @Transactional
    @Override
    public boolean approvePs(ConfirmPsReqDto confirmPsReqDto) {
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        RoleEntity role = roleRepository.findById(confirmPsReqDto.getAdminSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });
        PsEntity ps = psRepository.findById(confirmPsReqDto.getPsSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });

        if (!RoleType.ADMIN.equals(role.getAuthority())) {
            return false;
        }

        ps.updateByConfirm(RoleType.ADMIN + "_" + confirmPsReqDto.getAdminSeq(), LocalDateTime.now());
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return true;
    }


}
