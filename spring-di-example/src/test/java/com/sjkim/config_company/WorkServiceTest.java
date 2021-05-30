package com.sjkim.config_company;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanConfig.class})
public class WorkServiceTest extends TestCase {

    @Autowired
    private WorkService workService;

    @Test
    public void testAsk() {
        workService.ask();
    }
}