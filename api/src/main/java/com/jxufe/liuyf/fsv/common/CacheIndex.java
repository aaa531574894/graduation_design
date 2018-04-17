package com.jxufe.liuyf.fsv.common;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/19
 * create_time : 20:44
 */
public interface CacheIndex {
    /**
     * @Description: 返回表实体的主键, 组合主键就按顺序拼接起来返回
     *                所有需要在redis中缓存的表都要实现此接口
     * @author liuyf
     * @date 2018/3/19 20:56
     * @param
     */

    //注意：实现此方法 需要添加@JsonIgnore注解   不然json2Object会失败
    String getIndex();
}
