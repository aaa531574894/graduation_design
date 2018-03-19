package com.jxufe.liuyf.fsv.interfaces;

import java.util.Map;

/**
 * desc: 权限鉴定FSV
 * author:LYF
 * create_date : 2018/3/14
 * create_time : 20:13
 */
public interface ISecurityFSV {
     /**
     　* @Description: 是否已注册用户
     　* @author liuyf
     　* @date 2018/3/14 20:15
     　*/
    public boolean isRegisted(Map map);

     /**
     　* @Description: 新用户注册
     　* @author liuyf
     　* @date 2018/3/14 20:16
     　*/
    void registerNewUser();


}
