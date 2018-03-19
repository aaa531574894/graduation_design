package com.jxufe.lyf.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/17
 * create_time : 17:51
 */
public class SerializeUtils {
    private static final Logger log = LoggerFactory.getLogger(SerializeUtils.class);
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            log.error("序列化对象失败" + e);
        }
        return null;
    }

    public static Object unserialize( byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            log.error("反序列化失败" + e);
        }
        return null;
    }
}