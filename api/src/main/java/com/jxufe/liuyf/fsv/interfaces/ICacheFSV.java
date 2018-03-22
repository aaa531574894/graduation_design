package com.jxufe.liuyf.fsv.interfaces;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/21
 * create_time : 21:06
 */
public interface ICacheFSV {

    void refreshByTableName(String tableName);

    void refreshAll();
}
