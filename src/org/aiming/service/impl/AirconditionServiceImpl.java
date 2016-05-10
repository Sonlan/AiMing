package org.aiming.service.impl;

import java.math.BigDecimal;

import org.aiming.dao.AirconditionMapper;
import org.aiming.service.AirconditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AirconditionServiceImpl implements AirconditionService {
	@Autowired
	private AirconditionMapper airDao;
	@Override
	public boolean isWork(int index,BigDecimal workmode) {
		try {
			Float freg = 0.0f;
			switch (index) {
			case 0:
				freg = airDao.getWorkFreq1();
				break;
			case 1:
				freg = airDao.getWorkFreq2();
				break;
			case 2:
				freg = airDao.getWorkFreq3();
				break;
			default:
				break;
			}
			if(freg != 0){
				return true;
			}else return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
