package com.jxufe.liuyf;

import com.jxufe.liuyf.fsv.common.bean.InsUser;
import com.jxufe.liuyf.service.interfaces.ICacheSV;
import com.jxufe.liuyf.service.interfaces.ISecuritySV;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * description: 测试中心   需要被测试的方法  加上@Test注解
 * author: LYF
 * create_date : 2018/3/17
 * create_time : 18:58
 */
public class TestCenter extends AbstactBaseTest {
    private static final Logger log = LoggerFactory.getLogger(TestCenter.class);

    @Autowired
    private ICacheSV iCacheSV;


    @Autowired
    private ISecuritySV iSecuritySV;

    @Test
    public void TestFlushDB() {
        iCacheSV.refreshAllCache();
        InsUser insUser = new InsUser();
        insUser.setUserId("aaa531574894");
        insUser.setLoginPassword("3871070");
        boolean is = false;
        try {
            is = iSecuritySV.checkLoginPermission(insUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(is);
    }


}
