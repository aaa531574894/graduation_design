package com.jxufe.liuyf.fsv.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jxufe.liuyf.fsv.common.DubboResult;
import com.jxufe.liuyf.fsv.interfaces.ICacheFSV;
import com.jxufe.liuyf.service.interfaces.ICacheSV;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

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
     * @param tableName 表名  例如  CfgCache
     * @Description: 刷新指定表的缓存
     */
    @Override
    public DubboResult refreshByTableName(String tableName) {
        DubboResult result = new DubboResult();
        try {
            iCacheSV.refreshCacheByTableName(tableName);
            result.setProcessState(true);
        } catch (Exception e) {
            result.setFailReason(e.getMessage().toString());
            result.setProcessState(false);
        }
        return result;
    }

    /**
     * @Description: 全量刷新缓存
     */
    @Override
    public DubboResult refreshAll() {
        DubboResult result = new DubboResult();
        try {
            iCacheSV.refreshAllCache();
            result.setProcessState(true);
        } catch (Exception e) {
            result.setProcessState(false);
            result.setFailReason(e.getMessage());
        }
        return result;
    }


    @Autowired
    public void setiCacheSV(ICacheSV iCacheSV) {
        this.iCacheSV = iCacheSV;
    }
}
