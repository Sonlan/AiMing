package org.jymf.entity;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 活动详情信息
 * @author zmh
 *
 */
public class Play {
	
   private BigDecimal id;//活动id
   private BigDecimal companyid;//公司id
   private String areaid;//区域id
   private String name;//活动名称
   private BigDecimal level;//级别
   private String imgurl;//活动图片url
   private String description;//活动简介
   private BigDecimal state;//活动状态
   private String creator;//创建人
   private String icontent;//中奖信息
   private String idrawurl;//活动内容url
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private String createtime;//创建日期
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private String duration;//截止日期
   
   
public BigDecimal getId() {
	return id;
}
public void setId(BigDecimal id) {
	this.id = id;
}
public BigDecimal getCompanyid() {
	return companyid;
}
public void setCompanyid(BigDecimal companyid) {
	this.companyid = companyid;
}
public String getAreaid() {
	return areaid;
}
public void setAreaid(String areaid) {
	this.areaid = areaid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public BigDecimal getLevel() {
	return level;
}
public void setLevel(BigDecimal level) {
	this.level = level;
}
public String getImgurl() {
	return imgurl;
}
public void setImgurl(String imgurl) {
	this.imgurl = imgurl;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public BigDecimal getState() {
	return state;
}
public void setState(BigDecimal state) {
	this.state = state;
}
public String getCreator() {
	return creator;
}
public void setCreator(String creator) {
	this.creator = creator;
}
public String getIcontent() {
	return icontent;
}
public void setIcontent(String icontent) {
	this.icontent = icontent;
}
public String getIdrawurl() {
	return idrawurl;
}
public void setIdrawurl(String idrawurl) {
	this.idrawurl = idrawurl;
}
public String getCreatetime() {
	return createtime;
}
public void setCreatetime(String createtime) {
	this.createtime = createtime;
}
public String getDuration() {
	return duration;
}
public void setDuration(String duration) {
	this.duration = duration;
}
   
}
