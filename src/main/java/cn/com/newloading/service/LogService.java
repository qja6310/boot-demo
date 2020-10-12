package cn.com.newloading.service;

import cn.com.newloading.bean.WebLog;
import cn.com.newloading.dao.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogService {

    @Autowired
    private LogMapper logMapper;

    public Integer addLog(WebLog log){
        return logMapper.addLog(log);
    }

}
