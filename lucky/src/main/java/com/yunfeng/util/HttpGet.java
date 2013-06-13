package com.yunfeng.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGet {

	public static String myDoGet(String urlTemp) {
		HttpURLConnection httpURLConn = null;
		try {
			String temp = "";
			URL url = new URL(urlTemp);
			httpURLConn = (HttpURLConnection) url.openConnection();
			httpURLConn.setRequestMethod("GET");
			httpURLConn.setReadTimeout(5000);
			httpURLConn.connect();
			InputStream in = httpURLConn.getInputStream();
			BufferedReader bd = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			while ((temp = bd.readLine()) != null) {
				sb.append(temp);
			}
			return sb.toString();
		} catch (Exception e) {
			Log.error("", e);
		} finally {
			if (httpURLConn != null) {
				httpURLConn.disconnect();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		String appid = "111135";
		String uid = "713958228";
		String time = "20130605180200";
		String verify = Encrypt
				.SHA1("de85cdf3517ee2cbe15b69e5af41b1a251709216");
		String md5 = Encrypt.MD5(appid + uid + time + verify);
		String urlTemp = "http://111.178.146.46:8901/regPayOrder?appid=111135&uid=713958228&time=20130605180200&verify="
				+ md5;
		System.out.println(urlTemp);
		System.out.println(HttpGet.myDoGet(urlTemp));
	}

}