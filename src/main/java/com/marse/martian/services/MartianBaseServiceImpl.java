package com.marse.martian.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.marse.martian.constants.MartianConstant;
import com.marse.martian.dao.AuthorityDao;
import com.marse.martian.dao.NodeDao;
import com.marse.martian.dao.PaymentDao;
import com.marse.martian.dto.DashboardData;
import com.marse.martian.dto.NodeData;
import com.marse.martian.dto.PaymentFileData;
import com.marse.martian.dto.ProfileData;
import com.marse.martian.dto.RegisterationData;
import com.marse.martian.dto.WalletData;
import com.marse.martian.entities.AuthDetail;
import com.marse.martian.entities.BankDetail;
import com.marse.martian.entities.Node;
import com.marse.martian.entities.Payment;
import com.marse.martian.entities.Profile;
import com.marse.martian.entities.TransStatus;
import com.marse.martian.entities.Wallet;
import com.marse.martian.exceptions.MartianDataException;
import com.marse.martian.exceptions.MartianServiceException;
import com.marse.martian.security.filters.SHA256PasswordEncoder;
import com.marse.martian.util.DateUtil;
import com.marse.martian.util.SecurityContextHelper;

@Service
public class MartianBaseServiceImpl implements MartianBaseService {

	@Autowired
	private SHA256PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityDao authorityDao;

	@Autowired
	private NodeDao nodeDao;

	@Autowired
	private PaymentDao paymentDao;

