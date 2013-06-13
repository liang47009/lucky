package com.yunfeng.lucky.dto;

/**
 * 上传道具form
 * 
 * @author CurlyMaple
 * 
 */
public class PropsForm {
	private int id;
	private String name;
	private String link;
	private String activation;
	private String description;
	private short totalCount;
	private String cost;
	private int price;
	private String start;
	private short hour;
	private short minute;
	private String imgUrl;

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(short totalCount) {
		this.totalCount = totalCount;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public short getHour() {
		return hour;
	}

	public void setHour(short hour) {
		this.hour = hour;
	}

	public short getMinute() {
		return minute;
	}

	public void setMinute(short minute) {
		this.minute = minute;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "PropsForm [name=" + name + ", link=" + link + ", activation="
				+ activation + ", description=" + description + ", totalCount="
				+ totalCount + ", cost=" + cost + ", start=" + start
				+ ", hour=" + hour + ", minute=" + minute + "]";
	}
}
