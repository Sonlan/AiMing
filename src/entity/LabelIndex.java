package org.jymf.entity;

import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 标签索引
 * @author Administrator
 *
 */
public class LabelIndex {

	private BigDecimal id;//id
	private BigDecimal companyId;//所属企业id
	private BigDecimal startTid;//起始标签号
	private BigDecimal endTid;//终止标签号
	private BigDecimal status;//状态
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String createDate;//创建日期
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getCompanyId() {
		return companyId;
	}
	public void setCompanyId(BigDecimal companyId) {
		this.companyId = companyId;
	}
	public BigDecimal getStartTid() {
		return startTid;
	}
	public void setStartTid(BigDecimal startTid) {
		this.startTid = startTid;
	}
	public BigDecimal getEndTid() {
		return endTid;
	}
	public void setEndTid(BigDecimal endTid) {
		this.endTid = endTid;
	}
	public BigDecimal getStatus() {
		return status;
	}
	public void setStatus(BigDecimal status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
