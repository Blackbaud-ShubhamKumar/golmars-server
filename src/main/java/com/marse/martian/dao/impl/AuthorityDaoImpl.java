package com.marse.martian.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marse.martian.dao.AuthorityDao;
import com.marse.martian.entities.Authority;
import com.marse.martian.exceptions.MartianDataException;
import com.marse.martian.repos.base.AuthorityRepository;

@Repository
public class AuthorityDaoImpl extends BaseDaoImpl implements AuthorityDao {

	@Autowired
	private AuthorityRepository authRepo;

	@Override
	public List<Authority> findAllAuthorities() throws MartianDataException {
		try {
			return authRepo.findAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new MartianDataException("Error while saving node", e);
		}

	}

}
