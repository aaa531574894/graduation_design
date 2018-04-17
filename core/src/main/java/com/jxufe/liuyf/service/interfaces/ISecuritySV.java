package com.jxufe.liuyf.service.interfaces;

import com.jxufe.liuyf.fsv.common.bean.InsUser;

/**
 * description: please add the description
 * author: Navy
 * create_date : 2018/4/13
 * create_time : 16:52
 */
public interface ISecuritySV {
    boolean register(InsUser insUser) throws Exception;

    boolean isRegisted(InsUser insUser) throws Exception;

    boolean checkLoginPermission(InsUser insUser) throws Exception;


}
