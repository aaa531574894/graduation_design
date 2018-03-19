package com.jxufe.liuyf.controllers;

import com.jxufe.liuyf.dubbo.DubboUtils;
import com.jxufe.lyf.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * desc: please add the description
 * author:LYF
 * create_date : 2018/3/14
 * create_time : 9:58
 */
@Controller
@RequestMapping("/login")
public class SecurityController {
    private final static Logger log = LoggerFactory.getLogger(SecurityController.class);


    @RequestMapping(value = "/check", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    //注意produces  response的返回格式
    @ResponseBody()
    public String check(@RequestBody String params, HttpServletResponse response) throws IOException {
        HashMap hashMap = new HashMap();
        hashMap.put("flag", "SUCCESS");
        hashMap.put("page", "index");
        return JsonUtils.object2Json(hashMap);
    }

    @RequestMapping(value = {"", "/"})
    public String login(HttpSession session) throws IOException {
        log.info("切入登录页面");
        if (session.isNew()) {
            log.info("新用户");
            return "login";
        } else {
            log.info("持有session,直接跳转+sessionid :" + session.getId());
            return "index";
        }
    }

    @RequestMapping(value = "/login/register", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public void register(@RequestBody String params) {

    }

    @RequestMapping("/checkDubbo")
    @ResponseBody
    public String checkDubbo() {
        DubboUtils.callDubbo("TEST", new HashMap());
        return "OK";
    }
}

