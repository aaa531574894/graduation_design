package com.jxufe.liuyf.cache.redis;

import com.jxufe.liuyf.cache.interfaces.IRedisCache;
import com.jxufe.lyf.utils.ClassUtils;
import com.jxufe.lyf.utils.JsonUtils;
import com.jxufe.lyf.utils.SerializeUtils;
import com.jxufe.lyf.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.Map;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/16
 * create_time : 19:50
 */

@Component
public class JedisController implements IRedisCache {
    private final static String IS_CACHE_LOADED = "IS_REDIS_CACHE_REFRESHED";
    private final static Logger log = LoggerFactory.getLogger(JedisController.class);

    private JedisClusterHolder jedisClusterHolder;

    @Override
    public void refreshAllCache() {

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
    public Object getAsString(String index) {
        Object rtnObj = null;
        rtnObj = jedisClusterHolder.getJedisCluster().get(index);
        return rtnObj;
    }


    /**
     *
     * @param index  已经构造好的缓存主键   package.pk 的形式
     * @return
     */
    @Override
    public Object getAsObject(String index) {
        Object obj = null;
        try {
            obj = JsonUtils.json2Object(jedisClusterHolder.getJedisCluster().get(index), Class.forName(StringUtils.getClassNameByIndex(index)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }


    /**
     *   redis中的关键字   IS_CACHE_LOADED 作为缓存是否加载好的flag
     * @return
     */

    @Override
    public boolean isCacheLoaded() {
        String result = jedisClusterHolder.getJedisCluster().get(IS_CACHE_LOADED);
        if (result.equals("TRUE")) {
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
