package com.jxufe.liuyf.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jxufe.liuyf.fsv.interfaces.ICacheFSV;
import com.jxufe.liuyf.fsv.interfaces.ISecurityFSV;
import org.springframework.stereotype.Component;

/**
 * desc: 基于dubbo调用的所有接口的pool
 * author:LYF
 * create_date : 2018/3/15
 * create_time : 15:50
 */
@Component
public class FsvPool {

    @Reference(version = "1.0.0")
    public ISecurityFSV iSecurityFSV;

    @Reference(version = "1.0.0")
    public ICacheFSV iCacheFSV;


}
