package com.yunfeng.lucky.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 领取记录
 * 
 * @author CurlyMaple
 * 
 */
@Entity
public class CollectRecords {
	private int id;// 记录id
	private int uid;// 用户id
	private int pid;// 领取的道具id
	private String name;// 领取者名称
	private long lasttime;// 最后一次领取时间
	private short count;// 领取的数量
	private boolean success;// 领取成功

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

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

	public short getCount() {
		return count;
	}

	public void setCount(short count) {
		this.count = count;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public long getLasttime() {
		return lasttime;
	}

	public void setLasttime(long lasttime) {
		this.lasttime = lasttime;
	}

	@Override
	public String toString() {
		return "CollectRecords [id=" + id + ", uid=" + uid + ", pid=" + pid
				+ ", name=" + name + ", count=" + count + ", success="
				+ success + "]";
	}
}