package doro.action;

import android.graphics.Rect;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ckt.base.VP4;

import static doro.page.NotificationBarPage.*;

/**
 * Created by Tao.Wang on 2017/1/19.
 */

public class NotificationBarAction extends VP4 {
    public void pullDownNotificationBar(){
        //下拉通知栏
        try {
            if (!getObjectByClass(NOTIFICATIONBAR_BRIGHTNESS_CLASS).exists()) {
                Rect Z = getObjectByClass(NOTIFICATIONBAR_SCREEN_CLASS).getBounds();
                int centerX = Z.centerX();
                int centerY = Z.centerY();
                gDevice.swipe(centerX, 0, centerX, centerY, 10);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void checkTimeAndDate(){
        //获取当前系统时间后与状态栏显示时间比较
        try {
            Date day = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm/EEE, d MMM", Locale.ENGLISH);
            String timedate = sdf.format(day);
            String[] TimeDate = timedate.split("/");
            String Time = getObjectById(NOTIFICATIONBAR_TIME_ID).getText();
            String Date = getObjectById(NOTIFICATIONBAR_DATE_ID).getText();
            Assert.assertTrue("显示当前时间错误！",Time.equals(TimeDate[0])&&Date.equals(TimeDate[1]));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void checkMultiUserButton(){
        //点击User按钮后检查是否进入User菜单
        try {
            getObjectById(NOTIFICATIONBAR_USERSWITCH_ID).clickAndWaitForNewWindow();
            Assert.assertTrue("User_Button没有显示",getObjectById(NOTIFICATIONBAR_TITLE_ID).getText().equals(NOTIFICATIONBAR_USEPAGE_TEXT));
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void checkSettingButton(){
        //点击Settings按钮后检查是否进入Settings菜单
        try {
            getObjectById(NOTIFICATIONBAR_SETTINGSBUTTON_ID).clickAndWaitForNewWindow();
            Assert.assertTrue("Settings_Button没有显示！",text_exists(NOTIFICATIONBAR_SETTINGSPAGE_TEXT));
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }


    }














}
