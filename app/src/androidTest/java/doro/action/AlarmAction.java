package doro.action;

import android.os.RemoteException;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import junit.framework.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ckt.base.VP4;
import doro.page.AlarmPage;

import static doro.page.AlarmPage.ALARM_ACTIVATE_VIBRATION;
import static doro.page.AlarmPage.ALARM_CLICK_ADD_ALARM_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_CANCEL_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_CONFIRM_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_DELETEALARM_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_IWANTTO_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_NEXT_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_OK_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_SAVE_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_SELECTALL_TEXT;
import static doro.page.AlarmPage.ALARM_COMING_SNOOZE_TEXT;
import static doro.page.AlarmPage.ALARM_COMING_STOP_TEXT;
import static doro.page.AlarmPage.ALARM_FORMAT_DECREASE_ID;
import static doro.page.AlarmPage.ALARM_FORMAT_INCREASE_ID;
import static doro.page.AlarmPage.ALARM_FREQUENCY_FIELD_ID;
import static doro.page.AlarmPage.ALARM_HEADER_ICON_ID;
import static doro.page.AlarmPage.ALARM_HOUR_DECREASE_ID;
import static doro.page.AlarmPage.ALARM_HOUR_EDIT_ID;
import static doro.page.AlarmPage.ALARM_HOUR_INCREASE_ID;
import static doro.page.AlarmPage.ALARM_ID_TEXT_ID;
import static doro.page.AlarmPage.ALARM_IMAGEVIEW_ICON_CLASS;
import static doro.page.AlarmPage.ALARM_MELODY_FIELD_ID;
import static doro.page.AlarmPage.ALARM_MINUTE_DECREASE_ID;
import static doro.page.AlarmPage.ALARM_MINUTE_EDIT_ID;
import static doro.page.AlarmPage.ALARM_MINUTE_INCREASE_ID;
import static doro.page.AlarmPage.ALARM_RELATIVELAYOUT_CLASS;
import static doro.page.AlarmPage.ALARM_TIME_FIELD_ID;
import static doro.page.AlarmPage.ALARM_WIDGET_LISTVIEW;
import static doro.page.AlarmPage.AlARM_APPS_ALARM_PACKAGE;

/**
 * Created by Lingjiang.Du on 2016/12/07.
 */

public class AlarmAction extends VP4 {

