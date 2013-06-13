package com.yunfeng.lucky.dto;

/**
 * 道具
 * 
 * @author CurlyMaple
 * 
 */
public class PropsDto {
	private int id = 1;
	private int cast = 1;
	private String url = "1";
	private String name = "1";
	private String price = "1";
	private String count = "1";
	private String duration = "1";
	private String username = "1";
	private String createtime = "1";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getCast() {
		return cast;
	}

	public void setCast(int cast) {
		this.cast = cast;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
