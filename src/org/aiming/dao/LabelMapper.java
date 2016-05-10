package org.aiming.dao;

import java.util.List;
import java.util.Map;

import org.aiming.entity.Label;


public interface LabelMapper {
	/**
	 * 插入标签数据，用于激活绑定阶段
	 * @param id 标签id
	 * @param type 标签类型
	 * @param ac_id 空调id
	 */
	void labelInsert(Map<Object, Object> map);
	/**
	 * 安装label时更新对应标签
	 * @param map
	 */
	void deployLabel(Map<Object, Object> map);
	/**
	 * 根据标签id获取label信息
	 * @param id
	 * @return
	 */
	Label getLabelById(long id);
	/**
	 * 滤芯拆除
	 * @param id
	 */
	void removeLabel(long id);
	/**
	 * 滤芯手动报废
	 * @param id
	 */
	void scrapLabel(long id);
	/**
	 * 当id 为空时返回全部label信息
	 * @param id
	 * @return
	 */
	List<Label> getLablesById(Map<Object, Object> map);
	/**
	 * 根据空调id返回所有滤芯信息
	 * @param ac_id
	 * @return
	 */
	List<Label> getLabelByAcId(int ac_id);
	/**
	 * 更新累计使用时间
	 * @param id
	 */
	void updateTimeOfLabel(Map<Object, Object> map);
}
