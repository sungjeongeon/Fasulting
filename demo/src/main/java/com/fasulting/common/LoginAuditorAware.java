package com.fasulting.common;

import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class LoginAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Spring Security를 통한 Auditor 매핑
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Object authenticObj = authentication.getPrincipal();
        log.info("auditor : " + authentication.getPrincipal());

        String role = findRole(authentication);
        log.info("auditor role : " + role);

        if (role != null) {

            if (role.equals(RoleType.USER)) {
                UserEntity user = (UserEntity) authenticObj;
                return Optional.of(RoleType.USER + "_" + user.getSeq());
            }
            if (role.equals(RoleType.ADMIN)) {
                UserEntity user = (UserEntity) authenticObj;
                return Optional.of(RoleType.ADMIN + "_" + user.getSeq());
            }
            if (role.equals(RoleType.PS)) {
                PsEntity ps = (PsEntity) authenticObj;
                log.info("auditor ps : " + ps.getName());
                return Optional.of(RoleType.PS + "_" + ps.getSeq());
            }
        }

        return Optional.empty();
    }

    public static String findRole(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        log.info(authorities.toString());

        if (authorities.stream().filter(o -> o.getAuthority().equals(RoleType.ADMIN)).findAny().isPresent()) {
            return RoleType.ADMIN;
        }
        if (authorities.stream().filter(o -> o.getAuthority().equals(RoleType.PS)).findAny().isPresent()) {
            return RoleType.PS;
        }
        if (authorities.stream().filter(o -> o.getAuthority().equals(RoleType.USER)).findAny().isPresent()) {
            return RoleType.USER;
        }

        return null;
    }

}
