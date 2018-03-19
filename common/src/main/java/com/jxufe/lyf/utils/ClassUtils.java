package com.jxufe.lyf.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.util.locale.provider.LocaleServiceProviderPool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * description: Class操作工具类
 * author: LYF
 * create_date : 2018/3/17
 * create_time : 18:17
 *
 */
public class ClassUtils {
    private static final Logger log = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * @Description: 根据表名查找对应的bean 的class  注意  传入的表名必须符合驼峰规范 而且首字母必须大写  例如CfgCache
     * @author liuyf
     * @date 2018/3/18 16:32
     * @param
     */

    public static Class getClassByTableName(String packageName, String table_name) {
        Class rtnClass = null;
        try {
            if (!StringUtils.isNullOrEmpty(packageName) && !StringUtils.isNullOrEmpty(table_name)) {
                String classFullName = StringUtils.mergeStrings(packageName, table_name, "Bean");
                log.info(classFullName);
                rtnClass = Class.forName(classFullName);
            }
        } catch (ClassNotFoundException e) {
            log.error("通过报名和表名获取对应的bean 类型失败" + e);
            e.printStackTrace();
        }
        return rtnClass;

    }

    public static Object methodInvodeObject(String methodName, Object obj,Object args[]) {
        Object rtnObj = null;
        Method methods[] = null;
        try {
            methods = obj.getClass().getMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    rtnObj =method.invoke(obj,args);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return rtnObj;
    }


}
