package com.inntri.support.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class EntityAuditor implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("getCurrentAuditor ====>  {}", authentication);
        if (authentication != null) {
            log.info("getDetails ====>  {}", authentication.getPrincipal());
            if (authentication.getPrincipal() != null && !authentication.getPrincipal().equals("anonymousUser")) {
                return Optional.ofNullable((Long) authentication.getPrincipal());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }
}