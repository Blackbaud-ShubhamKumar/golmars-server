package com.marse.martian.security.impl;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.marse.martian.entities.AuthDetail;
import com.marse.martian.repos.base.AuthDetailRepository;
import com.marse.martian.security.TokenService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public final class TokenServiceImpl implements TokenService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

	private final String secret;

	private final AuthDetailRepository userRepository;

	@Autowired
	public TokenServiceImpl(@Value("${app.jwt.secret}") String secret, AuthDetailRepository userRepository) {
		this.secret = secret;
		this.userRepository = userRepository;
	}

	@Override
	public Optional<UserDetails> parseUserFromToken(String token) {
		final String subject = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		final AuthDetail user = userRepository.findById(Long.parseLong(subject)).orElse(null);

		return Optional.ofNullable(user);
	}

	@Override
	public String createTokenForUser(AuthDetail user) {
		final ZonedDateTime afterOneWeek = ZonedDateTime.now().plusWeeks(1);

		return Jwts.builder().setSubject(user.getId().toString()) // expiration + session
				.signWith(SignatureAlgorithm.HS512, secret).setExpiration(Date.from(afterOneWeek.toInstant()))
				.compact();
	}
}
