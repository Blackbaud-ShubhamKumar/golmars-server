package com.marse.martian.security.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Loggers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marse.martian.constants.MartianConstant;
import com.marse.martian.security.SecurityContextService;
import com.marse.martian.security.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger AUTH_LOGGER = LogManager.getLogger(AuthController.class);

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenHandler;
	private final SecurityContextService securityContextService;

	@Autowired
	AuthController(AuthenticationManager authenticationManager, TokenService tokenHandler,
			SecurityContextService securityContextService) {
		this.authenticationManager = authenticationManager;
		this.tokenHandler = tokenHandler;
		this.securityContextService = securityContextService;
	}

	@RequestMapping(method = RequestMethod.POST)
	@CrossOrigin(origins = MartianConstant.PROD_ORIGIN)
	public AuthResponse auth(@RequestBody AuthParams params) throws AuthenticationException {
		AUTH_LOGGER.info("Authentication request - U:{}", params.getEmail());
		final UsernamePasswordAuthenticationToken loginToken = params.toAuthenticationToken();
		final Authentication authentication = authenticationManager.authenticate(loginToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return securityContextService.currentUser().map(u -> {
			final String token = tokenHandler.createTokenForUser(u);
			AUTH_LOGGER.info("Authentication Token Generated.");
			return new AuthResponse(token);
		}).orElseThrow(RuntimeException::new); // it does not happen.
		
	}

	private static final class AuthParams {
		private String email;
		private String password;

		public AuthParams() {
		}

		public AuthParams(String email, String password) {
			this.email = email;
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		UsernamePasswordAuthenticationToken toAuthenticationToken() {
			return new UsernamePasswordAuthenticationToken(email, password);
		}
	}

	private static final class AuthResponse {
		private String token;

		public AuthResponse() {
		}

		public AuthResponse(String token) {
			this.token = token;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}
}
