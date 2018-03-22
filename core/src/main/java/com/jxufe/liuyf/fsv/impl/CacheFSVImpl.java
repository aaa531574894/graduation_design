package com.jxufe.liuyf.fsv.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jxufe.liuyf.fsv.interfaces.ICacheFSV;
import com.jxufe.liuyf.service.interfaces.ICacheSV;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description: redis缓存暴露的服务
 * author: LYF
 * create_date : 2018/3/21
 * create_time : 21:08
 */
@Service(version = "1.0.0")
public class CacheFSVImpl implements ICacheFSV {
    private ICacheSV iCacheSV;

    /**
     * @Description:  刷新指定表的缓存
     * @param tableName   表名  例如  CfgCache
     */
    @Override
    public void refreshByTableName(String tableName) {
        iCacheSV.refreshCacheByTableName(tableName);
    }

    /**
     * @Description: 全量刷新缓存
     */
    @Override
    public void refreshAll() {
        iCacheSV.refreshAllCache();
    }



    @Autowired
    public void setiCacheSV(ICacheSV iCacheSV) {
        this.iCacheSV = iCacheSV;
    }
}
