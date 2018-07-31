package com.marse.martian.security.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marse.martian.entities.AuthDetail;
import com.marse.martian.repos.base.AuthDetailRepository;
import com.marse.martian.security.SecurityContextService;

@Service
@Transactional
public class SecurityContextServiceImpl implements SecurityContextService {

    private final AuthDetailRepository userRepository;

    @Autowired
    public SecurityContextServiceImpl(AuthDetailRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<AuthDetail> currentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findOneByUsername(authentication.getName());
    }
}
