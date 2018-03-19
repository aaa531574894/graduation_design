package com.jxufe.lyf.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc: json 工具类
 * author:LYF
 * create_date : 2018/3/14
 * create_time : 10:12
 */


public class JsonUtils {
    public static ObjectMapper mapper = new ObjectMapper();

    /***
     * 将对象序列化为JSON文本  MAP,LIST,OBJECT都适用
     *
     * @param object
     * @return
     */
    public static String object2Json(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    /**
     * 将json字符串转化成对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T json2Object(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }


    /**
     * JSON字符串转化成Map对象具体需要转
     *  具体转换类型，参考Map<String,User> result = mapper.readValue(src, new TypeReference<Map<String,User>>()
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> json2Map(String jsonStr) throws IOException {
        return mapper.readValue(jsonStr, Map.class);
    }


    /**
     * JSON字符串转化成对象List
     *
     * @param jsonStr
     * @return
     */
    public static <T> List<T> json2List(String jsonStr) throws IOException {
        return mapper.readValue(jsonStr, List.class);

    }


    public static Object getValueInMap(Map<String, Object> map, String keyStr) {
        Object obj = map.get(keyStr);
        if (obj == null) {
            obj = map.get(keyStr.toUpperCase());
        }
        return obj;
    }

    /**
     * 将对象转化成属性全部转化为大写的Map对象
     *
     * @param obj
     * @return
     */
    public static <T> Map<String, Object> objectToUpperCaseMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (obj != null) {
                Class<?> clazz = obj.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    String name = field.getName();
                    String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                    Object entity = obj.getClass().getMethod(methodName, new Class[0]).invoke(obj, new Object[0]);
                    map.put(name.toUpperCase(), entity == null ? "" : entity);
                }
            }
        } catch (Exception e) {
        }
        return map;
    }

    /**
     * 将POJO对象装换成String可以使用注解自定义成员属性名称的大小写
     * @param bean
     * @return
     * @throws IOException
     */
    public static String beanToJsonString(Object bean) throws IOException {
        if(null ==bean)
            return "";
        //设置时间的format格式,add by haomeng
        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(dateFormat);
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,Boolean.FALSE);
        //设置时间的format格式,add by haomeng
        String reqParamStr = mapper.writeValueAsString(bean);
        return reqParamStr;
    }

    public static <T> T map2Object(Map map,Class<T> clazz) throws Exception{
        String json = object2Json(map);
        return json2Object(json,clazz);
    }
}