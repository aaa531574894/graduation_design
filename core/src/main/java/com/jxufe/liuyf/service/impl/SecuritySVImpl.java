package com.jxufe.liuyf.service.impl;

import com.jxufe.liuyf.cache.redis.JedisController;
import com.jxufe.liuyf.dao.interfaces.InsUserMapper;
import com.jxufe.liuyf.fsv.common.bean.InsUser;
import com.jxufe.liuyf.service.interfaces.ISecuritySV;
import com.jxufe.lyf.utils.JsonUtils;
import com.jxufe.lyf.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description: please add the description
 * author: Navy
 * create_date : 2018/4/13
 * create_time : 16:53
 */
@Service
public class SecuritySVImpl implements ISecuritySV {
    private static final Logger log = LoggerFactory.getLogger(SecuritySVImpl.class);
    @Autowired
    private InsUserMapper insUserMapper;
    @Autowired
    private JedisController jedisController;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(InsUser insUser) throws Exception {

        //数据库中插入
        insUserMapper.insert(insUser);

        //检查是否已经插入并且刷新缓存
        if (insUserMapper.selectByPrimaryKey(insUser.getUserId()) != null && !jedisController.containsKey(insUser.getIndex())) {
            jedisController.set(insUser.getIndex(), insUser);
        }else {
            log.error("插入数据库失败或者用户已存在" + JsonUtils.object2Json(insUser));
        }

        //根据缓存是否刷入判断是否注册成功
        if (isRegisted(insUser)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isRegisted(InsUser insUser) throws Exception {
        if (insUser == null || insUser.getUserId() == null) {
            throw new NullPointerException("传入参数为null或输入的用户名为null");
        }
        //走redis查询是否已经注册;
        if (jedisController.containsKey(insUser.getIndex())) {
            log.debug("查询用户是否已经注册   " + insUser.getIndex());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkLoginPermission(InsUser insUser) throws Exception {
        if (insUser == null) {
            throw new NullPointerException("入参不能为空");
        }
        if (StringUtils.isNullOrEmpty(insUser.getUserId()) || StringUtils.isNullOrEmpty(insUser.getLoginPassword())) {
            throw new NullPointerException("传入的用户名或者密码不能为空");
        }

        //判断是否已经注册    从缓存中拿用户信息  不走DB
        InsUser expectUser = null;
        expectUser = (InsUser) jedisController.getAsObject(insUser.getIndex());
        if (expectUser != null) {
            if (expectUser.getLoginPassword().equals( insUser.getLoginPassword()) && expectUser.getUserId().equals(insUser.getUserId())) {
                log.info("该用户已注册，可以登录" + insUser.getUserId());
                return true;
            }else {
                return false;
            }
        } else {
            return false;
        }
    }


}
