package com.marse.martian.security.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.marse.martian.security.TokenAuthenticationService;
import com.marse.martian.security.TokenService;

@Service
class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    private final TokenService tokenHandler;

    @Autowired
    TokenAuthenticationServiceImpl(TokenService tokenHandler) {
        this.tokenHandler = tokenHandler;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String authHeader = request.getHeader("authorization");
        if (authHeader == null) return null;
        if (!authHeader.startsWith("Bearer")) return null;

        final String jwt = authHeader.substring(7);
        if (jwt.isEmpty()) return null;

        return tokenHandler
                .parseUserFromToken(jwt)
                .map(UserAuthenticationImpl::new)
                .orElse(null);
    }
}

