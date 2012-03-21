package com.fw.springtest;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.test.context.ContextConfiguration;

@RunWith(SpringParameterizedRunner.class)
@ContextConfiguration(locations = { "classpath:appContext.xml" })
public class MyTest {
    private int userId;

    @Resource
    private UserService userService;

    public MyTest(int userId) {
        this.userId = userId;
    }

    @Test
    public void run() {
        userService.createUser(userId);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { 0 }, { 1 }, { 2 }, { 3 }, { 4 }, { 5 },
                { 6 } });
    }
}
