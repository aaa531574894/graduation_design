package com.jxufe.lyf.utils;

/**
 * description: String 工具类
 * author: LYF
 * create_date : 2018/3/16
 * create_time : 19:23
 */
public class StringUtils {

    /**
     * 　* @Description: 判断string 是否为空或为null
     * 　* @author liuyf
     * 　* @date 2018/3/16 19:24
     */
    public static boolean isNullOrEmpty(String str) {
        if (null == str || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 　* @Description: 忽略大小写比较两个字符串内容是否完全一致
     * 　* @author liuyf
     * 　* @date 2018/3/16 19:33
     */
    public static boolean isEqualIgnoreCase(String s1, String s2) {
        if (StringUtils.isNullOrEmpty(s1) || StringUtils.isNullOrEmpty(s2)) {
            return false;
        } else {
            if (s1.equalsIgnoreCase(s2)) {
                return true;
            } else {
                return false;
            }
        }
    }


    /**
     * 　* @Description:  按传入的顺序拼接字符串
     * 　* @author liuyf
     * 　* @date 2018/3/17 17:37
     */
    public static String mergeStrings(String... strings) {
        StringBuilder sb = new StringBuilder();
        if (!(strings.length == 0) || strings == null) {
            for (String c : strings) {
                sb.append(c);
            }
            return sb.toString();
        } else {
            return null;
        }
    }


    public static String getClassNameByIndex(String index) {
        StringBuilder sb = new StringBuilder();
        if (isNullOrEmpty(index)) {
            throw new NullPointerException("传入的索引不能为null");
        } else {
            String every[] = index.split("\\.");
            for (int i = 0; i < every.length; i++) {
                if (i != every.length-1) {
                    sb.append(every[i]);
                    if (i != every.length - 2) {
                        sb.append(".");
                    }
                } else {
                    break;
                }
            }
            return sb.toString().trim();
        }

    }

    /**
     * @Description: 通过缓存索引获取表名
     * @return
     *
     */
    public static String getTableNameByIndex(String index) {
        return null;
    }

    /**
     * @Description: 通过class 获取表名   只针对mybatismapper 接口类
     * @return
     */
    public static String getTableNameByMapper(Class<?> clazz) {
        String tableName = null;
        if (clazz == null) {
            throw new NullPointerException("传入的class不能为为空");
        }
        String ss[] = clazz.getName().split("\\.");
        if (ss.length == 0 || ss == null) {
            throw new NullPointerException("根据classname拆分string数组出错");
        }
        tableName = ss[ss.length - 1].replace("Mapper", "");
        return tableName;
    }

}
