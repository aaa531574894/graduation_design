package com.jxufe.liuyf.cache;

import com.jxufe.liuyf.cache.redis.JedisController;
import com.jxufe.liuyf.fsv.common.bean.CfgPageContent;
import com.jxufe.lyf.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description: 封装一些操作redis缓存的方法  如果需要操作redis可以直接继承一下
 *
 * author: Navy
 * create_date : 2018/4/20
 * create_time : 11:31
 */
public class BasicRedisMultiper {
    private static final Logger log = LoggerFactory.getLogger(BasicRedisMultiper.class);

    @Autowired
    protected JedisController jedisController;

    public String getPageContent(String pageUrl,String pageCategory,String contentLocation,String contentType) {
        if(StringUtils.isNullOrEmpty(pageUrl)||StringUtils.isNullOrEmpty(pageCategory)||StringUtils.isNullOrEmpty(contentLocation)||StringUtils.isNullOrEmpty(contentType)){
            throw new NullPointerException("查找索引时传入的参数为null, please check");
        }
        String index=null;
        CfgPageContent cfgPageContent = new CfgPageContent();
        cfgPageContent.setPageUrl(pageUrl);
        cfgPageContent.setPageCategory(pageCategory);
        cfgPageContent.setContentLocation(contentLocation);
        cfgPageContent.setContentType(contentType);
        index=cfgPageContent.getIndex();
        try {
            cfgPageContent=(CfgPageContent)jedisController.getAsObject(index);
        } catch (Exception e) {
            log.error("查找缓存失败" + e);
            e.printStackTrace();
        }
        return cfgPageContent.getTheContent();
    }



}
