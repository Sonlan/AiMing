package org.jymf.service;


import org.jymf.entity.UserRecord;

public interface UserRecordService {

	void updateStateByLabelId(int status,String labelId);
    int getStateByLabelId(String labelId);
	void insertUserRecord(UserRecord userRecord);

}
