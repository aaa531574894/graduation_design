package com.jxufe.liuyf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/17
 * create_time : 13:56
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class AbstactBaseTest {
    private Logger log = LoggerFactory.getLogger(AbstactBaseTest.class);

    @Before    //所有测试方法开始前要执行的方法
    public void before() {
        log.error("----------------------------------------------------------------------------------------------");
        log.error("---------------before测试方法---------");
        log.error("----------------------------------------------------------------------------------------------");

    }

    @After
    public void after() {
        log.error("----------------------------------------------------------------------------------------------");
        log.error("---------------after测试方法---------");
        log.error("----------------------------------------------------------------------------------------------");

    }

    @Test   //要实现的测试方法
    public abstract void test();


}
