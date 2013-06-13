package com.yunfeng.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.yunfeng.lucky.dto.Duration;

/**
 * 日期工具
 * 
 * @author xialiangliang
 * 
 */
public class DateUtil {

	private static final Calendar CAL = Calendar.getInstance();

	private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat(
			"yyyy:MM:dd HH:mm:ss");

	/**
	 * 将long类型的时间转化为string
	 * 
	 * @param time
	 * @return
	 */
	public static final String f(long time) {
		return DATEFORMAT.format(new Date(time));
	}

	public static final Duration format(long start, long end) {
		int d = (int) (end - start) / 1000;
		int h = d / 3600;
		int m = (d - h * 3600) / 60;
		int s = d - m * 60 - h * 3600;
		return new Duration(h, m, s);
	}

	public static int getHour(long time) {
		CAL.setTimeInMillis(time);
		int hour = CAL.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	public static int getMinute(long time) {
		CAL.setTimeInMillis(time);
		int minute = CAL.get(Calendar.MINUTE);
		return minute;
	}

	public static int getSecond(long time) {
		CAL.setTimeInMillis(time);
		int second = CAL.get(Calendar.SECOND);
		return second;
	}

	public static int getHour() {
		int hour = CAL.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	public static int getMinute() {
		int minute = CAL.get(Calendar.MINUTE);
		return minute;
	}

	public static int getSecond() {
		int second = CAL.get(Calendar.SECOND);
		return second;
	}

	public static final Duration format(long time) {
		long now = System.currentTimeMillis();
		int d = (int) (time - now) / 1000;
		int h = d / 3600;
		int m = (d - h * 3600) / 60;
		int s = d - m * 60 - h * 3600;
		return new Duration(h, m, s);
	}

	public static final String parse(int i) {
		return i < 10 ? "0" + i : "" + i;
	}

	public static void main(String[] args) {
		long now2 = 1370830742000l;
		long time2 = 1370723427078l;
		System.out.println(format(time2, now2));
		// System.out.println(format(1370830742000l));
		// long now = System.currentTimeMillis();
		// long time = now + 10 * 60 * 1000;
		// System.out.println(now + "--" + time);
		// System.out.println(format(time));
		//
		long now1 = 1370830742000l;
		System.out.println(DATEFORMAT.format(new Date(now1)));
		long time1 = 1370723427078l;
		System.out.println(DATEFORMAT.format(new Date(time1)));
		//
		//
		// int d = (int) (now1 - time1) / 1000;
		// System.out.println(d / 60 / 60);
		// int h = d / 3600;
		// int m = (d - h * 3600) / 60;
		// int s = d - m * 60 - h * 3600;
		// System.out.println(h + ":" + m + ":" + s);
		// System.out.println(getHour(1370750056265l));
		// System.out.println(getMinute(1370750056265l));
		// System.out.println(getSecond(1370750056265l));
		// System.out.println(getHour(1370750952296l));
		// System.out.println(getMinute(1370750952296l));
		// System.out.println(getSecond(1370750952296l));
	}

	/**
	 * 当前月份的最后一天
	 * 
	 * @param format
	 * @return
	 */
	public static long curMonthLastDay() {
		Calendar ca = Calendar.getInstance();// 获取当前日期
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		return ca.getTimeInMillis();
	}

	/**
	 * 当前月份的第一天
	 * 
	 * @param format
	 * @return
	 */
	public static long curMonthFirstDay() {
		Calendar ca = Calendar.getInstance();// 获取当前日期
		ca.add(Calendar.MONTH, 0);
		ca.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		return ca.getTimeInMillis();
	}

	/**
	 * 上个月份的第一天
	 * 
	 * @param format
	 * @return
	 */
	public static long lastMonthLastDay() {
		Calendar ca = Calendar.getInstance();// 获取当前日期
		ca.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		return ca.getTimeInMillis();
	}

	/**
	 * 上个月份的第一天
	 * 
	 * @param format
	 * @return
	 */
	public static long lastMonthFirstDay() {
		Calendar ca = Calendar.getInstance();// 获取当前日期
		ca.add(Calendar.MONTH, -1);
		ca.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		return ca.getTimeInMillis();
	}

	/**
	 * 获取当天起始时间
	 * 
	 * @return
	 */
	public static Long getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime();
	}

	/**
	 * 获取当天结束时间
	 * 
	 * @return
	 */
	public static Long getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime().getTime();
	}

	/**
	 * 是否过期
	 * 
	 * @param end
	 * @return
	 */
	public static boolean expired(long end) {
		return System.currentTimeMillis() > end;
	}

	private static final long HOUR24 = 24 * 60 * 60 * 1000;

	public static boolean in24Hour(long start) {
		return System.currentTimeMillis() + HOUR24 > start;
	}

	/**
	 * yyyy:MM:dd HH:mm
	 * 
	 * @param start
	 * @return
	 * @throws ParseException
	 */
	public static long format(String start) throws ParseException {
		Date date = DATEFORMAT.parse(start);
		return date.getTime();
	}
}
