package doro.testcase;

import android.content.SyncStatusObserver;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    public void Case1_testPullDownNotificationBar(){
        //检查状态栏是否全屏显示
        pullDownNotificationBar();
        Assert.assertTrue("NotificationBar is displayed incorrectly!",getObjectByClass(NOTIFICATIONBAR_BRIGHTNESS_CLASS).exists());
    }

    @Test
    public void Case2_testTimeAndDate(){
        //检查当前时间与日期
        pullDownNotificationBar();
        checkTimeAndDate();
    }

    @Test
    public void Case3_testMultiUserButton(){
        //检查User按钮显示
        pullDownNotificationBar();
        checkMultiUserButton();
        mDevice.pressBack();
    }

    @Test
    public void Case4_testSettingsButton(){
        //检查Settings按钮显示
        pullDownNotificationBar();
        checkSettingButton();
        mDevice.pressBack();
    }

    @Test
    public void Case5_testDisplayBrightness(){
        //检查亮度条显示
        pullDownNotificationBar();
        Assert.assertTrue("Brightness is displayed incorrectly!",getObjectById(NOTIFICATIONBAR_BRIGHTNESS_ID).exists());
    }

    @Test
    public void Case6_testButtons()throws UiObjectNotFoundException{
        //检查五个切换按钮显示

        pullDownNotificationBar();
        Assert.assertTrue("Wifi Button is displayed incorrectly!",
                getObjectByText("Wifi").exists()||
                        getUiObjectByText("CKT").exists()||
                        getObjectByText("CK-Test-CTS").exists());
        System.out.println(text_exists("No SIM card"));
        System.out.println(text_exists("No service"));
        System.out.println(text_exists("CU"));
        System.out.println(text_exists("CMCC"));
        System.out.println(text_exists("Emergency Calls Only"));

        Assert.assertFalse("Mobile data Button is displayed incorrectly!",text_exists("No SIM card")||text_exists("No service")||text_exists("CU")||text_exists("CMCC")||text_exists("Emergency Calls Only"));
        Assert.assertTrue("Bluetooth Button is displayed incorrectly!",getObjectByText("Bluetooth").exists());
        Assert.assertTrue("Torch Button is displayed incorrectly!",getObjectByText("Torch").exists());
        Assert.assertTrue("Airplane mode Button is displayed incorrectly!",getObjectByText("Aeroplane mode").exists());
    }
/**
 * 如下case doro不适用
 3.通知栏显示
 4.点击任意一个通知
 5.放大状态栏后运营商的显示
 */
}
