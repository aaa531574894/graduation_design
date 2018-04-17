package com.jxufe.liuyf.dubbo;

import com.jxufe.liuyf.fsv.common.DubboResult;
import com.jxufe.liuyf.fsv.common.bean.CfgBusinessCode;
import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
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

    public static DubboResult callDubbo(CfgBusinessCode cfgBusinessCode, Map mParams) {
        if (cfgBusinessCode == null) {
            throw new NullPointerException("callDubbo 传入cfgBusinessCode对象为null");
        }
        DubboResult dubboResult = null;
        String fsv = null;
        String methodName = null;
        try {
            fsv = cfgBusinessCode.getInterfacee();
            methodName = cfgBusinessCode.getMothod();
            if (fsv != null && methodName != null) {
                Object aimedObj = dubboFactory.getDubbo(fsv);
                Method method = Class.forName(fsv).getMethod(methodName, Map.class);
                dubboResult = (DubboResult) method.invoke(aimedObj, mParams);
            }
        } catch (Exception e) {
            log.error("dubboUtil error" + e.getMessage());
            e.printStackTrace();
        }
        return dubboResult;
    }
}