    public static void closeAlarm() {
        try {
            VP4.openAppliction(AlarmPage.APPS_ICON_ALARM_TEXT);
            if (getObjectByText(ALARM_CLICK_IWANTTO_TEXT).exists()) {
                getObjectByText(ALARM_CLICK_IWANTTO_TEXT).clickAndWaitForNewWindow();
                getObjectByText(ALARM_CLICK_DELETEALARM_TEXT).clickAndWaitForNewWindow();
                getObjectByText(ALARM_CLICK_SELECTALL_TEXT).clickAndWaitForNewWindow();
                getObjectByText(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
                getObjectByText(ALARM_CLICK_OK_TEXT).clickAndWaitForNewWindow();
            }
            VP4.openAppliction("Settings");
            VP4.scrollToEnd(20);
            getObjectByText("Date & time").clickAndWaitForNewWindow();
            for (int i = 0; i < 3; i++) {
                if ((gDevice.findObject(new UiSelector().resourceId("android:id/switch_widget")
                        .instance(i)).getText()).equals("OFF")) {
                    gDevice.findObject(new UiSelector().resourceId("android:id/switch_widget")
                            .instance(i)).clickAndWaitForNewWindow();
                }
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 等待闹钟到来
    * */
    public void waitAlarmComing(int SetAlarmTime){
        waitTime(SetAlarmTime - getCurrentSecond());
    }

    /*
    * 转换一个时间为毫秒
    * */
    public long changeTtoM(String yyyyMMddhhmm) {
        String str = yyyyMMddhhmm;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
        long millionSeconds = 0;//毫秒
        try {
            millionSeconds = sdf.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return millionSeconds;
    }

    /*
    * 得到当前日期
    * */
    public int getCurrentSecond() {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return second + minute * 60 + hour * 60 * 60;
    }

    public void closeAutoTime() {
        VP4.switchToMenuPage();
        try {
            getObjectByText("Set").clickAndWaitForNewWindow();
            for (int j = 0; j < 5; j++) {
                if (getObjectByText("A general option").exists())
                    break;
                else
                    VP4.scrollByVerticalForward(30);
            }
            getObjectByText("A general option").clickAndWaitForNewWindow();
            for (int i = 0; i < 5; i++) {
                if (getObjectByText("The date and time").exists()) {
                    break;
                } else
                    VP4.scrollByVerticalForward(30);
            }
            getObjectByText("The date and time").clickAndWaitForNewWindow();
            getObjectById("com.doro.settings:id/checkview").clickAndWaitForNewWindow();

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void specialTime(String time) {//得到指定的时间
        String[] hourMinTime = time.split(":");
        try {
            getObjectById(ALARM_TIME_FIELD_ID).clickAndWaitForNewWindow();
            String hour = getObjectById(ALARM_HOUR_EDIT_ID).getText();
            int h = Integer.valueOf(hour);
            String min = getObjectById(ALARM_MINUTE_EDIT_ID).getText();
            int m = Integer.valueOf(min);
            int countH = h - Integer.valueOf(hourMinTime[0]);
            int countM = m - Integer.valueOf(hourMinTime[1]);
            if (countH < 0) {
                for (int i = 0; i < -countH; i++) {
                    getObjectById(ALARM_HOUR_INCREASE_ID).click();
                }
            } else {
                for (int j = 0; j < countH; j++) {
                    getObjectById(ALARM_HOUR_DECREASE_ID).click();
                }
            }
            if (countM < 0) {
                for (int i = 0; i < -countM; i++) {
                    getObjectById(ALARM_MINUTE_INCREASE_ID).click();
                }
            } else {
                for (int j = 0; j < countM; j++) {
                    getObjectById(ALARM_MINUTE_DECREASE_ID).click();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void specialTime12(String time, String timeFormat) {//得到指定的时间,12小时制
        try {
            specialTime(time);
            if (timeFormat.equals("AM")) {
                if (getObjectByTextContains("PM").exists()) {
                    getObjectById(ALARM_FORMAT_INCREASE_ID).click();
                } else {
                    Thread.sleep(100);
                }
            }
            if (timeFormat.equals("PM")) {
                if (getObjectByTextContains("AM").exists()) {
                    getObjectById(ALARM_FORMAT_DECREASE_ID).click();
                } else {
                    Thread.sleep(100);
                }
            }
            getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void specialTime24(String time) {//得到指定的时间，24小时制
        try {
            specialTime(time);
            getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void chooseWeek(String week) { //选择周几
        try {
            getObjectByIdText(ALARM_ID_TEXT_ID, week).click();
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void repeatAlarmSetting(String monday, String tuesday, String wednesday,
                                   String thursday, String friday, String saturday, String sunday) {
        //选择手机在周几响铃。
        try {
            getObjectById(ALARM_FREQUENCY_FIELD_ID).clickAndWaitForNewWindow();
            if (sunday != null) {

                chooseWeek(sunday);
            }
            if (monday != null) {
                chooseWeek(monday);
            }
            if (tuesday != null) {
                chooseWeek(tuesday);
            }
            if (wednesday != null) {
                chooseWeek(wednesday);
            }
            scrollToEnd(10);
            if (thursday != null) {
                chooseWeek(thursday);
            }
            if (friday != null) {
                chooseWeek(friday);
            }
            if (saturday != null) {
                chooseWeek(saturday);
            }

            getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRingtone(String tone, boolean vibrate) { //更改闹钟铃声以及选择是否震动
        try {
            getObjectById(ALARM_MELODY_FIELD_ID).clickAndWaitForNewWindow();
            scrollToBegin(15);
            while (!getObjectByIdText(ALARM_ID_TEXT_ID, tone).exists()) {
                scrollByVerticalForward(15);
            }
            getObjectByIdText(ALARM_ID_TEXT_ID, tone).click();
            Thread.sleep(10000);
            getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
            if (vibrate) {
                if (mDevice.findObject(new UiSelector().checkable(true)).exists()) {
                    Thread.sleep(500);
                } else {
                    getObjectById(ALARM_ACTIVATE_VIBRATION).click();
                }
            } else {
                if (mDevice.findObject(new UiSelector().checkable(true)).exists()) {
                    getObjectById(ALARM_ACTIVATE_VIBRATION).click();
                } else {
                    Thread.sleep(500);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alarmComingSnooze() { //选择将闹钟 睡眠
        try {
            gDevice.openNotification();
            getUiObjectByText(ALARM_COMING_SNOOZE_TEXT).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alarmComingStop() { //选择将闹钟 停止
        try {
            gDevice.openNotification();
            getUiObjectByText(ALARM_COMING_STOP_TEXT).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkAlarmComing() { //判断闹钟是否到来
        try {
            gDevice.openNotification();
            Thread.sleep(2000);
            if(getObjectByText("Discover the new My Doro Manager").exists()){
                getObjectByText("Discover the new My Doro Manager").swipeRight(10);
            }
            Assert.assertTrue("The alarm don't come", getObjectByClassPackage(ALARM_IMAGEVIEW_ICON_CLASS, AlARM_APPS_ALARM_PACKAGE).exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitFiveMinuteforAlarmComing() {
        try {
            waitTime(240);
            int i = 0;
            while (!gDevice.isScreenOn() && i++ < 20) {
                waitTime(10);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void checkAlarmNotComing() { //判断闹钟没有到来
        try {
            VP4.unLock();
            gDevice.openNotification();
            Thread.sleep(2000);
            if(getObjectByText("Discover the new My Doro Manager").exists()){
                getObjectByText("Discover the new My Doro Manager").swipeRight(10);
            }
            Assert.assertFalse("The alarm shouldn't coming",
                    getObjectByClassPackage(ALARM_IMAGEVIEW_ICON_CLASS, AlARM_APPS_ALARM_PACKAGE).exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 关于闹钟的一些常见操作方法
    * */
    public void enterAndExitAlarm(int counts) { //进入与退出闹钟界面，反复执行 counts 次。
        try {
            for (int i = 0; i < counts; i++) {
                pressKey("back");
                Assert.assertFalse("Don't exit the alarm view", getObjectById(ALARM_HEADER_ICON_ID).exists());
                getObjectByTextContains("Alarm").clickAndWaitForNewWindow();
                Assert.assertTrue("Don't into the alarm view", getObjectById(ALARM_HEADER_ICON_ID).exists());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitAlarm() { //退出闹钟界面
        enterAndExitAlarm(1);
        try {
            mDevice.pressHome();
            Assert.assertFalse("Don't exit the alarm view", getObjectById(ALARM_HEADER_ICON_ID).exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTimeAlarm12(String time) {//建立一个指定时间的12小时制的闹钟
        try {
            getObjectByTextContains(ALARM_CLICK_ADD_ALARM_TEXT).clickAndWaitForNewWindow();
            specialTime12(time, "AM");
            getObjectByTextContains(ALARM_CLICK_NEXT_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_SAVE_TEXT).clickAndWaitForNewWindow();
            Thread.sleep(6000);
            gDevice.pressBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTimeAlarm24(String time) { //建立一个指定时间的24小时制的闹钟
        try {
            getObjectByTextContains(ALARM_CLICK_ADD_ALARM_TEXT).clickAndWaitForNewWindow();
            specialTime24(time);
            getObjectByTextContains(ALARM_CLICK_NEXT_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_SAVE_TEXT).clickAndWaitForNewWindow();
            Thread.sleep(6000);
            gDevice.pressBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int addOneAlarm() { //建立一个2分钟后的闹钟
        int seconds=0;
        try {
            getObjectByTextContains(ALARM_CLICK_ADD_ALARM_TEXT).clickAndWaitForNewWindow();
            getObjectById(ALARM_TIME_FIELD_ID).clickAndWaitForNewWindow();
            getObjectById(ALARM_MINUTE_INCREASE_ID).click();
            getObjectById(ALARM_MINUTE_INCREASE_ID).click();
            String hours =getObjectById("com.doro.apps.alarm:id/hour_edit").getText();
            String Minutes =getObjectById("com.doro.apps.alarm:id/minute_edit").getText();
            seconds =(Integer.valueOf(hours)*60*60)+(Integer.valueOf(Minutes)*60);
            getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();

            getObjectByTextContains(ALARM_CLICK_NEXT_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_SAVE_TEXT).clickAndWaitForNewWindow();
            Thread.sleep(6000);
            gDevice.pressBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seconds;
    }

    public void addRepeatAlarm(String monday, String tuesday, String wednesday,
                               String thursday, String friday, String saturday, String sunday) {
        // 增加一个指定周几响闹的闹钟
        try {
            getObjectByTextContains(ALARM_CLICK_ADD_ALARM_TEXT).clickAndWaitForNewWindow();
            repeatAlarmSetting(monday, tuesday, wednesday, thursday, friday, saturday, sunday);
            getObjectByTextContains(ALARM_CLICK_NEXT_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_SAVE_TEXT).clickAndWaitForNewWindow();
            Thread.sleep(6000);
            gDevice.pressBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTimeWeekAlarm(String time, String monday, String tuesday, String wednesday,
                                 String thursday, String friday, String saturday, String sunday) {
        //增加一个指定时间与星期的闹钟
        try {
            getObjectByTextContains(ALARM_CLICK_ADD_ALARM_TEXT).clickAndWaitForNewWindow();
            specialTime24(time);
            repeatAlarmSetting(monday, tuesday, wednesday, thursday, friday, saturday, sunday);
            getObjectByTextContains(ALARM_CLICK_NEXT_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_SAVE_TEXT).clickAndWaitForNewWindow();
            Thread.sleep(6000);
            gDevice.pressBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllAlarm() { //删除所有闹钟操作
        try {
            if (!getObjectByTextContains(ALARM_CLICK_IWANTTO_TEXT).exists()) {
                addTimeAlarm24("20:18");
            }
            openAppliction("Alarm");
            getObjectByTextContains(ALARM_CLICK_IWANTTO_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_DELETEALARM_TEXT).click();
            getObjectByTextContains(ALARM_CLICK_SELECTALL_TEXT).click();
            getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_CANCEL_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_OK_TEXT).clickAndWaitForNewWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOneAlarm() { //删除第一闹钟操作
        deleteAllAlarm();
        try {
            if (!getObjectByTextContains(ALARM_CLICK_IWANTTO_TEXT).exists()) {
                addTimeAlarm24("20:18");
            }
            openAppliction("Alarm");
            int count1 = getObjectByClass(ALARM_WIDGET_LISTVIEW).getChildCount();
            getObjectByTextContains(ALARM_CLICK_IWANTTO_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_DELETEALARM_TEXT).click();
            mDevice.findObject(new UiSelector().className(ALARM_RELATIVELAYOUT_CLASS).index(1)).click();
            getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_CANCEL_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_OK_TEXT).clickAndWaitForNewWindow();
            if (count1 <= 2) {
                checkHasAlarm();
            } else {
                int count2 = getObjectByClass(ALARM_WIDGET_LISTVIEW).getChildCount();
                Assert.assertEquals("Don't delete one Alarm successfully", 1, count1 - count2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkHasAlarm() { //判断闹钟已经到来
        try {
            Assert.assertFalse("Don't delete all Alarms", getObjectByTextContains(ALARM_CLICK_IWANTTO_TEXT).exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

