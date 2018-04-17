package com.jxufe.liuyf.fsv.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jxufe.liuyf.fsv.common.DubboResult;
import com.jxufe.liuyf.fsv.common.bean.InsUser;
import com.jxufe.liuyf.fsv.interfaces.ISecurityFSV;
import com.jxufe.liuyf.service.interfaces.ISecuritySV;
import com.jxufe.lyf.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;

/**
 * desc: please add the description
 * author:LYF
 * create_date : 2018/3/14
 * create_time : 20:22
 */
@Service(version = "1.0.0")
public class SecurityFSVImpl implements ISecurityFSV {
    private final static Logger log = LoggerFactory.getLogger(SecurityFSVImpl.class);

    @Autowired
    private ISecuritySV iSecuritySV;

    @Override
    public DubboResult registerNewUser(Map map) {
        DubboResult dubboResult = new DubboResult();
        if (map == null) {
            dubboResult.setFailReason("入参不能为null");
            dubboResult.setProcessState(false);
            return dubboResult;
        }
        InsUser newUser;
        try {
            if (iSecuritySV.isRegisted(JsonUtils.map2Object(map, InsUser.class))) {
                dubboResult.setProcessState(true);
                dubboResult.setFailReason("该用户已经注册过！！");
                return dubboResult;
            } else {
                newUser = JsonUtils.map2Object(map, InsUser.class);
                register(newUser);
                log.info("新注册用户" + map.toString());
                dubboResult.setProcessState(true);
                return dubboResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            dubboResult.setProcessState(false);
            dubboResult.setFailReason(e.getMessage());
            return dubboResult;
        }
    }

    @Override
    public DubboResult login(Map map) {
        DubboResult dubboResult = new DubboResult();
        if (map == null) {
            dubboResult.setProcessState(false);
            dubboResult.setFailReason("入参不能为null");
            return dubboResult;
        }
        try {
            InsUser thisUser = JsonUtils.map2Object(map, InsUser.class);
            Boolean isChecked=new Boolean(iSecuritySV.checkLoginPermission(thisUser));
            dubboResult.setProcessState(true);
            dubboResult.setRtnObj(isChecked);
            return dubboResult;
        } catch (Exception e) {
            log.error("map2Object出错" + e.getMessage());
            e.printStackTrace();
            dubboResult.setFailReason(e.getMessage());
            dubboResult.setProcessState(false);
            return dubboResult;
        }

    }




    private boolean register(InsUser insUser) {
        try {
            iSecuritySV.register(insUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
