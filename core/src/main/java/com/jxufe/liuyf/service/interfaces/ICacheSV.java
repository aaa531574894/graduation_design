package com.jxufe.liuyf.service.interfaces;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/21
 * create_time : 21:10
 */
public interface ICacheSV {

    void refreshCacheByTableName(String tableName);

    void refreshAllCache();
}
