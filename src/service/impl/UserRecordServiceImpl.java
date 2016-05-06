package org.jymf.service.impl;

import org.jymf.dao.UserRecordMapper;
import org.jymf.entity.UserRecord;
import org.jymf.service.UserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("userRecordSer")
public class UserRecordServiceImpl implements UserRecordService{
	@Autowired
	private UserRecordMapper userrecordDao;

	@Override
	public void updateStateByLabelId(int status, String labelId) {
        HashMap<Object,Object> map = new HashMap<Object,Object>();
        map.put("status",status) ;
        map.put("labelid",labelId);
        userrecordDao.updateStatusByLabelId(map);
	}

	@Override
	public void insertUserRecord(UserRecord userRecord) {
		userrecordDao.insertUserRecord(userRecord);
	}

	@Override
	public int getStateByLabelId(String labelId) {
		return userrecordDao.getStatusByLabelId(labelId);
	}
}
