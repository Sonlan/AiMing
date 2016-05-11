package org.aiming.entity;


import org.springframework.format.annotation.DateTimeFormat;

public class Label {
	private String id;  //唯一空气滤芯标识,13位，前两位为空调id(ac_id),第三位为type，初级中级
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String activate_date; //激活绑定时间
	
	private String cumulative_time;  //上次清洗后累计使用时间
	
	private int washing_count;  //清洗次数

	private int inuse ;  //是否在使用
	
	private int alive;  //是否已报废
	
	public int getInuse() {
		return inuse;
	}

	public void setInuse(int inuse) {
		this.inuse = inuse;
	}

	public int getAlive() {
		return alive;
	}

	public void setAlive(int alive) {
		this.alive = alive;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActivate_date() {
		return activate_date;
	}

	public void setActivate_date(String activate_date) {
		this.activate_date = activate_date;
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
