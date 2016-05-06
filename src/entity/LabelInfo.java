package org.jymf.entity;

import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 企业标签信息表
 * @author Administrator
 *
 */
public class LabelInfo {

	private BigDecimal labelId;//标签号
	private BigDecimal productId;//商品类型
	private BigDecimal isPackage;//是否包标签
	private BigDecimal isProduvt;//是否商品标签
	private BigDecimal packageId;//所属包标签
	private Integer salesArea;//销售区域
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String productDate;//生产日期
	private BigDecimal batchNumber;//生产批号
	private String originAddr;//生产地
	private Integer queryCount;//查询次数
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String queryTimeFirst;//查询时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String consTimeFirst;//消费时间
	private Integer consAddrFirst;//消费地区
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String timestamp;//时间戳
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String packstamp;//打包时间戳
	private BigDecimal status;//当前状态
	private Integer terminaltype;//终端系统类型
	private BigDecimal goodId;//货单ID
	private String carNo;//车牌号
	
	
	public BigDecimal getLabelId() {
		return labelId;
	}
	public void setLabelId(BigDecimal labelId) {
		this.labelId = labelId;
	}
	public BigDecimal getProductId() {
		return productId;
	}
	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}
	public BigDecimal getIsPackage() {
		return isPackage;
	}
	public void setIsPackage(BigDecimal isPackage) {
		this.isPackage = isPackage;
	}
	public BigDecimal getIsProduvt() {
		return isProduvt;
	}
	public void setIsProduvt(BigDecimal isProduvt) {
		this.isProduvt = isProduvt;
	}
	public BigDecimal getPackageId() {
		return packageId;
	}
	public void setPackageId(BigDecimal packageId) {
		this.packageId = packageId;
	}
	public Integer getSalesArea() {
		return salesArea;
	}
	public void setSalesArea(Integer salesArea) {
		this.salesArea = salesArea;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public BigDecimal getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(BigDecimal batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getOriginAddr() {
		return originAddr;
	}
	public void setOriginAddr(String originAddr) {
		this.originAddr = originAddr;
	}
	public Integer getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}
	public String getQueryTimeFirst() {
		return queryTimeFirst;
	}
	public void setQueryTimeFirst(String queryTimeFirst) {
		this.queryTimeFirst = queryTimeFirst;
	}
	public String getConsTimeFirst() {
		return consTimeFirst;
	}
	public void setConsTimeFirst(String consTimeFirst) {
		this.consTimeFirst = consTimeFirst;
	}
	public Integer getConsAddrFirst() {
		return consAddrFirst;
	}
	public void setConsAddrFirst(Integer consAddrFirst) {
		this.consAddrFirst = consAddrFirst;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getPackstamp() {
		return packstamp;
	}
	public void setPackstamp(String packstamp) {
		this.packstamp = packstamp;
	}
	public BigDecimal getStatus() {
		return status;
	}
	public void setStatus(BigDecimal status) {
		this.status = status;
	}
	public Integer getTerminaltype() {
		return terminaltype;
	}
	public void setTerminaltype(Integer terminaltype) {
		this.terminaltype = terminaltype;
	}
	public BigDecimal getGoodId() {
		return goodId;
	}
	public void setGoodId(BigDecimal goodId) {
		this.goodId = goodId;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	
	
}
