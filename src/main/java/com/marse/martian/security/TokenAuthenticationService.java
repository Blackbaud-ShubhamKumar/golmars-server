package com.marse.martian.security;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface TokenAuthenticationService {

    Authentication getAuthentication(HttpServletRequest request);
}

