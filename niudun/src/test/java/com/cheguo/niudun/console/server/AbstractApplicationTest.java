package com.cheguo.niudun.console.server;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 测试基类
 *
 * @author spy
 * @date 2018-04-25
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractApplicationTest {

    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ApplicationContext ctx;

    @Before
    public void before() {

    }

    protected Boolean randomFlag() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int randomNum = random.nextInt(10);
        return randomNum % 2 == 0;
    }

    protected String randomStr() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    protected String randomNumberStr() {
        return RandomStringUtils.randomNumeric(10);
    }


}
