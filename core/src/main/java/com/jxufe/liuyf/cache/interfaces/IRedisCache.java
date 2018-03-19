package com.jxufe.liuyf.cache.interfaces;

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
    void refreshAllCache();

    /**
     * 　* @Description: 更新缓存
     * 　* @author liuyf
     * 　* @date 2018/3/17 14:21
     */

    void update(String key, Object obj);

    /**
     * 　* @Description: redis中是否包含此key
     * 　* @author liuyf
     * 　* @date 2018/3/17 14:20
     */
    boolean containsKey(String key);

    /**
     * 　* @Description: 通过key拿到对象
     * 　* @author liuyf
     * 　* @date 2018/3/17 14:20
     */
    Object getAsString(String key);



    Object getAsObject(String index);


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

    void set(String key, Object object);
}
