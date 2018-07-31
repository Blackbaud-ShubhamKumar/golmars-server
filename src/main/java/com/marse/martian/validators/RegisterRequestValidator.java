package com.marse.martian.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.marse.martian.constants.MartianConstant;
import com.marse.martian.dao.NodeDao;
import com.marse.martian.dto.RegisterationData;
import com.marse.martian.entities.Node;

@Component("regVal")
public class RegisterRequestValidator implements Validator {

	@Autowired
	private NodeDao nodeDao;

	@Override
	public boolean supports(Class<?> claz) {
		return RegisterationData.class.isAssignableFrom(claz);
	}

	@Override
	public void validate(Object object, Errors error) {
		RegisterationData request = (RegisterationData) object;
		validateNull(error, request);
		validateSponsor(error, request);
	}

	private void validateNull(Errors error, RegisterationData request) {
		if (StringUtils.isEmpty(request.getAadharNumber())) {
			error.reject("Aadhaar number required");
		}
		if (StringUtils.isEmpty(request.getAccountNo())) {
			error.reject("Account number is required");
		}
		if (StringUtils.isEmpty(request.getBank())) {
			error.reject("");
		}
		if (StringUtils.isEmpty(request.getEmailId())) {
			error.reject("Email is required");
		}
		if (StringUtils.isEmpty(request.getFirstName())) {
			error.reject("Name is required");
		}
		if (StringUtils.isEmpty(request.getIfscCode())) {
			error.reject("IFSC code required");
		}
		if (StringUtils.isEmpty(request.getLastName())) {
			error.reject("Name is required");
		}
		if (StringUtils.isEmpty(request.getPanCard())) {
			error.reject("Pan number is required");
		}
		if (StringUtils.isEmpty(request.getPassword())) {
			error.reject("Password is required");
		}
		if (StringUtils.isEmpty(request.getPhoneNumber())) {
			error.reject("Phone number is required");
		}
		if (StringUtils.isEmpty(request.getPosition())) {
			error.reject("Position is required");
		}
		if (StringUtils.isEmpty(request.getSponsorId())) {
			error.reject("Sponsor is required");
		}
		if (!request.isAcceptedTnc()) {
			error.reject("Please accept TnC");
		}

	}

	private void validateSponsor(Errors error, RegisterationData request) {
		try {
			Node sponsor = nodeDao
					.findById(Long.parseLong(request.getSponsorId().substring(2, request.getSponsorId().length())));
			if (null != sponsor) {
				if (sponsor.getLeft() != null && sponsor.getRight() != null) {
					error.reject("Sponsor is not valid.");
				} else if (sponsor.getLeft() != null && sponsor.getRight() == null
						&& MartianConstant.LEFT_NODE.equalsIgnoreCase(request.getPosition())) {
					error.reject("Sponsor is not valid.");
				} else if (sponsor.getLeft() == null && sponsor.getRight() != null
						&& MartianConstant.RIGHT_NODE.equalsIgnoreCase(request.getPosition())) {
					error.reject("Sponsor is not valid.");
				}
			} else {
				error.reject("Sponsor is not valid.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			error.reject("Sponsor is not valid.");
		}
	}

}
