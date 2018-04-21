package com.jxufe.liuyf.fsv.common.bean;


import com.jxufe.liuyf.fsv.common.AbstractCacheTable;
import com.jxufe.liuyf.fsv.common.CacheIndex;
import org.codehaus.jackson.annotate.JsonIgnore;

public class CfgBusinessCode extends AbstractCacheTable implements CacheIndex {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CFG_BUSINESS_CODE.BUSICODE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    private String busicode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CFG_BUSINESS_CODE.INTERFACEE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    private String interfacee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CFG_BUSINESS_CODE.MOTHOD
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    private String mothod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CFG_BUSINESS_CODE.STATE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    private String state;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CFG_BUSINESS_CODE.BUSICODE
     *
     * @return the value of CFG_BUSINESS_CODE.BUSICODE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public String getBusicode() {
        return busicode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CFG_BUSINESS_CODE.BUSICODE
     *
     * @param busicode the value for CFG_BUSINESS_CODE.BUSICODE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public void setBusicode(String busicode) {
        this.busicode = busicode == null ? null : busicode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CFG_BUSINESS_CODE.INTERFACEE
     *
     * @return the value of CFG_BUSINESS_CODE.INTERFACEE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public String getInterfacee() {
        return interfacee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CFG_BUSINESS_CODE.INTERFACEE
     *
     * @param interfacee the value for CFG_BUSINESS_CODE.INTERFACEE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public void setInterfacee(String interfacee) {
        this.interfacee = interfacee == null ? null : interfacee.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CFG_BUSINESS_CODE.MOTHOD
     *
     * @return the value of CFG_BUSINESS_CODE.MOTHOD
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public String getMothod() {
        return mothod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CFG_BUSINESS_CODE.MOTHOD
     *
     * @param mothod the value for CFG_BUSINESS_CODE.MOTHOD
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public void setMothod(String mothod) {
        this.mothod = mothod == null ? null : mothod.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CFG_BUSINESS_CODE.STATE
     *
     * @return the value of CFG_BUSINESS_CODE.STATE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CFG_BUSINESS_CODE.STATE
     *
     * @param state the value for CFG_BUSINESS_CODE.STATE
     *
     * @mbggenerated Mon Mar 19 20:50:43 CST 2018
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    @JsonIgnore
    @Override
    public String getIndex() {
        return getFullClassName(this)+getBusicode();

    }
}