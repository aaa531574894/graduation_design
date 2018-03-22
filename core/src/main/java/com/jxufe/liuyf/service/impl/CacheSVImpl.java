package com.jxufe.liuyf.service.impl;

import com.jxufe.liuyf.cache.redis.JedisController;
import com.jxufe.liuyf.dao.DAORepository;
import com.jxufe.liuyf.dao.interfaces.CfgCacheMapper;
import com.jxufe.liuyf.fsv.common.CacheIndex;
import com.jxufe.liuyf.fsv.common.bean.CfgCache;
import com.jxufe.liuyf.service.interfaces.ICacheSV;
import com.jxufe.lyf.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * description: 控制缓存sv
 * author: LYF
 * create_date : 2018/3/21
 * create_time : 21:10
 */
@Service
public class CacheSVImpl implements ICacheSV {
    private final static Logger log = LoggerFactory.getLogger(CacheSVImpl.class);
    private JedisController jedisController;
    private DAORepository daoRepository;
    private CfgCacheMapper cfgCacheMapper;

    @Override
    public void refreshCacheByTableName(String tableName) {
        if (StringUtils.isNullOrEmpty(tableName)) {
            log.error("根据表名刷新缓存 , 传入的表名不能为空");
            throw new NullPointerException("根据表名刷新缓存 , 传入的表名不能为空");
        }
        String methodName = "selectByExample";
        Object mapper = daoRepository.getMapper(tableName);
        if (mapper == null) {
            throw new NullPointerException(tableName + " 该表名对应的mapper没读取到");
        }
        Method[] methods = mapper.getClass().getMethods();
        String index;
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                try {
                    List<CacheIndex> lCacheIndex = (List<CacheIndex>) method.invoke(mapper, (Object) null);
                    for (CacheIndex item : lCacheIndex) {
                        jedisController.set(item.getIndex(), item);
                    }
                } catch (IllegalAccessException e) {
                    log.error("method.invode传入参数有问题" + e);
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void refreshAllCache() {
        //读 cfg_cache表  获取需要缓存的表名  然后调用refreshCacheByTableName 方法
        List<CfgCache> list = cfgCacheMapper.selectByExample(null);
        String tableName;
        if (list != null && list.size() != 0) {
            for (CfgCache cfgCache : list) {
                tableName = cfgCache.getTableName();
                refreshCacheByTableName(tableName);
            }
        }
        jedisController.set(JedisController.IS_CACHE_LOADED, "TRUE");
    }


    @Autowired
    public void setJedisController(JedisController jedisController) {
        this.jedisController = jedisController;
    }

    @Autowired
    public void setDaoRepository(DAORepository daoRepository) {
        this.daoRepository = daoRepository;
    }

    @Autowired
    public void setCfgCacheMapper(CfgCacheMapper cfgCacheMapper) {
        this.cfgCacheMapper = cfgCacheMapper;
    }
}


