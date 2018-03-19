package com.jxufe.liuyf;

import com.jxufe.liuyf.dao.interfaces.CfgBusinessCodeMapper;
import com.jxufe.liuyf.fsv.common.bean.CfgBusinessCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/17
 * create_time : 18:58
 */
public class TestCenter extends AbstactBaseTest {
    private static final Logger log = LoggerFactory.getLogger(TestCenter.class);

    @Autowired
    private CfgBusinessCodeMapper cfgBusinessCodeMapper;

    @Override
    public void test() {
        List<CfgBusinessCode> list= cfgBusinessCodeMapper.selectByExample(null);
        for (CfgBusinessCode item : list) {
            System.out.println(item.getPk());
        }

    }
}
