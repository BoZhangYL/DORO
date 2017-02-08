package doro.testcase;

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
    public void testPullDownNotificationBar(){
        //检查状态栏是否全屏显示
        pullDownNotificationBar();
        Assert.assertTrue("状态栏没有全屏显示",getObjectByClass(NOTIFICATIONBAR_BRIGHTNESS_CLASS).exists());
    }

    @Test
    public void testTimeAndDate(){
        //检查当前时间与日期
        pullDownNotificationBar();
        checkTimeAndDate();
    }

    @Test
    public void testMultiUserButton(){
        //检查User按钮显示
        pullDownNotificationBar();
        checkMultiUserButton();
        mDevice.pressBack();
    }

    @Test
    public void testSettingsButton(){
        //检查Settings按钮显示
        pullDownNotificationBar();
        checkSettingButton();
        mDevice.pressBack();
    }

    @Test
    public void testDisplayBrightness(){
        //检查亮度条显示
        pullDownNotificationBar();
        Assert.assertTrue("亮度条没有显示！",getObjectById(NOTIFICATIONBAR_BRIGHTNESS_ID).exists());
    }

    @Test
    public void testButtons()throws UiObjectNotFoundException{
        //检查五个切换按钮显示
        pullDownNotificationBar();
        Assert.assertTrue("Wifi_Button没有显示！",getObjectByText("Wifi").exists());
        Assert.assertTrue("Mobile data_Button没有显示！",text_exists("No SIM card")||text_exists("No service")||text_exists("CU")||text_exists("CMCC"));
        Assert.assertTrue("Bluetooth_Button没有显示！",getObjectByText("Bluetooth").exists());
        Assert.assertTrue("Torch_Button没有显示！",getObjectByText("Torch").exists());
        Assert.assertTrue("Airplane mode_Button没有显示！",getObjectByText("Airplane mode").exists());
    }
/**
 * 如下case doro不适用
 3.通知栏显示
 4.点击任意一个通知
 5.放大状态栏后运营商的显示
 */
}
