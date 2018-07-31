package com.marse.martian.controllers.base;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marse.martian.constants.MartianConstant;
import com.marse.martian.dto.DashboardData;
import com.marse.martian.dto.DashboardResponse;
import com.marse.martian.dto.NodeData;
import com.marse.martian.dto.PaymentUploadResponse;
import com.marse.martian.dto.ProfileData;
import com.marse.martian.dto.ProfileResponse;
import com.marse.martian.dto.RegisterartionResponse;
import com.marse.martian.dto.RegisterationData;
import com.marse.martian.dto.ResetPasswordData;
import com.marse.martian.dto.ResetPasswordResponse;
import com.marse.martian.dto.TreeListResponse;
import com.marse.martian.dto.TreeResponse;
import com.marse.martian.dto.WalletData;
import com.marse.martian.dto.WalletResponse;
import com.marse.martian.entities.AuthDetail;
import com.marse.martian.entities.TransStatus;
import com.marse.martian.security.UserService;
import com.marse.martian.services.MartianBaseService;
import com.marse.martian.util.SecurityContextHelper;
import com.marse.martian.validators.RegisterRequestValidator;
import com.marse.martian.validators.ResetPasswordValidator;

@RestController
public class MartianController {

	private static final Logger MARS_LOGGER = LogManager.getLogger(MartianController.class);

	@Autowired
	@Qualifier("resetVal")
	private ResetPasswordValidator resetPasswordValidator;

	@Autowired
	@Qualifier("regVal")
	private RegisterRequestValidator registerRequestValidator;

	@Autowired
	private MartianBaseService martianService;

	@Autowired
	private UserService userService;

	@InitBinder("registerationData")
	public void setupRegValBinder(WebDataBinder binder) {
		binder.addValidators(registerRequestValidator);
	}

	@InitBinder("resetPasswordData")
	public void setupResetValBinder(WebDataBinder binder) {
		binder.addValidators(resetPasswordValidator);
	}

	@PostMapping("/api/register")
	@CrossOrigin(origins = MartianConstant.PROD_ORIGIN)
	public ResponseEntity<RegisterartionResponse> registerUser(@RequestBody @Valid RegisterationData reg) {
		MARS_LOGGER.info("Registration request recieved.");
		String myUserId = martianService.register(reg);
		MARS_LOGGER.info("Registration request compleated.");
		return new ResponseEntity<RegisterartionResponse>(new RegisterartionResponse(myUserId, "00", "Registered"),
				HttpStatus.OK);
	}

	@GetMapping("/api/tree")
	@CrossOrigin(origins = MartianConstant.PROD_ORIGIN)
	public ResponseEntity<TreeResponse> getTree() {
		MARS_LOGGER.info("Tree view request recieved.");
		NodeData node = martianService.getTree(SecurityContextHelper.getCurrentUserName());
		MARS_LOGGER.info("Tree view request compleated.");
		return new ResponseEntity<TreeResponse>(new TreeResponse(node, "00", "Registered"), HttpStatus.OK);
	}

	@GetMapping("/api/wallet")
	@CrossOrigin(origins = MartianConstant.PROD_ORIGIN)
	public ResponseEntity<WalletResponse> getWallets() {
		MARS_LOGGER.info("Wallet view request recieved.");
		List<WalletData> wallets = martianService.getWalletDataForUser(SecurityContextHelper.getCurrentUserName());
		Double totalRecieved = wallets.stream().mapToDouble(w -> w.getAmount()).sum();
		Double totalExpenseOrSent = wallets.stream()
				.filter(w -> w.getTransStatus().equalsIgnoreCase(TransStatus.PAID.getValue()))
				.mapToDouble(w -> w.getAmount()).sum();

		WalletResponse response = new WalletResponse(wallets, "00", "WalletList");
		response.setTotalRecieved(totalRecieved.floatValue());
		response.setTotalExpenseOrSent(totalExpenseOrSent.floatValue());
		response.setTotalBalance((totalRecieved.floatValue() - totalExpenseOrSent.floatValue()));
		MARS_LOGGER.info("Wallet view request compleated.");
		return new ResponseEntity<WalletResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/api/profile")
	@CrossOrigin(origins = MartianConstant.PROD_ORIGIN)
	public ResponseEntity<ProfileResponse> getProfile() {
		MARS_LOGGER.info("Profile view request recieved.");
		ProfileData profile = martianService.getProfileForUser(SecurityContextHelper.getCurrentUserName());
		MARS_LOGGER.info("Profile view request compleated.");
		return new ResponseEntity<ProfileResponse>(new ProfileResponse(profile, "00", "Profile"), HttpStatus.OK);
	}

	@GetMapping("/api/dashboard")
	@CrossOrigin(origins = MartianConstant.PROD_ORIGIN)
	public ResponseEntity<DashboardResponse> getDashboardStats() {
		MARS_LOGGER.info("Dashboard view request recieved.");
		DashboardData dashboardData = martianService.getDashboardForUser(SecurityContextHelper.getCurrentUserName());
		MARS_LOGGER.info("Dashboard view request compleated.");
		return new ResponseEntity<DashboardResponse>(new DashboardResponse(dashboardData, "00", "Dashboard"),
				HttpStatus.OK);
	}

	@PostMapping("/api/paymentUpload")
	@CrossOrigin(origins = MartianConstant.PROD_ORIGIN)
	public ResponseEntity<PaymentUploadResponse> uploadPaymentFile(@RequestParam("file") MultipartFile paymentFile) {
		MARS_LOGGER.info("Payment upload request recieved.");
		martianService.uploadPayment(paymentFile);
		PaymentUploadResponse response = new PaymentUploadResponse("00", "Success");
		MARS_LOGGER.info("Payment upload request compleated.");
		return new ResponseEntity<PaymentUploadResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/api/resetPassword")
	@CrossOrigin(origins = MartianConstant.PROD_ORIGIN)
	public ResponseEntity<ResetPasswordResponse> changePassword(@RequestBody @Valid ResetPasswordData passwordData) {
		MARS_LOGGER.info("Reset password request recieved.");
		userService.updatePassword(passwordData.getNewPassword());
		MARS_LOGGER.info("Reset password request compleated.");
		return new ResponseEntity<ResetPasswordResponse>(new ResetPasswordResponse("00", "ResetPasswordSuccess"),
				HttpStatus.OK);
	}

	@GetMapping("/api/treeList")
	@CrossOrigin(origins = MartianConstant.PROD_ORIGIN)
	public ResponseEntity<TreeListResponse> listNodes() {
		MARS_LOGGER.info("Tree List view request recieved.");
		List<NodeData> nodeDatas = martianService.getChildList(SecurityContextHelper.getCurrentUserName());
		MARS_LOGGER.info("Tree List view request compleated.");
		return new ResponseEntity<TreeListResponse>(
				new TreeListResponse(nodeDatas, "00", "ListSuccess"), HttpStatus.OK);
	}

}
