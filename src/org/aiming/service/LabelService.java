package org.aiming.service;

import java.util.List;

import javax.ws.rs.GET;

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
	 * @param id 空气滤芯id
	 * @param aliveTime 剩余待清洗时间
	 * @param ac_id 空调id
	 * @param alive 是否报废
	 * @param inuse 是否在使用
	 * @param page 
	 * @return
	 */
	public List<Label> labelQuery(String id, String inuse, String alive, String ac_id, String aliveTime, int page);
	/**
	 * 返回符合查询条件的所有项的条数
	 * @param id
	 * @param inuse
	 * @param alive
	 * @param ac_id
	 * @param aliveTime
	 * @return
	 */
	public int getlabelSizeQuery(String id, String inuse, String alive, String ac_id, String aliveTime);
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
