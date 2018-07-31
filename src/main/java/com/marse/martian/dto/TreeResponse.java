package com.marse.martian.dto;

public class TreeResponse extends MartianResponse {

	private NodeData node;

	public TreeResponse() {
		super();
	}

	public TreeResponse(NodeData node, String code, String message) {
		super(code, message);
		this.node = node;

	}

	public NodeData getNode() {
		return node;
	}

	public void setNode(NodeData node) {
		this.node = node;
	}

}
