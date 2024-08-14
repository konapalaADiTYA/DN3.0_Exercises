package com.example.EmployeeManagementSystem;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Here you can retrieve the current user from the security context or session
        // For simplicity, returning a fixed username
        return Optional.of("Admin");
    }
}
