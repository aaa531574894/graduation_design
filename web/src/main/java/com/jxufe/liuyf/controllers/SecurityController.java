package com.jxufe.liuyf.controllers;

import com.jxufe.liuyf.cache.redis.JedisController;
import com.jxufe.liuyf.dubbo.DubboUtils;
import com.jxufe.liuyf.fsv.common.DubboResult;
import com.jxufe.liuyf.fsv.common.bean.CfgBusinessCode;
import com.jxufe.liuyf.fsv.common.bean.CfgPageContent;
import com.jxufe.liuyf.fsv.common.bean.InsUser;
import com.jxufe.lyf.utils.JsonUtils;
import com.jxufe.lyf.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * desc: 控制用户权限
 * author:LYF
 * create_date : 2018/3/14
 * create_time : 9:58
 */
@Controller
@RequestMapping("/login")
public class SecurityController {
    private final static Logger log = LoggerFactory.getLogger(SecurityController.class);
    @Autowired
    private JedisController jedisController;
    private CfgBusinessCode cfgBusinessCode = new CfgBusinessCode();


    @RequestMapping(value = "/check", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    //注意produces  response的返回格式   produces指明后 返回的是json对象  不是字符串  前台不需要Json.parse()了
    @ResponseBody()
    public String check(@RequestBody String params, HttpServletResponse response) throws IOException {
        Map<String, String> rtnMap = new HashMap<String, String>();
        try {
            if (params == null) {
                throw new NullPointerException("传入的参数不能为null");
            }
            Map parasMap = JsonUtils.urlParams2Map(params);
            if (parasMap.get("BUSINESS_CODE") == null) {
                throw new NullPointerException("business_CODE不可为空");
            }
            cfgBusinessCode.setBusicode((String) parasMap.get("BUSINESS_CODE"));
            CfgBusinessCode theBusiCode = null;
            try {
                theBusiCode = (CfgBusinessCode) jedisController.getAsObject(cfgBusinessCode.getIndex());
            } catch (Exception e) {
                e.printStackTrace();
                rtnMap.put("REASION", e.getMessage());
            }
            InsUser thisUser = new InsUser();
            thisUser.setUserId((String) parasMap.get("UNAME"));
            thisUser.setLoginPassword(parasMap.get("PWD").toString());
            DubboResult dubboResult = DubboUtils.callDubbo(theBusiCode, JsonUtils.object2Map(thisUser));
            if (!dubboResult.getProcessState()) {
                log.error("dubbo调用失败: uuid :  " + dubboResult.getUuid() + " 失败原因:  " + dubboResult.getFailReason());
                rtnMap.put("FLAG", "FALUER");
                rtnMap.put("PAGE", "login");
                rtnMap.put("REASON", dubboResult.getFailReason());
            } else if (dubboResult.getProcessState()) {
                if (dubboResult.getRtnObj() == Boolean.TRUE) {
                    rtnMap.put("FLAG", "SUCCESS");
                    rtnMap.put("PAGE", "INDEX");
                } else if (dubboResult.getRtnObj() == Boolean.FALSE) {
                    rtnMap.put("FLAG", "FALUER");
                    rtnMap.put("PAGE", "login");
                    rtnMap.put("REASON", "用户不存在或密码不正确");
                }
            }
        }finally {
            return JsonUtils.object2Json(rtnMap);
        }
    }


    @RequestMapping(value = {"", "/"})
    public String login(HttpSession session) throws IOException {
        log.info("切入登录页面");
        if (session.isNew()) {
            log.info("新用户");
            return "login";
        } else {
            log.info("持有session,直接跳转+sessionid :" + session.getId());
            return "login";
        }
    }

    @RequestMapping(value = "/register", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestBody String params) {
        return null;
    }






}

