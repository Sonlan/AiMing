package org.aiming.service.impl;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.PUT;

import org.aiming.dao.AirconditionMapper;
import org.aiming.service.AirconditionService;
import org.aiming.utils.JsonUtil;
import org.aiming.web.TimingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AirconditionServiceImpl implements AirconditionService {
	@Autowired
	private AirconditionMapper airDao;
	@Override
	public Map<String, Float> getWorkValue(BigDecimal workMode) {
		try {
			Properties prop=new Properties();
			prop.load(new InputStreamReader(TimingControl.class.getClassLoader().getResourceAsStream("workConfig.properties"), "UTF-8"));
			List<String> ac_ids = JsonUtil.toObject(prop.getProperty("ac_id"), List.class);
			String tableName = prop.getProperty("ac_table_name");
			List<String> valueNames = new ArrayList<>();
			for(int i=0;i<ac_ids.size();i++){
				valueNames.add("value"+(i+1));
			}
			String teString = valueNames.toString().replace('[', ' ').replace(']', ' ');
			Map<Object, Object> map = new HashMap<>();
			map.put("tableName", tableName);
			map.put("valueNames",teString);
			List<Map<String, Float>> result = airDao.getWorkStatus(map);
			return result.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
