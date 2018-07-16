package com.composite.springbootelk;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ElkApplicationTests {

    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() throws Exception {
        for (int i = 0; i < 100; i++) {
            logger.info("输出info  ");
            logger.debug("输出debug+嗡嗡嗡");
            logger.error("输出error  嗡嗡嗡我");
        }
    }


}
