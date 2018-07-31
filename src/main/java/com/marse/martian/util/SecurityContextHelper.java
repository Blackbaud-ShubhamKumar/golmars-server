package com.marse.martian.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.marse.martian.entities.AuthDetail;

public class SecurityContextHelper {

	public static String getCurrentUserName() {
		return ((AuthDetail) SecurityContextHolder.getContext().getAuthentication().getDetails()).getUsername();
	}

	public static AuthDetail getCurrentUserAuthDetail() {
		return ((AuthDetail) SecurityContextHolder.getContext().getAuthentication().getDetails());
	}

}
