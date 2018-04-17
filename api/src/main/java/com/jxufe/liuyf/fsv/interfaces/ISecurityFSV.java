package com.jxufe.liuyf.fsv.interfaces;

import com.jxufe.liuyf.fsv.common.DubboResult;

import java.util.Map;

/**
 * desc: 权限管理FSV
 * author:LYF
 * create_date : 2018/3/14
 * create_time : 20:13
 */
public interface ISecurityFSV {

    /**
     * 　* @Description: 新用户注册
     * 　* @author liuyf
     * 　* @date 2018/3/14 20:16
     *
     */
    DubboResult registerNewUser(Map map);

    /**
     * @param
     * @Description: 验证用户名密码
     * @author liuyf
     * @date 2018/4/15 15:50
     */

    DubboResult login(Map map);

}
