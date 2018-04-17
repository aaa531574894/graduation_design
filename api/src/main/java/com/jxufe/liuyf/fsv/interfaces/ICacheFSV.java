package com.jxufe.liuyf.fsv.interfaces;

import com.jxufe.liuyf.fsv.common.DubboResult;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/21
 * create_time : 21:06
 */
public interface ICacheFSV {

    DubboResult refreshByTableName(String tableName);

    DubboResult refreshAll();
}
