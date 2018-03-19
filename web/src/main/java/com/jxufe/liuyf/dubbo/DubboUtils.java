package com.jxufe.liuyf.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * desc: please add the description
 * author:LYF
 * create_date : 2018/3/15
 * create_time : 16:14
 */
@Component
@Scope("singleton")
public class DubboUtils {
    private final static Logger log = LoggerFactory.getLogger(DubboUtils.class);
    private static DubboFactory dubboFactory;


    @Autowired(required = true)    //注意是如何给静态变量依赖注入的   单独为其设置一个set方法 (set方法一定不能是 static的)
    public void setDubboFactory(DubboFactory dubboFactory) {
        DubboUtils.dubboFactory = dubboFactory;
    }

    public static Object callDubbo(String busi_code, Map mParams) {
        Object rtnObj = null;
        try {
            Object object = dubboFactory.getDubbo("com.jxufe.liuyf.fsv.interfaces.ISecurityFSV");
            Method method = Class.forName("com.jxufe.liuyf.fsv.interfaces.ISecurityFSV").getMethod("isRegisted", Map.class);
            method.invoke(object, mParams);
            log.info(object.getClass().toString());
        } catch (Exception e) {
            log.error("dubboUtil error" + e);
        }
        return rtnObj;
    }


}
