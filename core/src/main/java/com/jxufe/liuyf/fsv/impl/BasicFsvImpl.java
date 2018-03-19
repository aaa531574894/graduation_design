package com.jxufe.liuyf.fsv.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jxufe.liuyf.fsv.interfaces.IBasicCfgFsv;
import com.jxufe.liuyf.service.interfaces.IBasicSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description: 基础fsv
 * author: LYF
 * create_date : 2018/3/16
 * create_time : 19:53
 */

@Service(version = "1.0.0.")
public class BasicFsvImpl implements IBasicCfgFsv {
    private IBasicSV iBasicSV;

    @Override
    public void initCache() {
        iBasicSV.initCache();
    }

    @Autowired
    public void setiBasicSV(IBasicSV iBasicSV) {
        this.iBasicSV = iBasicSV;
    }
}
