package org.jymf.service.impl;

import org.jymf.dao.PlayMapper;
import org.jymf.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service("playSer")
public class PlayServiceImpl implements PlayService{

    @Autowired
    PlayMapper mapper;



    @Override
    public boolean isIndate(int palyId) {
        return mapper.isIndate(new BigDecimal(palyId))==1;
    }
}
