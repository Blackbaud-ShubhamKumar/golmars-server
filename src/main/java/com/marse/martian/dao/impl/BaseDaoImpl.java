package com.marse.martian.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.marse.martian.dao.BaseDao;

@Repository
public class BaseDaoImpl  implements BaseDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	

}
