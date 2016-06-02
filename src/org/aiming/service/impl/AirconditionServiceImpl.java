package org.aiming.service.impl;

import java.io.InputStreamReader;
import java.math.BigDecimal;
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
	public boolean isWork(String ac_id,BigDecimal workmode) {
		try {
			Properties prop=new Properties();
			prop.load(new InputStreamReader(TimingControl.class.getClassLoader().getResourceAsStream("workConfig.properties"), "UTF-8"));
			String tableName = prop.getProperty("ac_table_name");
			String valueName = prop.getProperty("ac_value_name");
			Float freq = 0.0f;
			Map<Object, Object> map = new HashMap<>();
			map.put("tableName", tableName);
			map.put("valueName", valueName);
			map.put("ac_id",ac_id);
			freq = airDao.getWorkFreq(map);
			if(freq != 0){
				return true;
			}else return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
