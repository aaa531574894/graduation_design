package com.jxufe.liuyf.controllers;

import com.jxufe.liuyf.cache.BasicRedisMultiper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * description: please add the description
 * author: Navy
 * create_date : 2018/4/19
 * create_time : 16:23
 */

@Controller
public class CommonController extends BasicRedisMultiper {
    private final static Logger log = LoggerFactory.getLogger(CommonController.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
    Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();


    @RequestMapping({"/", ""})
    public String turnToIndexPage(Model model) {
        HashMap params = new HashMap();
        warpIndexContent(params);
        model.addAllAttributes(params);
        return "index";
    }

    @RequestMapping("/test")
    public String testThymeleaf(Model model) {
        model.addAttribute("testCache", getPageContent("index", "DEFAULT", "index_banner_pic0", "URL"));
        return "ThymeleafTest";
    }


    private void warpIndexContent(Map map) {
        //banner_data
        map.put("index_banner_pic0_url", getPageContent("index", "DEFAULT", "index_banner_pic0_url", "URL"));
        map.put("index_banner_pic1_url", getPageContent("index", "DEFAULT", "index_banner_pic1_url", "URL"));
        map.put("index_banner_pic2_url", getPageContent("index", "DEFAULT", "index_banner_pic2_url", "URL"));
        map.put("index_banner_slogan_text", getPageContent("index", "DEFAULT", "index_banner_slogan_text", "TEXT"));
        map.put("index_banner_pic0_text", getPageContent("index", "DEFAULT", "index_banner_pic0_text", "TEXT"));
        map.put("index_banner_pic1_text", getPageContent("index", "DEFAULT", "index_banner_pic1_text", "TEXT"));
        map.put("index_banner_pic2_text", getPageContent("index", "DEFAULT", "index_banner_pic2_text", "TEXT"));
        //banner-bottom_data
        map.put("index_banner_bottom_11_text", getPageContent("index", "DEFAULT", "index_banner_bottom_11_text", "TEXT"));
        map.put("index_banner_bottom_21_text", getPageContent("index", "DEFAULT", "index_banner_bottom_21_text", "TEXT"));
        map.put("index_banner_bottom_22_text", getPageContent("index", "DEFAULT", "index_banner_bottom_22_text", "TEXT"));
        map.put("index_banner_bottom_31_text", getPageContent("index", "DEFAULT", "index_banner_bottom_31_text", "TEXT"));
        map.put("index_banner_bottom_11_url", getPageContent("index", "DEFAULT", "index_banner_bottom_11_url", "URL"));
        map.put("index_banner_bottom_21_url", getPageContent("index", "DEFAULT", "index_banner_bottom_21_url", "URL"));
        map.put("index_banner_bottom_22_url", getPageContent("index", "DEFAULT", "index_banner_bottom_22_url", "URL"));
        map.put("index_banner_bottom_31_url", getPageContent("index", "DEFAULT", "index_banner_bottom_31_url", "URL"));
        //collections_data
        map.put("index_collections_11_now_text",getPageContent("index","DEFAULT","index_collections_11_now_text","TEXT"));
        map.put("index_collections_11_pre_text",getPageContent("index","DEFAULT","index_collections_11_pre_text","TEXT"));
        map.put("index_collections_11_text",getPageContent("index","DEFAULT","index_collections_11_text","TEXT"));
        map.put("index_collections_12_now_text",getPageContent("index","DEFAULT","index_collections_12_now_text","TEXT"));
        map.put("index_collections_12_pre_text",getPageContent("index","DEFAULT","index_collections_12_pre_text","TEXT"));
        map.put("index_collections_12_text",getPageContent("index","DEFAULT","index_collections_12_text","TEXT"));
        map.put("index_collections_21_now_text",getPageContent("index","DEFAULT","index_collections_21_now_text","TEXT"));
        map.put("index_collections_21_pre_text",getPageContent("index","DEFAULT","index_collections_21_pre_text","TEXT"));
        map.put("index_collections_21_text",getPageContent("index","DEFAULT","index_collections_21_text","TEXT"));
        map.put("index_collections_22_now_text",getPageContent("index","DEFAULT","index_collections_22_now_text","TEXT"));
        map.put("index_collections_22_pre_text",getPageContent("index","DEFAULT","index_collections_22_pre_text","TEXT"));
        map.put("index_collections_22_text",getPageContent("index","DEFAULT","index_collections_22_text","TEXT"));
        map.put("index_collections_23_now_text",getPageContent("index","DEFAULT","index_collections_23_now_text","TEXT"));
        map.put("index_collections_23_pre_text",getPageContent("index","DEFAULT","index_collections_23_pre_text","TEXT"));
        map.put("index_collections_23_text",getPageContent("index","DEFAULT","index_collections_23_text","TEXT"));
        map.put("index_collections_31_now_text",getPageContent("index","DEFAULT","index_collections_31_now_text","TEXT"));
        map.put("index_collections_31_pre_text",getPageContent("index","DEFAULT","index_collections_31_pre_text","TEXT"));
        map.put("index_collections_31_text",getPageContent("index","DEFAULT","index_collections_31_text","TEXT"));
        map.put("index_collections_32_now_text",getPageContent("index","DEFAULT","index_collections_32_now_text","TEXT"));
        map.put("index_collections_32_pre_text",getPageContent("index","DEFAULT","index_collections_32_pre_text","TEXT"));
        map.put("index_collections_32_text",getPageContent("index","DEFAULT","index_collections_32_text","TEXT"));
        map.put("index_collections_11_url",getPageContent("index","DEFAULT","index_collections_11_url","URL"));
        map.put("index_collections_12_url",getPageContent("index","DEFAULT","index_collections_12_url","URL"));
        map.put("index_collections_21_url",getPageContent("index","DEFAULT","index_collections_21_url","URL"));
        map.put("index_collections_22_url",getPageContent("index","DEFAULT","index_collections_22_url","URL"));
        map.put("index_collections_23_url",getPageContent("index","DEFAULT","index_collections_23_url","URL"));
        map.put("index_collections_31_url",getPageContent("index","DEFAULT","index_collections_31_url","URL"));
        map.put("index_collections_32_url",getPageContent("index","DEFAULT","index_collections_32_url","URL"));
        //seckill
        map.put("index_seckill_desc_text",getPageContent("index","DEFAULT","index_seckill_desc_text","TEXT"));
        map.put("index_seckill_now_text",getPageContent("index","DEFAULT","index_seckill_now_text","TEXT"));
        map.put("index_seckill_pre_text",getPageContent("index","DEFAULT","index_seckill_pre_text","TEXT"));
        map.put("index_seckill_title_text",getPageContent("index","DEFAULT","index_seckill_title_text","TEXT"));
        map.put("index_seckill_jpg_url",getPageContent("index","DEFAULT","index_seckill_jpg_url","URL"));
        String dateTime = getPageContent("index","DEFAULT","index_seckill_endtime_text","TEXT");
        long timeleft=-1;
        try {
            calendar1.setTime(dateFormat.parse(dateTime));
            calendar2.setTime(new Date());
            timeleft = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (timeleft == -1) {
            throw new RuntimeException("获取优惠剩余时间失败");
        }
        map.put("index_seckill_timeleft_text",timeleft);


    }


}
