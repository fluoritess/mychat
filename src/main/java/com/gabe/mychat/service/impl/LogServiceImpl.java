package com.gabe.mychat.service.impl;

import com.gabe.mychat.mapper.sercurityLogMapper;
import com.gabe.mychat.pojo.sercurityLog;
import com.gabe.mychat.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;

public class LogServiceImpl implements LogService {
    @Autowired
    sercurityLogMapper logMapper;
    @Override
    public void insertLog(sercurityLog sercurityLog) {
        logMapper.insert(sercurityLog);
    }
}
