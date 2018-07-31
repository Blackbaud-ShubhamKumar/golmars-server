package com.marse.martian.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.marse.martian.constants.MartianConstant;

@Entity
@Table(name = "node")
/*@NamedStoredProcedureQuery(name = "updateTreeNodesChildCountAndProcessPayment", 
							procedureName = "update_tree_payment", 
							parameters = {
										@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "sponsorId")
						})*/
public class Node extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	public Node(Long id) {
		super(id);
	}
	
	public Node() {
		
	}

	@Column(name = "position")
	private String position;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "left_node")
	private Node left;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "right_node")
	private Node right;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_node")
	private Node parent;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "profile")
	private Profile profile;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "node", cascade = CascadeType.ALL)
	private List<Wallet> wallet;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "auth_detail")
	private AuthDetail authDetail;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "node", cascade = CascadeType.ALL)
	private List<Payment> payment;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_detail")
	private BankDetail bankDetail;

	@Column(name = "child_count")
	private Long childCount;

	@Column(name = "accepted_tnc" )
	private boolean acceptedTnc = Boolean.TRUE;

	public String getSponsorId() {
		return MartianConstant.ID_PREFIX + super.getId();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Wallet> getWallet() {
		return wallet;
	}

	public void setWallet(List<Wallet> wallet) {
		this.wallet = wallet;
	}

	public AuthDetail getAuthDetail() {
		return authDetail;
	}

	public void setAuthDetail(AuthDetail authDetail) {
		this.authDetail = authDetail;
	}

	public BankDetail getBankDetail() {
		return bankDetail;
	}

	public void setBankDetail(BankDetail bankDetail) {
		this.bankDetail = bankDetail;
	}

	public boolean isAcceptedTnc() {
		return acceptedTnc;
	}

	public void setAcceptedTnc(boolean acceptedTnc) {
		this.acceptedTnc = acceptedTnc;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	public Long getChildCount() {
		return childCount;
	}

	public void setChildCount(Long childCount) {
		this.childCount = childCount;
	}

}
