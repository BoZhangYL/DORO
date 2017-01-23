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
        try {
            Date day = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm/EEE, DD MMM", Locale.ENGLISH);
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
        try {
            getObjectById("com.android.systemui:id/multi_user_switch").clickAndWaitForNewWindow();

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void checkSettingButton(){

    }














}
