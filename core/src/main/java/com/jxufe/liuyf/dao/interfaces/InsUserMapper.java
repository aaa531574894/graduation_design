package com.jxufe.liuyf.dao.interfaces;

import com.jxufe.liuyf.fsv.common.bean.InsUser;
import com.jxufe.liuyf.fsv.common.bean.InsUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InsUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INS_USER
     *
     * @mbggenerated Fri Apr 13 16:32:05 CST 2018
     */
    int countByExample(InsUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INS_USER
     *
     * @mbggenerated Fri Apr 13 16:32:05 CST 2018
     */
    int deleteByExample(InsUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INS_USER
     *
     * @mbggenerated Fri Apr 13 16:32:05 CST 2018
     */
    int deleteByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INS_USER
     *
     * @mbggenerated Fri Apr 13 16:32:05 CST 2018
     */
    int insert(InsUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INS_USER
     *
     * @mbggenerated Fri Apr 13 16:32:05 CST 2018
     */
    int insertSelective(InsUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INS_USER
     *
     * @mbggenerated Fri Apr 13 16:32:05 CST 2018
     */
    List<InsUser> selectByExample(InsUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INS_USER
     *
     * @mbggenerated Fri Apr 13 16:32:05 CST 2018
     */
    InsUser selectByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INS_USER
     *
     * @mbggenerated Fri Apr 13 16:32:05 CST 2018
     */
    int updateByExampleSelective(@Param("record") InsUser record, @Param("example") InsUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INS_USER
     *
     * @mbggenerated Fri Apr 13 16:32:05 CST 2018
     */
    int updateByExample(@Param("record") InsUser record, @Param("example") InsUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INS_USER
     *
     * @mbggenerated Fri Apr 13 16:32:05 CST 2018
     */
    int updateByPrimaryKeySelective(InsUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INS_USER
     *
     * @mbggenerated Fri Apr 13 16:32:05 CST 2018
     */
    int updateByPrimaryKey(InsUser record);
}