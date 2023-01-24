import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AccountAuditorAware implements AuditorAware<String> {

//    private final HttpSession httpSession;
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//        if(user != null) {
//            return Optional.ofNullable("user " + user.getId());
//        }
//
//        SessionUser ps = (SessionUser) httpSession.getAttribute("ps");
//        if(ps != null) {
//            return Optional.ofNullable("ps " + ps.getId());
//        }
//
//        SessionUser admin = (SessionUser) httpSession.getAttribute("admin");
//        if(admin != null) {
//            return Optional.ofNullable("admin " + admin.getId());
//        }
//
//        return null;
//    }

//    @Override
//    public Optional<?> getCurrentAuditor() {
//        // Spring Security를 통한 Auditor 매핑
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return null;
//        }
//
//        return ((MyUserDetails) authentication.getPrincipal()).getUser();
//    }
}