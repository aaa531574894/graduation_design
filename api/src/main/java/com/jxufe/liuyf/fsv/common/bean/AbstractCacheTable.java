package com.jxufe.liuyf.fsv.common.bean;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/21
 * create_time : 19:47
 */
public abstract  class AbstractCacheTable {
    @JsonIgnore
    private String prefix;
    @JsonIgnore
    protected String getFullClassName(Object obj){
        prefix = obj.getClass().getName();
        return prefix+".";
    }
}
