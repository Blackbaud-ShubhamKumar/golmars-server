package com.marse.martian.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.marse.martian.dto.DashboardData;
import com.marse.martian.dto.NodeData;
import com.marse.martian.dto.ProfileData;
import com.marse.martian.dto.RegisterationData;
import com.marse.martian.dto.WalletData;
import com.marse.martian.exceptions.MartianServiceException;

public interface MartianBaseService {
	
	public String register(RegisterationData registeration)throws MartianServiceException;
	
	public NodeData getTree(String myId)throws MartianServiceException;

	public List<WalletData> getWalletDataForUser(String userName) throws MartianServiceException;
	
	public ProfileData getProfileForUser(String myId)throws MartianServiceException;
	
	public DashboardData getDashboardForUser(String myId)throws MartianServiceException;
	
	public void uploadPayment(MultipartFile file)throws MartianServiceException;
	
	public List<NodeData> getChildList(String myId)throws MartianServiceException;
}
