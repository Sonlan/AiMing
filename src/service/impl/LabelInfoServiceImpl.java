package org.jymf.service.impl;
import java.math.BigDecimal;
import java.util.Map;

import org.jymf.dao.LabelInfoMapper;
import org.jymf.entity.LabelInfo;
import org.jymf.service.LabelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("lablInfoService")
public class LabelInfoServiceImpl implements LabelInfoService{

	@Autowired
	private LabelInfoMapper lablinforDao; 
	
	@Override
	public LabelInfo getJsonLabelInfo(String labelid) {
		return lablinforDao.findBylabelId(labelid);
	}

	@Override
	public BigDecimal selPrdConsNum(Map<Object, Object> map, BigDecimal workMode) {
		return lablinforDao.selPrdConsNum(map);
	}

	@Override
	public Integer getConsNum(Map<Object, Object> map, BigDecimal workMode) {
		return lablinforDao.getConsNum(map);
	}
}
