package com.yunfeng.lucky.dto;

import java.util.List;

/**
 * 激活码领取详情
 * 
 * @author CurlyMaple
 * 
 */
public class DtailDto {
	private int pid;// 道具id
	private String name;
	private int cost;// 消耗y币
	private String time = "00:00:00";// 倒计时
	private String url;// 图片地址
	private int totalCount;// 总份数
	private int alreadyCount;// 已领取的份数
	private int size = 0;// 领取的总人数
	private String lastUserName = "";// 最后一次领取的用户名
	private List<CollectDto> totalUser;// 领取的所有人

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getAlreadyCount() {
		return alreadyCount;
	}

	public void setAlreadyCount(int alreadyCount) {
		this.alreadyCount = alreadyCount;
	}

	public String getLastUserName() {
		return lastUserName;
	}

	public void setLastUserName(String lastUserName) {
		this.lastUserName = lastUserName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public List<CollectDto> getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(List<CollectDto> totalUser) {
		this.totalUser = totalUser;
	}

}
