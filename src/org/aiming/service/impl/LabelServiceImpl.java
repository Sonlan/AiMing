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
	public int labelDepoy(String id) {
		try {
			Label label = labelDao.getLabelById(id);
			if(null == label){
				return 1;
			}else if(label.getAlive()==0){
				return 2;
			}else if(label.getInuse()==1){
				return 3;
			}
			else{
				Map<Object, Object> map = new HashMap<>();
				Properties prop=new Properties();
				prop.load(new InputStreamReader(LabelControl.class.getClassLoader().getResourceAsStream("workConfig.properties"), "UTF-8"));
				List<String> limitTimes = JsonUtil.toObject(prop.getProperty("limitTime"), List.class);
				map.put("aliveTime", TimeRevert.toString(Long.parseLong(limitTimes.get(Integer.parseInt(id.substring(2, 3))))*3600000));
				map.put("id", id);
				map.put("washRemain", label.getWashRemain()-1);
				labelDao.deployLabel(map);
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 4;
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
				//判断是否到达报废条件
				if(label.getWashRemain()<=0)
					labelDao.scrapLabel(id);
				return label;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int labelScrap(String id) {
		try {
			Label label = labelDao.getLabelById(id);
			if(null == label){
				return 1;
			}else{
				labelDao.scrapLabel(id);
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}
	@Override
	public int getlabelSizeQuery(String id, String inuse, String alive, String ac_id,String level, String aliveTime,String washRemain) {
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("inuse", inuse);
			map.put("alive", alive);
			map.put("ac_id", ac_id);
			map.put("level", level);
			map.put("washRemain", washRemain);
			List<Label> list = labelDao.getLablesSizeById(map);
			if(!"".equals(aliveTime)){
				if(null != list && 0!=list.size()){
					for(int i=0;i<list.size();i++){
						long aTime =  Long.parseLong(aliveTime)*3600000;
						long cTime = TimeRevert.toLong(list.get(i).getAliveTime());
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
	public List<Label> labelQuery(String id,String inuse, String alive, String ac_id,String level,String aliveTime,String washRemain,int page) {
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("inuse", inuse);
			map.put("alive", alive);
			map.put("ac_id", ac_id);
			map.put("level", level);
			map.put("washRemain", washRemain);
			List<Label> list = labelDao.getLablesById(map);
			if(!"".equals(aliveTime)){
				if(null != list && 0!=list.size()){
					for(int i=0;i<list.size();i++){
						long aTime =  Long.parseLong(aliveTime)*3600000;
						long cTime = TimeRevert.toLong(list.get(i).getAliveTime());
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
			return list.subList(page*9, (page+1)*9<list.size()?(page+1)*9:list.size());
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
	public boolean updateTimeofLabel(String id, String aliveTime) {
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("aliveTime",aliveTime);
			labelDao.updateTimeOfLabel(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public int labelBind(String id) {
		try {
			Properties prop=new Properties();
			prop.load(new InputStreamReader(LabelControl.class.getClassLoader().getResourceAsStream("workConfig.properties"), "UTF-8"));
			if(null != id && !"".equals(id)){
				Map<Object, Object> map = new HashMap<>();
				//获取使用时间上限及清洗次数上限
				List<String> washCountLimits = JsonUtil.toObject(prop.getProperty("washCountLimit"), List.class);
				List<String> limitTimes = JsonUtil.toObject(prop.getProperty("limitTime"), List.class);
				map.put("id", id);
				map.put("washRemain", Integer.parseInt(washCountLimits.get(Integer.parseInt(id.substring(2, 3))))+1);
				map.put("aliveTime", TimeRevert.toString(Long.parseLong(limitTimes.get(Integer.parseInt(id.substring(2, 3))))*3600000));
				labelDao.labelInsert(map);
				return 0;
			}else return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}
	@Override
	public boolean deleteLabelInfo(String id) {
		try {
			labelDao.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean editLabel(Label label) {
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("label", label);
			labelDao.editLabel(map);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
