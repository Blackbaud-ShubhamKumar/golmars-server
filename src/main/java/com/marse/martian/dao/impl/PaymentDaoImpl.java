package com.marse.martian.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marse.martian.dao.PaymentDao;
import com.marse.martian.entities.Payment;
import com.marse.martian.exceptions.MartianDataException;
import com.marse.martian.exceptions.MartianServiceException;
import com.marse.martian.repos.base.PaymentRepository;

@Repository
public class PaymentDaoImpl extends BaseDaoImpl implements PaymentDao {

	@Autowired
	private PaymentRepository repository;
	
	@Override
	public void saveAll(List<Payment> payments) throws MartianDataException {
		try {
			repository.saveAll(payments);
		}catch (PersistenceException e) {
			throw new MartianServiceException("Error while saving payments.",e);
		}
	}

}
