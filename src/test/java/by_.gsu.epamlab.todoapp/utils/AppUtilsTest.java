package by_.gsu.epamlab.todoapp.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/TestContext.xml",})
public class AppUtilsTest {

    @Test
    public void testMd5() throws NoSuchAlgorithmException {
        final String randomString = String.valueOf(new Random());
        String utilMd5 = AppUtils.md5(randomString);
        String utilMd5Next = AppUtils.md5(randomString);

        Assert.assertEquals(utilMd5, utilMd5Next);
        Assert.assertNotEquals(utilMd5, randomString);
        Assert.assertEquals(utilMd5.length(), 32);
    }
}
