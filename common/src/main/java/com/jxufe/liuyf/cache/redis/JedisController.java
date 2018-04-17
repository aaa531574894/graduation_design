package com.jxufe.liuyf.cache.redis;

import com.jxufe.liuyf.cache.interfaces.IRedisCache;
import com.jxufe.lyf.utils.JsonUtils;
import com.jxufe.lyf.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.List;

/**
 * description: jedis操作封装类
 * author: LYF
 * create_date : 2018/3/16
 * create_time : 19:50
 */

@Component
public class JedisController implements IRedisCache {
    private final static Logger log = LoggerFactory.getLogger(JedisController.class);
    private JedisClusterHolder jedisClusterHolder;
    public static Boolean IS_CACHE_READY = Boolean.TRUE;    //判断redis是否已经加载缓存

    @Override
    public void refreshCacheByTableName(List lTableBean) {
        //  暂时不做  只实现全量刷新缓存  不刷新指定缓存表
    }

    @Override
    public void update(String key, Object obj) throws Exception {
        set(key, obj);
    }

    @Override
    public boolean containsKey(String key) throws Exception {
        checkCacheState();
        boolean result = false;
        JedisCluster jedis = null;
        try {
            jedis = jedisClusterHolder.getJedisCluster();
            result = jedis.exists(key);
        } finally {
            if (jedis != null) {
                jedis = null;
            }
        }
        return result;
    }


    @Override
    public String getAsString(String index) throws Exception {
        checkCacheState();
        String rtnObj = null;
        if (!StringUtils.isNullOrEmpty(index)) {
            rtnObj = jedisClusterHolder.getJedisCluster().get(index);
            return rtnObj;
        } else {
            log.error("缓存索引不能为空");
            throw new NullPointerException("缓存索引不能为空");
        }
    }


    /**
     * @param index 已经构造好的缓存主键   package.pk 的形式
     * @return
     */
    @Override
    public Object getAsObject(String index) throws Exception {
        checkCacheState();
        Object obj = null;
        Class clazz = null;
        String className = null;
        if (StringUtils.isNullOrEmpty(index)) {
            throw new NullPointerException("传入的缓存索引不能为空");
        } else {
            className = StringUtils.getClassNameByIndex(index);
        }
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        try {
            obj = JsonUtils.json2Object(jedisClusterHolder.getJedisCluster().get(index), clazz);
        } catch (IOException e) {
            log.error("json2Object失败" + e);
            e.printStackTrace();
        }
        return obj;

    }


    @Override
    public boolean isCacheLoaded() {
        return IS_CACHE_READY.booleanValue() == true;
    }


    @Override
    public void delete(String key) {
        jedisClusterHolder.getJedisCluster().del(key);
    }


    @Override
    public void set(String key, Object object) throws Exception {
        checkCacheState();
        String sValue = null;
        try {

            sValue = JsonUtils.object2Json(object);
        } catch (IOException e) {
            log.error("object转json失败");
            e.printStackTrace();
        }
        jedisClusterHolder.getJedisCluster().set(key, sValue);
    }

    @Override
    public void fSet(String key, Object object) {
        String sValue = null;
        try {

            sValue = JsonUtils.object2Json(object);
        } catch (IOException e) {
            log.error("object转json失败");
            e.printStackTrace();
        }
        jedisClusterHolder.getJedisCluster().set(key, sValue);
    }

    @Override
    public void publish(String channel, String message) {
        jedisClusterHolder.getJedisCluster().publish(channel, message);
    }

    private void checkCacheState() throws RuntimeException {
        if (IS_CACHE_READY==Boolean.TRUE) {
            log.info("缓存已经准备就绪");
        } else {
            throw new RuntimeException("正在刷新缓存或者缓存未初始化，请稍后尝试");
        }
    }


    public void setFlag(String key, String value) {
        jedisClusterHolder.getJedisCluster().set(key, value);
    }





    @Autowired
    public void setJedisClusterHolder(JedisClusterHolder jedisClusterHolder) {
        this.jedisClusterHolder = jedisClusterHolder;
    }


}
