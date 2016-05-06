package org.jymf.service.impl;

import java.math.BigDecimal;

import org.jymf.dao.LabelIndexMapper;
import org.jymf.entity.LabelIndex;
import org.jymf.service.LabelIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("labelIndexSer")
public class LabelIndexServiceImpl implements LabelIndexService{

	@Autowired
	private LabelIndexMapper labelIndexDao;
	
	@Override
	public LabelIndex selLabelIndex(BigDecimal labelId) {
		return labelIndexDao.selLabelIndex(labelId);
	}
	
}
