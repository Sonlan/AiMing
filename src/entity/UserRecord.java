package org.jymf.entity;

import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户查询记录
 * @author zmh
 *
 */
public class UserRecord {
	
	private BigDecimal id;//记录id
	private String userid = "0";//用户id
	private BigDecimal playid = new BigDecimal(0);   //活动id
	private BigDecimal companyid = new BigDecimal(0);//公司id
	private BigDecimal prdid = new BigDecimal(0);//产品id
	private String prdimgurl = "";//产品图片url
	private String prdname = "";//产品名称
	private String labelid = "";//追溯码
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String firsttime = "";//首次消费时间
	private String prdshowurl = "";//产品展示url
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String createtime = "";//创建日期
	private int state = 0;//奖品状态
	private String playdetail = "";//活动记录


	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
    public BigDecimal getPlayid() {
        return playid;
    }
    public void setPlayid(BigDecimal playid) {
        this.playid = playid;
    }
    public BigDecimal getCompanyid() {
		return companyid;
	}
	public void setCompanyid(BigDecimal companyid) {
		this.companyid = companyid;
	}
	public BigDecimal getPrdid() {
		return prdid;
	}
	public void setPrdid(BigDecimal prdid) {
		this.prdid = prdid;
	}
	public String getPrdimgurl() {
		return prdimgurl;
	}
	public void setPrdimgurl(String prdimgurl) {
		this.prdimgurl = prdimgurl;
	}
	public String getPrdname() {
		return prdname;
	}
	public void setPrdname(String prdname) {
		this.prdname = prdname;
	}
	public String getLabelid() {
		return labelid;
	}
	public void setLabelid(String labelid) {
		this.labelid = labelid;
	}
	public String getFirsttime() {
		return firsttime;
	}
	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}
	public String getPrdshowurl() {
		return prdshowurl;
	}
	public void setPrdshowurl(String prdshowurl) {
		this.prdshowurl = prdshowurl;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public String getPlaydetail() {
		return playdetail;
	}
	public void setPlaydetail(String playdetail) {
		this.playdetail = playdetail;
	}
}
