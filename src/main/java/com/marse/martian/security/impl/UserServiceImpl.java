package com.marse.martian.security.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marse.martian.entities.AuthDetail;
import com.marse.martian.exceptions.MartianServiceException;
import com.marse.martian.repos.base.AuthDetailRepository;
import com.marse.martian.security.SecurityContextService;
import com.marse.martian.security.UserService;
import com.marse.martian.security.filters.SHA256PasswordEncoder;
import com.marse.martian.util.SecurityContextHelper;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private final SecurityContextService securityContextService;
	private final AuthDetailRepository userRepository;
	private SHA256PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(AuthDetailRepository userRepository, SecurityContextService securityContextService,
			SHA256PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.securityContextService = securityContextService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Optional<AuthDetail> user = userRepository.findOneByUsername(username);
		final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
		user.ifPresent(detailsChecker::check);
		return user.orElseThrow(() -> new UsernameNotFoundException("user not found."));
	}

	@Override
	@Transactional
	public void updatePassword(String newPassword) throws MartianServiceException {
		try {

			userRepository.updatePassword(passwordEncoder.encode(newPassword), SecurityContextHelper.getCurrentUserName());
		} catch (Exception e) {
			throw new MartianServiceException("Error while updating password.", e);
		}
	}

}
