package org.jymf.dao;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 活动详情信息
 * @author Administrator
 *
 */ 
public interface PlayMapper {

    int isIndate(@Param("id") BigDecimal playId);

}
