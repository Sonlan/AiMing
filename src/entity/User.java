package org.jymf.entity;

import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户基本信息
 * @author zmh
 *
 */
public class User {
	
	private String userid;//用户id
	private String password;//密码
	private BigDecimal uservip;//用户级别
	private BigDecimal gender;//性别
	private String professional;//职业
	private String mobile;//手机号
	private String city;//城市
	private String age;//年龄段
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String createtime;//创建日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private String updatetime;//修改日期
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BigDecimal getUservip() {
		return uservip;
	}
	public void setUservip(BigDecimal uservip) {
		this.uservip = uservip;
	}
	public BigDecimal getGender() {
		return gender;
	}
	public void setGender(BigDecimal gender) {
		this.gender = gender;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

}
