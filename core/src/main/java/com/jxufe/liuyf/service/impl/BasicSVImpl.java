package com.jxufe.liuyf.service.impl;

import com.jxufe.liuyf.cache.interfaces.ITablePrefix;
import com.jxufe.liuyf.cache.redis.JedisController;
import com.jxufe.liuyf.dao.interfaces.CfgCacheMapper;
import com.jxufe.liuyf.fsv.common.bean.CfgCache;
import com.jxufe.liuyf.fsv.common.bean.CfgCacheExample;
import com.jxufe.liuyf.service.interfaces.IBasicSV;
import com.jxufe.lyf.utils.ClassUtils;
import com.jxufe.lyf.utils.IndexUtils;
import com.jxufe.lyf.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/17
 * create_time : 16:52
 */
@Service
@Scope("prototype")
public class BasicSVImpl implements IBasicSV {
    private final static Logger log = LoggerFactory.getLogger(BasicSVImpl.class);
    private CfgCacheMapper cfgCacheMapper;

    private JedisController jedisController;
    @Override
    public void initCache() {

        List<CfgCache> list = null;
        try {
            list = cfgCacheMapper.selectByExample(new CfgCacheExample());
            if (null != list && list.size() != 0) {
                for (CfgCache cfgCacheBean : list) {
                    refreshSpecificTable(cfgCacheBean.getTableName());  //通过cfg_cahce表名中 列出的表名进行刷新缓存
                }
            }
        } catch (Exception e) {
            log.error(e.toString());
        }


    }

    private void refreshSpecificTable(String tableName) {
        List tableElements = null;
        try {
            tableElements = getAllElementsByTableName(tableName);
            if (null == tableElements) {
                throw new NullPointerException("通过表名获取该表的所有记录失败");
            }
            if (tableElements != null && tableElements.size() != 0) {
                for (Object item : tableElements) {
                    jedisController.set(IndexUtils.getIndexByObject(item), item);
                }
            }
        } catch (Exception e) {
            log.error(e.toString());
        }

    }

    private List getAllElementsByTableName(String tableName) throws InvocationTargetException, IllegalAccessException {
        List rtnList = null;
        if (!StringUtils.isNullOrEmpty(tableName)) {
            String mthodName = StringUtils.mergeStrings("list", tableName, "Bean");  //构造方法名   传入的表名必须符合驼峰命名法  如:CfgCach
            Method[] methods = CfgCacheMapper.class.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(mthodName)) {
                    rtnList = (List) method.invoke(cfgCacheMapper);
                    break;
                }
            }
        } else {
            log.error("传入的表名称错误  值为:" + tableName);
        }
        return rtnList;
    }


    private Class getBeanTypeByTableName(String tableName) {
        Class beanClass=ClassUtils.getClassByTableName(ITablePrefix.BEAN_PREFIX, tableName);
        return beanClass;
    }


    @Autowired
    public void setJedisController(JedisController jedisController) {
        this.jedisController = jedisController;
    }



    @Autowired
    public void setCfgCacheMapper(CfgCacheMapper cfgCacheMapper) {
        this.cfgCacheMapper = cfgCacheMapper;
    }

}
