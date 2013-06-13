package com.yunfeng.lucky.dto;

import com.yunfeng.util.DateUtil;

/**
 * 
 * @author CurlyMaple
 * 
 */
public class Duration {
	private int hour;
	private int mint;
	private int secd;

	public Duration(int h, int m, int s) {
		this.hour = h;
		this.mint = m;
		this.secd = s;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMint() {
		return mint;
	}

	public void setMint(int mint) {
		this.mint = mint;
	}

	public int getSecd() {
		return secd;
	}

	public void setSecd(int secd) {
		this.secd = secd;
	}

	@Override
	public String toString() {
		return DateUtil.parse(hour) + ":" + DateUtil.parse(mint) + ":"
				+ DateUtil.parse(secd);
	}

}