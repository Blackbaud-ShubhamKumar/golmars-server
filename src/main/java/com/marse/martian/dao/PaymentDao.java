package com.marse.martian.dao;

import java.util.List;

import com.marse.martian.entities.Payment;
import com.marse.martian.exceptions.MartianDataException;

public interface PaymentDao extends BaseDao {
	
	public void saveAll(List<Payment> payments)throws MartianDataException;

}
