package com.su.model;

import java.util.ArrayList;
import java.util.List;

public class ProductionModel {
	private List<String> imageList;
	public List<String> getImageList() {
		return imageList;
	}
	public void setImageList(ArrayList<String> imageList) {
		this.imageList = (List<String>)imageList.clone();
	}
	
	private String title;
	private int voteCount;
	private String description;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	private String headurl;
	public String getHeadurl() {
		return headurl;
	}
	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}
	private int productionid;
	public int getProductionid() {
		return productionid;
	}
	public void setProductionid(int productionid) {
		this.productionid = productionid;
	}
}
