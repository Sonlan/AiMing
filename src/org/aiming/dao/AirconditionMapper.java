package org.aiming.dao;

public interface AirconditionMapper {
	/**
	 * 从第三方数据库查询得到空调变频。非0代表空调在工作
	 * @return
	 */
	Float getWorkFreq1();
	Float getWorkFreq2();
	Float getWorkFreq3();
}
