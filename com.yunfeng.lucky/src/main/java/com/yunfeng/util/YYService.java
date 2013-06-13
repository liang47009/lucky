package com.yunfeng.util;

import java.util.Date;

public class YYService {
	/**
	 * 用户信息
	 */
	private static final String USERINFO = "http://service.open.yy.com/getUserInfo?";
	/**
	 * 用户当前所在频道信息
	 */
	private static final String USERCURCH = "http://service.open.yy.com/getUserCurChInfo?";

	private static final String APPID = "111135";
	private static final String APPKEY = "de85cdf3517ee2cbe15b69e5af41b1a251709216";
	private static final String VERIFY = Encrypt.SHA1(APPKEY);

	/**
	 * 用户信息
	 */
	public static String getUserInfo(int uid) {
		String time = ConstantUtil.formatId(new Date());
		String md5 = Encrypt.MD5(APPID + uid + time + VERIFY);
		StringBuilder sb = new StringBuilder(USERINFO);
		sb.append("appid=");
		sb.append(APPID);
		sb.append("&uid=");
		sb.append(uid);
		sb.append("&time=");
		sb.append(time);
		sb.append("&verify=");
		sb.append(md5);
		return HttpGet.myDoGet(sb.toString());
	}

	/**
	 * 用户当前所在频道信息
	 */
	public static String getUserCurCh(int uid) {
		String time = ConstantUtil.formatId(new Date());
		String md5 = Encrypt.MD5(APPID + uid + time + VERIFY);
		StringBuilder sb = new StringBuilder(USERCURCH);
		sb.append("appid=");
		sb.append(APPID);
		sb.append("&uid=");
		sb.append(uid);
		sb.append("&time=");
		sb.append(time);
		sb.append("&verify=");
		sb.append(md5);
		return HttpGet.myDoGet(sb.toString());
	}

	public static void main(String[] args) {
		System.out.println(getUserInfo(13543252));
	}
}