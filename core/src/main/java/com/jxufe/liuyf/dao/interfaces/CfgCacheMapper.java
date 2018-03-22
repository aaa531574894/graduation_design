package com.jxufe.liuyf.dao.interfaces;

import com.jxufe.liuyf.fsv.common.bean.CfgCache;
import com.jxufe.liuyf.fsv.common.bean.CfgCacheExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CfgCacheMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CFG_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    int countByExample(CfgCacheExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CFG_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    int deleteByExample(CfgCacheExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CFG_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    int deleteByPrimaryKey(String tableName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CFG_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    int insert(CfgCache record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CFG_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    int insertSelective(CfgCache record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CFG_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    List<CfgCache> selectByExample(CfgCacheExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CFG_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    CfgCache selectByPrimaryKey(String tableName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CFG_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    int updateByExampleSelective(@Param("record") CfgCache record, @Param("example") CfgCacheExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CFG_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    int updateByExample(@Param("record") CfgCache record, @Param("example") CfgCacheExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CFG_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    int updateByPrimaryKeySelective(CfgCache record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CFG_CACHE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    int updateByPrimaryKey(CfgCache record);
}