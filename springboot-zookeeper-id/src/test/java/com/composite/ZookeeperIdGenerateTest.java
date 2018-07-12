package com.composite;

import com.composite.utils.ZookeeperIdGenerateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperIdGenerateTest {

    @Autowired
    ZookeeperIdGenerateUtils generateId;

    @Test
    public void testGenerateZookeeperId() {
        try {
            generateId.start();
            try {
                for (int i = 0; i < 10; i++) {
                    String id = generateId.generateId(ZookeeperIdGenerateUtils.RemoveMethod.NONE);
                    System.out.println(id);
                }
            } finally {
                generateId.stop();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
