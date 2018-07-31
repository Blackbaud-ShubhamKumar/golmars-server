package com.marse.martian.dto;

import java.util.List;

public class TreeListResponse extends MartianResponse {

	private List<NodeData> nodes;

	public TreeListResponse() {
		super();
	}

	public TreeListResponse(List<NodeData> nodes, String code, String message) {
		super(code, message);
		this.nodes = nodes;
	}

	public List<NodeData> getNodes() {
		return nodes;
	}

	public void setNodes(List<NodeData> nodes) {
		this.nodes = nodes;
	}

}
