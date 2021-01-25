package com.board.foo.config;

import com.board.foo.domain.Member;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@EnableJpaAuditing
@Configuration
public class JpaConfig{

    private final String ANONYMOUS_USER = "anonymousUser";

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication.getPrincipal().equals(ANONYMOUS_USER)) {
                return Optional.empty();
            }
            Member member = (Member) authentication.getPrincipal();
            return Optional.of(member.getEmail());
        };
    }
}

