package com.marse.martian.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.marse.martian.constants.MartianConstant;
import com.marse.martian.dao.NodeDao;
import com.marse.martian.dto.RegisterationData;
import com.marse.martian.dto.ResetPasswordData;
import com.marse.martian.entities.Node;
import com.marse.martian.security.filters.SHA256PasswordEncoder;
import com.marse.martian.util.SecurityContextHelper;

@Component("resetVal")
public class ResetPasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> claz) {
		return ResetPasswordData.class.isAssignableFrom(claz);
	}

	@Autowired
	private SHA256PasswordEncoder passwordEncoder;

	@Override
	public void validate(Object object, Errors error) {
		ResetPasswordData request = (ResetPasswordData) object;
		if (StringUtils.isBlank(request.getNewPassword())) {
			error.reject("New Password is required.");
		}
		if (StringUtils.isBlank(request.getOldPassword())) {
			error.reject("Old Password is required.");
		}
		if (StringUtils.isBlank(request.getRepeatPassword())) {
			error.reject("Repeat Password is required.");
		}
		if (StringUtils.isNotBlank(request.getRepeatPassword()) && StringUtils.isNotBlank(request.getNewPassword())) {
			if (!StringUtils.equals(request.getRepeatPassword(), request.getNewPassword())) {
				error.reject("New Password / Repeat Password should be same.");
			}
			
			if (StringUtils.isNotBlank(request.getOldPassword()) && StringUtils.equals(request.getOldPassword(), request.getNewPassword())) {
				error.reject("New Password / Old Password should not be same.");
			}
		}

		if (!passwordEncoder.matches(request.getOldPassword(),
				SecurityContextHelper.getCurrentUserAuthDetail().getPassword())) {
			error.reject("Old Password is not correct.");
		}

	}

}
