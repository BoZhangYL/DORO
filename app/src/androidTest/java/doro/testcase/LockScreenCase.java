package doro.testcase;


import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import doro.action.LockScreenAction;

import static doro.page.LockScreenPage.*;

/**
 * Created by Tao.Wang on 2017/1/19.
 */
@RunWith(AndroidJUnit4.class)
public class LockScreenCase extends LockScreenAction {
    @Test
    public void testLockScreen(){
        //进入锁屏界面
        clearAllApplications();//清理后台运行程序
        lockScreen();//锁屏
        Assert.assertTrue("没有进入锁屏界面！", getObjectByDesc(LOCKSCREEN_UNLOCKBUTTON_DESC).exists());//检查是否进入锁屏界面
    }

    @Test
    public void testTopStatusBar()throws UiObjectNotFoundException{
        //检查顶部状态栏显示
        clearAllApplications();//清理后台运行程序
        lockScreen();//锁屏
        Assert.assertTrue("Invalid IMEI没有显示！",getObjectById(LOCKSCREEN_IMEI_ID).exists()&&text_exists(getObjectById(LOCKSCREEN_IMEI_ID).getText()));
        Assert.assertTrue("信号强度没有显示！",id_exists(LOCKSCREEN_SIGNAL_ID)||id_exists(LOCKSCREEN_NOSIMSIGNAL_ID));
        Assert.assertTrue("电量没有显示！",id_exists(LOCKSCREEN_BATTERY_ID)&&id_exists(LOCKSCREEN_BATTERYLEVEL_ID));
        Assert.assertTrue("User没有显示！",id_exists(LOCKSCREEN_USER_ID));
    }

    @Test
    public void testTime()throws UiObjectNotFoundException{
        //检查当前时间显示
        clearAllApplications();//清理后台运行程序
        lockScreen();//锁屏
        Assert.assertTrue("当前时间没有显示！",getObjectById(LOCKSCREEN_TIME_ID).exists()&&text_exists(getObjectById(LOCKSCREEN_TIME_ID).getText()));
    }

    @Test
    public void testDate()throws UiObjectNotFoundException{
        //检查当前日期显示
        clearAllApplications();//清理后台运行程序
        lockScreen();//锁屏
        Assert.assertTrue("当前日期没有显示！",getObjectById(LOCKSCREEN_DATE_ID).exists()&&text_exists(getObjectById(LOCKSCREEN_DATE_ID).getText()));
    }

    @Test
    public void testNotification()throws UiObjectNotFoundException{
        //检查通知栏显示
        clearAllApplications();//清理后台运行程序
        lockScreen();//锁屏
        Assert.assertTrue("通知栏没有显示！",id_exists(LOCKSCREEN_NOTIFICATION_ID));
    }

    @Test
    public void testKeyguardBottomArea() throws UiObjectNotFoundException{
        //检查Left键/解锁键/Camera键显示
        clearAllApplications();//清理后台运行程序
        lockScreen();//锁屏
        Assert.assertTrue("解锁键没有显示！",id_exists(LOCKSCREEN_UNLOCKBUTTON_ID));

    }
/**
 * 如下case doro不适用
 5.背景显示（默认或自定义设置）
 */
}
