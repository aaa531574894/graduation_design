package com.jxufe.liuyf.cache.interfaces;

import java.util.List;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/16
 * create_time : 20:51
 */
public interface IRedisCache {

    /**
     * 　* @Description: 全量刷新缓存
     * 　* @author liuyf
     * 　* @date 2018/3/17 14:21
     */
    void refreshCacheByTableName(List lTableBean);

    /**
     * 　* @Description: 更新缓存
     * 　* @author liuyf
     * 　* @date 2018/3/17 14:21
     */

    void update(String key, Object obj) throws Exception;

    /**
     * 　* @Description: redis中是否包含此key
     * 　* @author liuyf
     * 　* @date 2018/3/17 14:20
     */
    boolean containsKey(String key) throws Exception;

    /**
     * 　* @Description: 通过key拿到对象
     * 　* @author liuyf
     * 　* @date 2018/3/17 14:20
     */
    Object getAsString(String key) throws Exception;



    Object getAsObject(String index) throws Exception;


    /**
     * 　* @Description: redis是否已经加载缓存
     * 　* @author liuyf
     * 　* @date 2018/3/17 14:20
     */
    boolean isCacheLoaded();

    /**
     * 　* @Description: 删除指定缓存条目
     * 　* @author liuyf
     * 　* @date 2018/3/17 14:23
     */
    void delete(String key);

    /**
     * 　* @Description: 添加缓存
     * 　* @author liuyf
     * 　* @date 2018/3/17 14:23
     *
     */

    void set(String key, Object object) throws Exception;


    /**
     * @param
     * @Description: 刷入缓存时才可以用，其他时候不可用
     * @author liuyf
     * @date 2018/4/17 20:11
     */

    void fSet(String key, Object object);

    /**
     * @param 广播消息
     * @Description:
     * @author liuyf
     * @date 2018/4/17 20:49
     */
    void publish(String channel, String message);
}
