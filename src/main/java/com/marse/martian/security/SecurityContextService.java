package com.marse.martian.security;

import java.util.Optional;

import com.marse.martian.entities.AuthDetail;

public interface SecurityContextService {

    Optional<AuthDetail> currentUser();

}
