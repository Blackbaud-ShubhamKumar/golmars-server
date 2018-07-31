package com.marse.martian.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.marse.martian.entities.*;

import java.util.Optional;
 
@Component
public interface TokenService {

    Optional<UserDetails> parseUserFromToken(String token);

    String createTokenForUser(AuthDetail user);

}
