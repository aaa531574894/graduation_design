package com.jxufe.liuyf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description: please add the description
 * author: Navy
 * create_date : 2018/4/19
 * create_time : 16:23
 */

@Controller
public class CommonController {

    @RequestMapping({"/", ""})
    public String turnToIndexPage() {
        return "index";
    }
}
