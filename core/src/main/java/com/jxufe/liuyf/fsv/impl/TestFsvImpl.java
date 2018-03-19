package com.jxufe.liuyf.fsv.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jxufe.liuyf.cache.redis.JedisController;
import com.jxufe.liuyf.fsv.interfaces.ITestFsv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/17
 * create_time : 21:08
 */
@Service(version = "1.0.0")
public class TestFsvImpl implements ITestFsv {
    private final static Logger log = LoggerFactory.getLogger(TestFsvImpl.class);
    @Autowired
    private  JedisController jedisController;
    @Override
    public void test() {
        log.error((String) jedisController.getAsString("TEST"));
    }

}
