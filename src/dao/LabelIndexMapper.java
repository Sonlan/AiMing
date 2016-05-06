package org.jymf.dao;

import java.math.BigDecimal;

import org.jymf.entity.LabelIndex;

/**
 * 标签索引
 * @author Administrator
 *
 */
public interface LabelIndexMapper {

	LabelIndex selLabelIndex(BigDecimal labelId);
	
}
