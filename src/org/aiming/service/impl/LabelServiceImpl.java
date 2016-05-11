package org.aiming.service.impl;


import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.aiming.dao.LabelMapper;
import org.aiming.entity.Label;
import org.aiming.service.LabelService;
import org.aiming.utils.JsonUtil;
import org.aiming.utils.LabelComparator;
import org.aiming.utils.TimeRevert;
import org.aiming.web.LabelControl;
import org.aiming.web.TimingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LabelServiceImpl implements LabelService {
	@Autowired
	private LabelMapper labelDao;
	@Override
	public boolean labelDepoy(String id) {
		try {
			Label label = labelDao.getLabelById(id);
			if(null == label){
				return false;
			}else{
				Map<Object, Object> map = new HashMap<>();
				Properties prop=new Properties();
				prop.load(new InputStreamReader(LabelControl.class.getClassLoader().getResourceAsStream("workConig.properties"), "UTF-8"));
				List<String> limitTimes = JsonUtil.toObject(prop.getProperty("limitTime"), List.class);
				map.put("cumulative_time", TimeRevert.toString(Long.parseLong(limitTimes.get(Integer.parseInt(id.substring(2, 3))))*3600000));
				map.put("id", id);
				map.put("washing_count", label.getWashing_count()-1);
				labelDao.deployLabel(map);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public Label labelRemove(String id) {
		try {
			Label label = labelDao.getLabelById(id);
			if(null == label){
				return null;
			}else{
				labelDao.removeLabel(id);
				return label;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean labelScrap(String id) {
		try {
			Label label = labelDao.getLabelById(id);
			if(null == label){
				return false;
			}else{
				labelDao.scrapLabel(id);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public int getlabelSizeQuery(String id, String inuse, String alive, String ac_id, String aliveTime) {
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("inuse", inuse);
			map.put("alive", alive);
			map.put("ac_id", ac_id);
			List<Label> list = labelDao.getLablesSizeById(map);
			if(!"".equals(aliveTime)){
				if(null != list && 0!=list.size()){
					for(int i=0;i<list.size();i++){
						long aTime =  Long.parseLong(aliveTime)*3600000;
						long cTime = TimeRevert.toLong(list.get(i).getCumulative_time());
						if(aTime<=cTime){
							list.remove(i);
							i--;
						}
					}
				}
			}
			return list.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<Label> labelQuery(String id,String inuse, String alive, String ac_id, String aliveTime,int page) {
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("inuse", inuse);
			map.put("alive", alive);
			map.put("ac_id", ac_id);
			map.put("pageStart", page*8);
			map.put("pageEnd", page*8+8);
			List<Label> list = labelDao.getLablesById(map);
			if(!"".equals(aliveTime)){
				if(null != list && 0!=list.size()){
					for(int i=0;i<list.size();i++){
						long aTime =  Long.parseLong(aliveTime)*3600000;
						long cTime = TimeRevert.toLong(list.get(i).getCumulative_time());
						if(aTime<=cTime){
							list.remove(i);
							i--;
						}
					}
				}
			}
			//将查询结果按照累计使用时间排序
			LabelComparator comparator = new LabelComparator();
			Collections.sort(list,comparator);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<Label> getLabelByAcId(String id) {
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("id", id);
			return labelDao.getLabelByAcId(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean updateTimeofLabel(String id, String cumulative_time) {
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("cumulative_time",cumulative_time);
			labelDao.updateTimeOfLabel(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean labelBind(List<String> list) {
		try {
			Properties prop=new Properties();
			prop.load(new InputStreamReader(LabelControl.class.getClassLoader().getResourceAsStream("workConig.properties"), "UTF-8"));
			if(null != list && 0!=list.size()){
				Map<Object, Object> map = new HashMap<>();
				//获取使用时间上限及清洗次数上限
				List<String> washCountLimits = JsonUtil.toObject(prop.getProperty("washCountLimit"), List.class);
				List<String> limitTimes = JsonUtil.toObject(prop.getProperty("limitTime"), List.class);
				for(int i=0;i<list.size();i++){
					map.put("id", list.get(i));
					map.put("washing_count", Integer.parseInt(washCountLimits.get(Integer.parseInt(list.get(i).substring(2, 3))))+1);
					map.put("cumulative_time", TimeRevert.toString(Long.parseLong(limitTimes.get(Integer.parseInt(list.get(i).substring(2, 3))))*3600000));
					labelDao.labelInsert(map);
				}
				return true;
			}else return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
