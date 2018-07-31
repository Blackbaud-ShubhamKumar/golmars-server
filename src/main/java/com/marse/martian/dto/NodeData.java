package com.marse.martian.dto;

public class NodeData {
	
	private String name;
	
	private String sponsor;
	
	private String myId;
	
	private String date;
	
	private String leftBusiness;
	
	private String rightBusiness;
	
	private String status;
	
	private NodeData left;
	
	private NodeData right;

	private String pos;
 
	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public NodeData getLeft() {
		return left;
	}

	public void setLeft(NodeData left) {
		this.left = left;
	}

	public NodeData getRight() {
		return right;
	}

	public void setRight(NodeData right) {
		this.right = right;
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLeftBusiness() {
		return leftBusiness;
	}

	public void setLeftBusiness(String leftBusiness) {
		this.leftBusiness = leftBusiness;
	}

	public String getRightBusiness() {
		return rightBusiness;
	}

	public void setRightBusiness(String rightBusiness) {
		this.rightBusiness = rightBusiness;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
