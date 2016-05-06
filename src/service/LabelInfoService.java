package org.jymf.service;

import java.math.BigDecimal;
import java.util.Map;

import org.jymf.entity.LabelInfo;

public interface LabelInfoService {
	
    LabelInfo getJsonLabelInfo(String labelid);
    
    BigDecimal selPrdConsNum(Map<Object, Object> map, BigDecimal workMode);

    Integer getConsNum(Map<Object, Object> map, BigDecimal workMode);
}
