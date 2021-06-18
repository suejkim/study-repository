package com.sjkim.jdbc;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void studentClassTest() throws Exception {
        Class clazz = Class.forName("com.sjkim.jdbc1.Student");
        logger.info("getName() {}", clazz.getName());
        logger.info("getSimpleName() {}", clazz.getSimpleName());
    }
}
