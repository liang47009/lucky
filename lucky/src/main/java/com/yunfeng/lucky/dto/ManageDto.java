package com.yunfeng.lucky.dto;

public class ManageDto {
	private int id;
	private String ct;// 创建时间
	private String name;// 激活码名称
	private String start;// 开始时间
	private int state;// 状态(0:尚未开始, 1:正在进行, 2:已经完成)
	private int count;// 参与人数

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
