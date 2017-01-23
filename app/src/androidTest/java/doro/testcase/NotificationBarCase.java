package doro.testcase;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import doro.action.NotificationBarAction;

import static doro.page.NotificationBarPage.*;

/**
 * Created by Tao.Wang on 2017/1/19.
 */
@RunWith(AndroidJUnit4.class)
public class NotificationBarCase extends NotificationBarAction {
    @BeforeClass
    public static void initNotificationBar(){
        unLock();
    }

    @Test
    public void testPullDownNotificationBar(){
        mDevice.pressBack();
        pullDownNotificationBar();
        Assert.assertTrue("状态栏没有全屏显示",getObjectByClass(NOTIFICATIONBAR_BRIGHTNESS_CLASS).exists());
    }

    @Test
    public void testTimeAndDate(){
        mDevice.pressBack();
        pullDownNotificationBar();
        checkTimeAndDate();
    }

    @Test
    public void testMultiUserButton(){
        mDevice.pressBack();
        pullDownNotificationBar();
        checkMultiUserButton();
    }




}
