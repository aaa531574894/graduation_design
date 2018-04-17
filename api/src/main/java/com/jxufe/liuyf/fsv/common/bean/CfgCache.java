package com.jxufe.liuyf.fsv.common.bean;

import com.jxufe.liuyf.fsv.common.CacheIndex;
import org.codehaus.jackson.annotate.JsonIgnore;

public class CfgCache extends  AbstractCacheTable implements CacheIndex  {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CFG_CACHE.TABLE_NAME
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    private String tableName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CFG_CACHE.IS_NEED_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    private String isNeedCache;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CFG_CACHE.STATE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    private String state;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CFG_CACHE.TABLE_NAME
     *
     * @return the value of CFG_CACHE.TABLE_NAME
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CFG_CACHE.TABLE_NAME
     *
     * @param tableName the value for CFG_CACHE.TABLE_NAME
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CFG_CACHE.IS_NEED_CACHE
     *
     * @return the value of CFG_CACHE.IS_NEED_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public String getIsNeedCache() {
        return isNeedCache;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CFG_CACHE.IS_NEED_CACHE
     *
     * @param isNeedCache the value for CFG_CACHE.IS_NEED_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public void setIsNeedCache(String isNeedCache) {
        this.isNeedCache = isNeedCache == null ? null : isNeedCache.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CFG_CACHE.STATE
     *
     * @return the value of CFG_CACHE.STATE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CFG_CACHE.STATE
     *
     * @param state the value for CFG_CACHE.STATE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    @JsonIgnore
    @Override
    public String getIndex() {
        return getFullClassName(this)+getTableName();
    }
}