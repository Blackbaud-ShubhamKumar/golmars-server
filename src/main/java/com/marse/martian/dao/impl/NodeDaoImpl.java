package com.marse.martian.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marse.martian.dao.NodeDao;
import com.marse.martian.entities.Node;
import com.marse.martian.exceptions.MartianDataException;
import com.marse.martian.repos.base.NodeRepository;

@Repository
public class NodeDaoImpl extends BaseDaoImpl implements NodeDao {

	@Autowired
	private NodeRepository nodeRepo;

	@Override
	public void saveNode(Node node) throws MartianDataException {
		try {
			nodeRepo.save(node);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new MartianDataException("Error while saving node", e);
		}

	}

	@Override
	public Node findById(Long id) throws MartianDataException {
		try {
			return nodeRepo.findById(id).orElse(null);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new MartianDataException("Error while saving node", e);
		}
	}

	@Override
	public void updateTree(Long sponsorId) throws MartianDataException {
		try {
			int result = nodeRepo.updateTreeNodesChildCountAndProcessPayment(sponsorId);
			if (result == 0)
				throw new MartianDataException("Execution procedure fails.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MartianDataException("Error while updating tree", e);
		}
	}

	@Override
	public List<Node> findAll() throws MartianDataException {
		try {
			return nodeRepo.findAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new MartianDataException("Error while getting node", e);
		}
	}

	@Override
	public List<Node> findAllById(Set<Long> ids) throws MartianDataException {
		try {
			return nodeRepo.findAllById(ids);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new MartianDataException("Error while getting all node in ids", e);
		}
	}

}
