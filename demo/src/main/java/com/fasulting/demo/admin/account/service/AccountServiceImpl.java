package com.fasulting.demo.admin.account.service;

import com.fasulting.demo.admin.account.dto.respDto.PsWaitRespDto;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.ps.ps.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    private final PsRepository psRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final PsMainRepository psMainRepository;
    private final PsMainSubRepository psMainSubRepository;

    public AccountServiceImpl(PsRepository psRepository, MainCategoryRepository mainCategoryRepository, SubCategoryRepository subCategoryRepository, PsMainRepository psMainRepository, PsMainSubRepository psMainSubRepository) {
        this.psRepository = psRepository;
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.psMainRepository = psMainRepository;
        this.psMainSubRepository = psMainSubRepository;
    }


    @Override
    public List<PsWaitRespDto> getPsWaitList() {
        List<PsEntity> psList = psRepository.findAllByConfirmYn("N");

        for(PsEntity ps : psList) {
            PsWaitRespDto psWait = PsWaitRespDto.builder()
                    .psSeq(ps.getSeq())
                    .email(ps.getEmail())
                    .name(ps.getName())
                    .address(ps.getAddress())
                    .zipcode(ps.getZipcode())
                    .registration(ps.getRegistration())
                    .number(ps.getNumber())
                    .director(ps.getDirector())
                    .homepage(ps.getHomepage())
                    .intro(ps.getIntro())
//                    .mainCategoryList(psMainRepository.getMainByPsSeq(ps.getSeq()))
//                    .subCategoryList(subCategoryRepository.findByMainCategory())
                    .build();
        }
//
//        return ;
    }


}
