package com.jxufe.liuyf;

import com.jxufe.liuyf.cache.redis.ShellCommandExecutor;
import com.jxufe.liuyf.service.interfaces.ICacheSV;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * description: ��������   ��Ҫ�����Եķ���  ����@Testע��
 * author: LYF
 * create_date : 2018/3/17
 * create_time : 18:58
 */
public class TestCenter extends AbstactBaseTest {
    private static final Logger log = LoggerFactory.getLogger(TestCenter.class);


    @Autowired
    private ShellCommandExecutor shellCommandExecutor;

    public void test() {

    }

    @Test
    public void TestFlushDB() {
        shellCommandExecutor.flushDB();

    }

}
