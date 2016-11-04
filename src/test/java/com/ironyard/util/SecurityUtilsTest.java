package com.ironyard.util;

import com.ironyard.security.SecurityUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jasonskipper on 10/31/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SecurityUtilsTest {

    @Test
    public void validToekenShouldBeValid() throws Exception {
        String token = null;
        try {
            token = SecurityUtils.genToken();
            System.out.println(token);
            Assert.assertTrue(SecurityUtils.isValidKey(token));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void fakeToekenShouldNotBeValid() throws Exception {
        String token = null;
        try {
            token = SecurityUtils.genToken()+"FAKE";
            System.out.println(token);
            Assert.assertFalse(SecurityUtils.isValidKey(token));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}