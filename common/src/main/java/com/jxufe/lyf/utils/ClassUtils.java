package com.jxufe.lyf.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * description: Class操作工具类
 * author: LYF
 * create_date : 2018/3/17
 * create_time : 18:17
 */
public class ClassUtils {
    private static final Logger log = LoggerFactory.getLogger(ClassUtils.class);




    /**
     * @param
     * @Description: 根据表名查找对应的bean 的class  注意  传入的表名必须符合驼峰规范 而且首字母必须大写  例如CfgCache
     * @author liuyf
     * @date 2018/3/18 16:32
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



    /**
     * @Description:  反射访问指定对象的指定方法
     * @param methodName
     * @param obj
     * @param args   方法的参数数组
     * @return
     */
    public static Object methodInvodeObject(String methodName, Object obj, Object args[]) {
        Object rtnObj = null;
        Method methods[] = null;
        try {
            methods = obj.getClass().getMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    rtnObj = method.invoke(obj, args);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return rtnObj;
    }





    /**
     *
     * @param pkgName   包路径 eg: com.jxufe.liuyf
     * @param isRecursive  是否迭代子包
     * @param annotation   只有被注解的类才会被list出来
     * @return
     */
    public static List<Class<?>> getClassList(String pkgName, boolean isRecursive, Class<? extends Annotation> annotation) {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            // 按文件的形式去查找
            String strFile = pkgName.replaceAll("\\.", "/");
            Enumeration<URL> urls = loader.getResources(strFile);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    String pkgPath = url.getPath();
                    System.out.println("protocol:" + protocol + " path:" + pkgPath);
                    if ("file".equals(protocol)) {
                        // 本地自己可见的代码
                        findClassName(classList, pkgName, pkgPath, isRecursive, annotation);
                    } else if ("jar".equals(protocol)) {
                        //jar包暂时不做支持
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classList;
    }
    private static void findClassName(List<Class<?>> clazzList, String pkgName, String pkgPath, boolean isRecursive, Class<? extends Annotation> annotation) {
        if (clazzList == null) {
            return;
        }
        File[] files = filterClassFiles(pkgPath);// 过滤出.class文件及文件夹
        System.out.println("files:" + ((files == null) ? "null" : "length=" + files.length));
        if (files != null) {
            for (File f : files) {
                String fileName = f.getName();
                if (f.isFile()) {
                    // .class 文件的情况
                    String clazzName = getClassName(pkgName, fileName);
                    addClassName(clazzList, clazzName, annotation);
                } else {
                    // 文件夹的情况
                    if (isRecursive) {
                        // 需要继续查找该文件夹/包名下的类
                        String subPkgName = pkgName + "." + fileName;
                        String subPkgPath = pkgPath + "/" + fileName;
                        findClassName(clazzList, subPkgName, subPkgPath, true, annotation);
                    }
                }
            }
        }
    }
    private static File[] filterClassFiles(String pkgPath) {
        if (pkgPath == null) {
            return null;
        }
        // 接收 .class 文件 或 类文件夹
        return new File(pkgPath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
    }
    private static void addClassName(List<Class<?>> clazzList, String clazzName, Class<? extends Annotation> annotation) {
        if (clazzList != null && clazzName != null) {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(clazzName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (clazz != null) {
                if (annotation == null) {
                    clazzList.add(clazz);
                } else if (clazz.isAnnotationPresent(annotation)) {
                    clazzList.add(clazz);
                }
            }
        }
    }
    private static String getClassName(String pkgName, String fileName) {
        int endIndex = fileName.lastIndexOf(".");
        String clazz = null;
        if (endIndex >= 0) {
            clazz = fileName.substring(0, endIndex);
        }
        String clazzName = null;
        if (clazz != null) {
            clazzName = pkgName + "." + clazz;
        }
        return clazzName;
    }


}
