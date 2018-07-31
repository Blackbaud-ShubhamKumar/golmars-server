package com.marse.martian.dao;

import java.util.List;
import java.util.Set;

import com.marse.martian.entities.Node;
import com.marse.martian.exceptions.MartianDataException;

public interface NodeDao extends BaseDao {

	public void saveNode(Node node) throws MartianDataException;

	public Node findById(Long id) throws MartianDataException;
	
	public void updateTree(Long id) throws MartianDataException;
	
	public List<Node> findAll()throws MartianDataException;

	public List<Node> findAllById(Set<Long> ids) throws MartianDataException;
	
}
