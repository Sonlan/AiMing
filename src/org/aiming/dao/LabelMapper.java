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
	Label getLabelById(String id);
	/**
	 * 滤芯拆除
	 * @param id
	 */
	void removeLabel(String id);
	/**
	 * 滤芯手动报废
	 * @param id
	 */
	void scrapLabel(String id);
	/**
	 * 当id 为空时返回全部label信息
	 * @param id
	 * @return
	 */
	List<Label> getLablesById(Map<Object, Object> map);
	/**
	 * 根据空调id(滤芯id前两位)返回所有滤芯信息
	 * @param map 空调id
	 * @return
	 */
	List<Label> getLabelByAcId(Map<Object, Object> map);
	/**
	 * 更新累计使用时间
	 * @param id
	 */
	void updateTimeOfLabel(Map<Object, Object> map);
}
