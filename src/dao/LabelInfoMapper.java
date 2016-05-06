package org.jymf.dao;

import java.math.BigDecimal;
import java.util.Map;

import org.jymf.entity.LabelInfo;

/**
 * 企业标签信息
 * @author Administrator
 *
 */
public interface LabelInfoMapper {
	/**
	 * 根据标签号查询
	 */
	LabelInfo findBylabelId(String labelid);

	BigDecimal selPrdConsNum(Map<Object, Object> map);

	Integer getConsNum(Map<Object,Object> objectMap);

}