	@Override
	@Transactional(readOnly = true)
	public NodeData getTree(String myId) throws MartianServiceException {
		Long nodeId = Long.parseLong(myId.substring(2, myId.length()));
		try {
			Node dbNode = nodeDao.findById(nodeId);
			return getTreeDto(dbNode);
		} catch (MartianDataException e) {
			e.printStackTrace();
			throw new MartianServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public String register(RegisterationData registeration) throws MartianServiceException {
		try {
			Node node = new Node();
			node.setChildCount(0L);

			// Updating parent
			Long parentId = Long
					.parseLong(registeration.getSponsorId().substring(2, registeration.getSponsorId().length()));
			Node parent = nodeDao.findById(parentId);
			node.setParent(parent);

			node.setAcceptedTnc(registeration.isAcceptedTnc());

			// Adding login details
			AuthDetail authDetail = populateAuthData(registeration, node);

			// Adding bank detail
			populateBankData(registeration, node);

			node.setLeft(null);
			node.setRight(null);

			node.setPosition(registeration.getPosition());

			// Adding profile data
			populateProfileData(registeration, node);

			node.setWallet(null);
			node.setPayment(null);

			// Saving node
			nodeDao.saveNode(node);

			// Updating user name with primary key of the node
			authDetail.setUsername(node.getSponsorId());
			nodeDao.saveNode(node);

			if (MartianConstant.LEFT_NODE.equalsIgnoreCase(node.getPosition())) {
				parent.setLeft(node);
			} else if (MartianConstant.RIGHT_NODE.equalsIgnoreCase(node.getPosition())) {
				parent.setRight(node);
			}

			nodeDao.saveNode(parent);

			nodeDao.getEntityManager().flush();

			// Updating tree
			nodeDao.updateTree(parentId);

			return String.valueOf(node.getId());
		} catch (MartianDataException e) {
			throw new MartianServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<WalletData> getWalletDataForUser(String userName) throws MartianServiceException {
		Long userId = Long.parseLong(userName.substring(2, userName.length()));
		try {
			return nodeDao.findById(userId).getWallet().stream().map(wallet -> {
				WalletData dto = new WalletData();
				dto.setAmount(wallet.getAmount());
				dto.setTransStatus(wallet.getStatus().getValue());
				dto.setWalletType(wallet.getWalletType().getValue());
				dto.setTransType(wallet.getTransType().getValue());
				dto.setClearBalancePopDate(DateUtil.calenderToString(wallet.getCreatedOn()));
				dto.setMainBalancePopDate(DateUtil.calenderToString(wallet.getCreatedOn()));
				return dto;
			}).collect(Collectors.toList());
		} catch (MartianDataException e) {
			throw new MartianServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ProfileData getProfileForUser(String userName) throws MartianServiceException {
		Long userId = Long.parseLong(userName.substring(2, userName.length()));
		try {
			Profile profile = nodeDao.findById(userId).getProfile();
			ProfileData dto = new ProfileData();
			dto.setAadharNumber(profile.getAadharNumber());
			dto.setAddress(profile.getAddress());
			dto.setCity(profile.getCity());
			dto.setDob(profile.getDateOfBirth());
			dto.setEmail(profile.getEmail());
			dto.setFatherHushName(profile.getFatherOrHusbandName());
			dto.setGender(profile.getGender());
			dto.setGuestName(profile.getFirstName());
			dto.setOccupation(profile.getOccupation());
			dto.setPhone(profile.getPhoneNumber());
			dto.setPinCode(profile.getPinCode());
			return dto;
		} catch (MartianDataException e) {
			throw new MartianServiceException(e.getMessage());
		}
	}

	@Override
	public DashboardData getDashboardForUser(String userName) throws MartianServiceException {
		Long userId = Long.parseLong(userName.substring(2, userName.length()));
		try {
			DashboardData data = new DashboardData();
			Node node = nodeDao.findById(userId);
			data.setNodeCount(String.valueOf(node.getChildCount()));
			data.setJoiningDate(DateUtil.calenderToString(node.getCreatedOn()));
			data.setPromoter(node.getCreatedBy());
			data.setUserId(MartianConstant.ID_PREFIX + node.getId());
			data.setUserName(
					node.getProfile().getFirstName() + MartianConstant.SPACE + node.getProfile().getLastName());
			return data;
		} catch (MartianDataException e) {
			throw new MartianServiceException(e.getMessage());
		}
	}

	public List<NodeData> getChildList(String myId) throws MartianServiceException {
		Long nodeId = Long.parseLong(myId.substring(2, myId.length()));
		try {
			Node dbNode = nodeDao.findById(nodeId);
			return Arrays.asList(getTreeListDto(dbNode.getLeft()), getTreeListDto(dbNode.getRight())).stream()
					.filter(Objects::nonNull).collect(Collectors.toList());
		} catch (MartianDataException e) {
			e.printStackTrace();
			throw new MartianServiceException(e.getMessage());
		}
	}

	private void populateProfileData(RegisterationData registeration, Node node) {
		Profile profile = new Profile();
		profile.setAadharNumber(registeration.getAadharNumber());
		profile.setEmail(registeration.getEmailId());
		profile.setFirstName(registeration.getFirstName());
		profile.setLastName(registeration.getLastName());
		profile.setGender(MartianConstant.MALE);
		profile.setPhoneNumber(registeration.getPhoneNumber());
		node.setProfile(profile);
	}

	private void populateBankData(RegisterationData registeration, Node node) {
		BankDetail bankDetail = new BankDetail();
		bankDetail.setAccountNumber(registeration.getAccountNo());
		bankDetail.setBankName(registeration.getBank());
		bankDetail.setIfscCode(registeration.getIfscCode());
		bankDetail.setPassImageData(null);
		bankDetail.setPassImageName(null);
		bankDetail.setPanNumber(registeration.getPanCard());
		node.setBankDetail(bankDetail);
	}

	private AuthDetail populateAuthData(RegisterationData registeration, Node node) throws MartianDataException {
		AuthDetail authDetail = new AuthDetail();
		authDetail.setPassword(passwordEncoder.encode(registeration.getPassword()));
		authDetail.setUsername(node.getSponsorId());
		authDetail.setAuthority(authorityDao.findAllAuthorities());
		authDetail.setEnabled(Boolean.TRUE);
		node.setAuthDetail(authDetail);
		return authDetail;
	}

	private NodeData getTreeDto(Node node) {
		NodeData nodeDto = null;
		if (null != node) {

			nodeDto = new NodeData();
			nodeDto.setSponsor(node.getSponsorId() != null ? node.getSponsorId() : MartianConstant.ROOT_NODE);
			nodeDto.setMyId(String.valueOf(node.getId()));
			Profile profile = node.getProfile();
			nodeDto.setName(profile.getFirstName() + "<br/>" + profile.getLastName());

			nodeDto.setLeft(getTreeDto(node.getLeft()));
			nodeDto.setRight(getTreeDto(node.getRight()));
		}
		return nodeDto;
	}

	private NodeData getTreeListDto(Node node) {
		NodeData nodeDto = null;
		if (null != node) {
			nodeDto = new NodeData();
			nodeDto.setSponsor(node.getParent().getSponsorId());
			nodeDto.setMyId(String.valueOf(node.getId()));
			Profile profile = node.getProfile();
			nodeDto.setName(profile.getFirstName() + " " + profile.getLastName());
			nodeDto.setPos(node.getPosition());
		}
		return nodeDto;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void uploadPayment(MultipartFile file) throws MartianServiceException {
		try {
			List<PaymentFileData> paymentData = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			paymentData = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
			br.close();
			if (CollectionUtils.isEmpty(paymentData)) {
				throw new MartianServiceException("No valid data in file..");
			} else {
				// Process valid data
				Map<Long, List<Payment>> filePaymentsMap = paymentData.stream().map(data -> {
					Payment p = new Payment();
					p.setNode(new Node(data.getUserRegId()));
					p.setPaymentDate(data.getPaymentDate());
					p.setPaymentMode(data.getPaymentMode());
					p.setTransactionNo(data.getTransNumber());
					p.setAmount(data.getAmount());
					return p;
				}).collect(Collectors.groupingBy(p -> p.getNode().getId()));

				List<Node> nodes = nodeDao.findAllById(filePaymentsMap.keySet());

				// Looping for each node
				nodes.forEach(node -> {
					List<Payment> filePayments = filePaymentsMap.get(node.getId());
					List<Payment> dbPayments = node.getPayment();

					List<Wallet> dbWallets = node.getWallet();

					if (CollectionUtils.isEmpty(dbPayments)) {
						dbPayments = filePayments;
					} else {
						dbPayments.addAll(filePayments);
					}
					int totalPaymentEntries = dbPayments.size();

					if (totalPaymentEntries > dbWallets.size()) {
						throw new MartianServiceException("Invalid entries provided for node - GM" + node.getId());
					} else {

						// Setting wallet status to paid
						dbWallets.stream()
								.filter(w -> w.getStatus().getValue().equalsIgnoreCase(TransStatus.PENDING.getValue()))
								.forEach(w -> {
									for (int i = 0; i < filePayments.size(); i++) {
										w.setStatus(TransStatus.PAID);
									}
								});

						// Updating payment entries to node
						node.setPayment(dbPayments);
					}
				});
			}
		} catch (Exception e) {
			throw new MartianServiceException(e.getMessage());
		}
	}

	private Function<String, PaymentFileData> mapToItem = (line) -> {
		String[] data = line.split(MartianConstant.COMMA);

		// Basic validation
		if (data == null || data.length < 5) {
			throw new MartianServiceException("Invalid data in one of the row..");
		}

		// User ID validation
		PaymentFileData item = new PaymentFileData();
		Long userId = Long.parseLong(data[0].substring(2, data[0].length()));
		try {
			Node node = nodeDao.findById(userId);
			if (null == node) {
				throw new MartianServiceException(data[0] + " - Id is not valid in one of the row..");
			}

		} catch (MartianDataException e) {
			throw new MartianServiceException(data[0] + " - Id is not valid in one of the row..");
		}
		item.setUserRegId(userId);

		// Payment date validation
		Calendar paydate = DateUtil.stringToCalender(data[1]);
		if (paydate == null) {
			throw new MartianServiceException(data[1] + " - Date is not valid in one of the row..");
		}
		item.setPaymentDate(paydate);

		// Setting payment mode
		if (StringUtils.isEmpty(data[2])) {
			throw new MartianServiceException(data[2] + " - Payment mode is not valid in one of the row..");
		}
		item.setPaymentMode(data[2]);

		// Setting Transaction number
		if (StringUtils.isEmpty(data[3])) {
			throw new MartianServiceException(data[2] + " - Transaction Number mode is not valid in one of the row..");
		}
		item.setTransNumber(data[3]);

		// Setting amount
		if (StringUtils.isEmpty(data[4])) {
			throw new MartianServiceException(data[2] + " - Amount is not valid in one of the row..");
		}
		Float amount = Float.parseFloat(data[4].trim());
		if (Objects.equals(amount, PaymentFileData.DEVIDER)) {
			item.setAmount(Float.parseFloat(data[4].trim()));
		} else {
			throw new MartianServiceException(
					data[2] + " - Amount is not valid in one of the row.. requires multiple of 900.");
		}
		return item;
	};

}
