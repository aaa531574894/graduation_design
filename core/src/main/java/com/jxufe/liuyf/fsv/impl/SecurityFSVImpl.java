package com.jxufe.liuyf.fsv.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jxufe.liuyf.fsv.interfaces.ISecurityFSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * desc: please add the description
 * author:LYF
 * create_date : 2018/3/14
 * create_time : 20:22
 */
@Service(version = "1.0.0")
public class SecurityFSVImpl implements ISecurityFSV {
    private final static Logger log = LoggerFactory.getLogger(SecurityFSVImpl.class);


    @Override
    public boolean isRegisted(Map map) {
        log.info("服务方被调用一次");
        return true;
    }

    @Override
    public void registerNewUser() {

    }
}
