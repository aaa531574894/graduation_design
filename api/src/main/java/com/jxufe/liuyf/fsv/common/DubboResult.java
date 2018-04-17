package com.jxufe.liuyf.fsv.common;

import java.io.Serializable;
import java.util.UUID;

/**
 * description: 基于dubbo 调用的返回结果
 * author: Navy
 * create_date : 2018/4/13
 * create_time : 16:41
 */
public class DubboResult implements Serializable {
    private transient UUID uuid;            //唯一调用id
    private boolean processState; //执行成功还是失败
    private String failReason;    //dubbo调用是否成功   报错填false  如果没报错就是true
    private Object rtnObj;        //如果需要返回对象，存储在这里

    public DubboResult() {
        this.uuid = UUID.randomUUID();
    }


    public Object getRtnObj() {
        return rtnObj;
    }

    public void setRtnObj(Object rtnObj) {
        this.rtnObj = rtnObj;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean getProcessState() {
        return processState;
    }

    public void setProcessState(boolean processState) {
        this.processState = processState;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
