package com.marse.martian.dao;

import java.util.List;

import com.marse.martian.entities.Authority;
import com.marse.martian.exceptions.MartianDataException;

public interface AuthorityDao extends BaseDao {
	
	public List<Authority> findAllAuthorities() throws MartianDataException;

}
