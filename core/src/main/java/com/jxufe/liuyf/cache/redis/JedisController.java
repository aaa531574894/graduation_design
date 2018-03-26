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
    public final static String IS_CACHE_LOADED = "IS_CACHE_LOADED";   //判断redis是否已经加载缓存
    private final static Logger log = LoggerFactory.getLogger(JedisController.class);
    private JedisClusterHolder jedisClusterHolder;


    @Override
    public void refreshCacheByTableName(List lTableBean) {
        //先取一个bean出来  清空这个前缀的所有缓存  然后开始刷入新的缓存
    }

    @Override
    public void update(String key, Object obj) {
        set(key, obj);
    }


    @Override
    public boolean containsKey(String key) {
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
    public String getAsString(String index) {
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
    public Object getAsObject(String index) {
        Object obj = null;
        Class clazz = null;
        String className = null;
        if (StringUtils.isNullOrEmpty(index)){
            throw new NullPointerException("传入的缓存索引不能为空");
        }else {
            className = StringUtils.getClassNameByIndex(index);
        }
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        try{
            obj = JsonUtils.json2Object(jedisClusterHolder.getJedisCluster().get(index),clazz);
        } catch (IOException e) {
            log.error("json2Object失败"+e);
            e.printStackTrace();
        }
        return obj;

    }


    /**
     * redis中的关键字   IS_CACHE_LOADED 作为缓存是否加载好的flag
     *
     * @return
     */

    @Override
    public boolean isCacheLoaded() {
        String result = jedisClusterHolder.getJedisCluster().get(IS_CACHE_LOADED);
        if (result.equalsIgnoreCase("TRUE")) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void delete(String key) {
        jedisClusterHolder.getJedisCluster().del(key);
    }


    @Override
    public void set(String key, Object object) {
        String sValue = null;
        try {

            sValue = JsonUtils.object2Json(object);
        } catch (IOException e) {
            log.error("object转json失败");
            e.printStackTrace();
        }
        jedisClusterHolder.getJedisCluster().set(key, sValue);
    }


    @Autowired
    public void setJedisClusterHolder(JedisClusterHolder jedisClusterHolder) {
        this.jedisClusterHolder = jedisClusterHolder;
    }
}
