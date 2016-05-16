package org.aiming.utils;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.aiming.web.TimingControl;

/**
 * 空气滤芯标签校验工具
 * @author Administrator
 *
 */
public class LabelValidate {
	public static boolean validate(String id){
		try {
			List<String> ac_id = new ArrayList<>();
			List<String> washCountLimit = new ArrayList<>();
			//必须是13位
			if(13!=id.length()){
				return false;
			}
			Properties prop=new Properties();
			prop.load(new InputStreamReader(TimingControl.class.getClassLoader().getResourceAsStream("workConig.properties"), "UTF-8"));
			ac_id = JsonUtil.toObject(prop.getProperty("ac_id"), List.class);
			washCountLimit = JsonUtil.toObject(prop.getProperty("washCountLimit"), List.class);
			//前两位必须与所配置的空调id一致
			if(!ac_id.contains(id.substring(0,2)))
				return false;
			//第三位一定所表示的空气滤芯类型数量一定与配置文件一致
			if(Integer.parseInt(id.substring(2, 3))>=washCountLimit.size())
				return false;
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
