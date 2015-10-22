package com.su.model;

public class TaskModel {
private int id;
private int type;
private String descripteion;
private String start_date;
private String end_date;
private String reward;
private int user_id;
private int status;
private String gmt_created;
private String title;
private String name ;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public String getDescripteion() {
	return descripteion;
}
public void setDescripteion(String descripteion) {
	this.descripteion = descripteion;
}
public String getStart_date() {
	return start_date;
}
public void setStart_date(String start_date) {
	this.start_date = start_date;
}
public String getEnd_date() {
	return end_date;
}
public void setEnd_date(String end_date) {
	this.end_date = end_date;
}
public String getReward() {
	return reward;
}
public void setReward(String reward) {
	this.reward = reward;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getGmt_created() {
	return gmt_created;
}
public void setGmt_created(String gmt_created) {
	this.gmt_created = gmt_created;
}


}
