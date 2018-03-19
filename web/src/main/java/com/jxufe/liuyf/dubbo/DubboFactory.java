package com.jxufe.liuyf.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * desc: please add the description
 * author:LYF
 * create_date : 2018/3/15
 * create_time : 16:15
 */
@Component
@Scope("singleton")
public class DubboFactory {
    private final static Logger log = LoggerFactory.getLogger(DubboFactory.class);
    private static Boolean isInitial=Boolean.FALSE;
    private Map<Class, Object> class2Instance = new HashMap<Class, Object>();
    private FsvPool fsvPool;

    @Autowired
    public DubboFactory(FsvPool fsvPool) {
        this.fsvPool = fsvPool;
    }


    private void initial(Map map) throws IllegalAccessException {
        Field fields[] = FsvPool.class.getFields();
        for (Field field : fields) {
            Class cls = field.getType();
            Object obj = field.get(fsvPool);
            map.put(cls, obj);
            log.info(cls.toString() + "  " + obj.toString());
        }
        isInitial = Boolean.TRUE;
    }

    public Object getDubbo(String clsName) {
        if (isInitial == Boolean.FALSE) {
            synchronized (isInitial) {
                if (isInitial == Boolean.FALSE) {
                    try {
                        initial(this.class2Instance);
                    } catch (IllegalAccessException e) {
                        log.error("DubboFactory初始化失败" + e);
                    }

                }
            }
        }
        Object obj = null;
        try {
            obj=class2Instance.get(Class.forName(clsName));
        } catch (ClassNotFoundException e) {
            log.error("加载类失败" + e);
        }
        return obj;
    }
}
