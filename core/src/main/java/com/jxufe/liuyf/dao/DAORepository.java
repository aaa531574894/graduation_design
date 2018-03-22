package com.jxufe.liuyf.dao;

import com.jxufe.lyf.utils.ClassUtils;
import com.jxufe.lyf.utils.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:   储存com.jxufe.liuyf.dao.interfaces 包下所有的表名和class的映射关系
 * author: LYF
 * create_date : 2018/3/21
 * create_time : 21:19
 */
@Component
@Scope("singleton")
public class DAORepository {
    private final static Logger log = LoggerFactory.getLogger(DAORepository.class);

    private final static String daoPkgName = "com.jxufe.liuyf.dao.interfaces";

    private SqlSession sqlSession;

    private final Map<String, Class> INSTANCE = new HashMap<>();       //将所有Mapper接口 以 tablename, class 的映射关系放在instance中


    public Object getMapper(String tableName) {
        Object rtnObj;
        if (StringUtils.isNullOrEmpty(tableName)) {
            log.error("传入的参数不能为空");
            return null;
        }
        Class mapper;
        if (INSTANCE.size() != 0 && INSTANCE != null) {
            mapper = INSTANCE.get(tableName.toUpperCase());
            if (mapper == null) {
                log.error("并不存在此表对应的DAOMapper");
                return null;
            } else {
                rtnObj = sqlSession.getMapper(mapper);
                return rtnObj;
            }
        } else {
            log.error("DAORepo 中为空,请检查");
            throw new NullPointerException("DAORepository中持有的对象INSTANCE为空,得不到表名对应的mapper对象,请检查");
        }
    }

    //初始化时候扫描dao.interface下面的所有类
    private void init() {
        List<Class<?>> list;
        try {
            list = ClassUtils.getClassList(DAORepository.daoPkgName, false, null);
            for (Class clazz : list) {
                INSTANCE.put(StringUtils.getTableNameByMapper(clazz).toUpperCase(), clazz);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public DAORepository() {
        init();
    }

    @Autowired
    public void setSqlSessions(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

}
