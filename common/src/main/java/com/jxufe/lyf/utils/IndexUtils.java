package com.jxufe.lyf.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/16
 * create_time : 19:11
 */
@Component
public class IndexUtils {
    private static final Logger log = LoggerFactory.getLogger(IndexUtils.class);

    /**
     * 　* @Description: 获取对象在所在表中的主键
     * 　* @author liuyf
     * 　* @date 2018/3/17 18:47
     */
    public static String getPkByObject(Object object) {
        String pk = null;
        try {
            Method method = object.getClass().getMethod("getPk");
            pk = (String) method.invoke(object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return pk;
    }
    /**
     　* @Description: 构造缓存主键  缓存的主键类型为 全限定包名+pk  例如 :com.jxufe.liuyf.fsv.common.bean.CfgBusinessCodeBean.TEST
     　* @author liuyf
     　* @date 2018/3/18 16:03
     　*/
    public static String getIndexByObject(Object object) {
        String pk = null;
        String index = null;
        try {
            pk = getPkByObject(object);
            index = object.getClass().getName() +"."+ pk;
            log.info("构造的缓存索引是:  " + index);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return index;
    }


}
