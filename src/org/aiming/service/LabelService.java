package org.aiming.service;

import java.util.List;

import org.aiming.entity.Label;


public interface LabelService {
	/**
	 * 批量标签激活
	 * @return
	 */
	public boolean labelBind(List<String> list);
	/**
	 * 滤芯安装
	 * @param id
	 */
	public boolean labelDepoy(String id);
	/**
	 * 滤芯拆除
	 * @param id
	 * @return 返回修改前的label信息，判断清洗次数
	 */
	public Label labelRemove(String id);
	/**
	 * 滤芯手动报废
	 * @param id
	 * @return
	 */
	public boolean labelScrap(String id);
	/**
	 * 标签信息查询
	 * @param id
	 * @return
	 */
	public List<Label> labelQuery(String id);
	/**
	 * 根据空调id查询得到所有label信息
	 * @param id
	 * @return
	 */
	public List<Label> getLabelByAcId(String id); 
	/**
	 * 更新空气滤芯累计使用时间
	 * @param id
	 * @param cumulative_time
	 * @return
	 */
	public boolean updateTimeofLabel(String id,String cumulative_time);
}
