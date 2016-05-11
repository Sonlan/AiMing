package org.aiming.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aiming.dao.LabelMapper;
import org.aiming.entity.Label;
import org.aiming.service.LabelService;
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
				map.put("id", id);
				map.put("washing_count", label.getWashing_count()+1);
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
	public List<Label> labelQuery(String id) {
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("id", id);
			return labelDao.getLablesById(map);
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
			if(null != list && 0!=list.size()){
				Map<Object, Object> map = new HashMap<>();
				for(int i=0;i<list.size();i++){
					map.put("id", list.get(i));
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
