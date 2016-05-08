package org.aiming.entity;

import org.springframework.format.annotation.DateTimeFormat;

public class Label {
	private int id;  //唯一空气滤芯标识
	
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private String activate_date; //激活绑定时间
	
	private int type;  //滤芯类型
	
	private int ac_id;  //对应空调ID号
	
	@DateTimeFormat(pattern="hh:mm:ss")
	private String cumulative_time;  //上次清洗后累计使用时间
	
	private int washing_count;  //清洗次数

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivate_date() {
		return activate_date;
	}

	public void setActivate_date(String activate_date) {
		this.activate_date = activate_date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAc_id() {
		return ac_id;
	}

	public void setAc_id(int ac_id) {
		this.ac_id = ac_id;
	}

	public String getCumulative_time() {
		return cumulative_time;
	}

	public void setCumulative_time(String cumulative_time) {
		this.cumulative_time = cumulative_time;
	}

	public int getWashing_count() {
		return washing_count;
	}

	public void setWashing_count(int washing_count) {
		this.washing_count = washing_count;
	}
}
