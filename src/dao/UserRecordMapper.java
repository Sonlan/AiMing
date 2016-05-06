package org.jymf.dao;

import net.sf.ehcache.config.generator.model.XMLGeneratorVisitor;
import org.apache.ibatis.annotations.Param;
import org.jymf.entity.UserRecord;

import java.util.Map;
import java.util.Objects;

/**
 * 用户查询记录
 * @author Administrator
 *
 */
public interface UserRecordMapper {
     
	/**
	 * 根据追溯标签更新status
	 */
	void updateStatusByLabelId(Map<Object,Object> map);
    int getStatusByLabelId(@Param("labelid") String labelId);
	void insertUserRecord(UserRecord userRecord);
}
