package com.marse.martian.security;

import com.marse.martian.exceptions.MartianServiceException;

public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService {

	public void updatePassword(String newPassword) throws MartianServiceException;

}
