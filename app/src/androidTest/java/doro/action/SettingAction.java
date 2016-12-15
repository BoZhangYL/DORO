package doro.action;

import android.support.test.uiautomator.UiSelector;

import ckt.base.VP4;
import doro.page.SettingPage;

import static doro.page.SettingPage.SETTINGS_AUTOMATIC_DATETIME_TEXT;
import static doro.page.SettingPage.SETTINGS_DATE_HEADER_DATE_ID;
import static doro.page.SettingPage.SETTINGS_DATE_HEADER_YEAR_ID;
import static doro.page.SettingPage.SETTINGS_DATE_MONTH_NEXT_ID;
import static doro.page.SettingPage.SETTINGS_DATE_MONTH_PREV_ID;
import static doro.page.SettingPage.SETTINGS_DATE_TIME_TEXT;
import static doro.page.SettingPage.SETTINGS_DATE_YEAR_TEXT1_ID;
import static doro.page.SettingPage.SETTINGS_GPS_PROVIDED_TEXT;
import static doro.page.SettingPage.SETTINGS_NETWORK_PROVIDED_TIME_TEXT;
import static doro.page.SettingPage.SETTINGS_OFF_TEXT;
import static doro.page.SettingPage.SETTINGS_SET_DATE_OK_TEXT;
import static doro.page.SettingPage.SETTINGS_SET_DATE_TEXT;
import static doro.page.SettingPage.SETTINGS_TIME_HOURS_ID;
import static doro.page.SettingPage.SETTINGS_TIME_MINUTES_ID;
import static doro.page.SettingPage.SETTING_DATE_DAY_VIEW_CLASS;
import static doro.page.SettingPage.SETTING_HOUR_TOUCHHELPER_CLASS;
import static doro.page.SettingPage.SETTING_SET_TIME_TEXT;
import static java.lang.Integer.parseInt;

/**
 * Created by user on 2016/12/1.
 */
public class SettingAction extends VP4 {
    public static void openBluetooth() {
        clickById(SettingPage.BLUETOOTH_SWITCH);
    }

    public static void closeBluetooth() {
    }

    public void dateAndTime() { //进入手机设置中的 Date&Time
        try {
            while (!getObjectByTextContains(SETTINGS_DATE_TIME_TEXT).exists()) {
                scrollByVerticalForward(15);
            }
            getObjectByTextContains(SETTINGS_DATE_TIME_TEXT).clickAndWaitForNewWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void whatProvidedTime(int style) {
        //选择使用什么方式提供时间：0：个人设置；1：网络提供时间；2：GPS提供时间。
        try {
            switch (style) {
                case 1:
                    if (getUiObjectByText(SETTINGS_NETWORK_PROVIDED_TIME_TEXT).exists()) {
                        Thread.sleep(500);
                        break;
                    } else {
                        getObjectByTextContains(SETTINGS_AUTOMATIC_DATETIME_TEXT).clickAndWaitForNewWindow();
                        getObjectByTextContains(SETTINGS_NETWORK_PROVIDED_TIME_TEXT).click();
                        break;
                    }
                case 2:
                    if (getObjectByTextContains(SETTINGS_GPS_PROVIDED_TEXT).exists()) {
                        Thread.sleep(500);
                        break;
                    } else {
                        getObjectByTextContains(SETTINGS_AUTOMATIC_DATETIME_TEXT).clickAndWaitForNewWindow();
                        getObjectByTextContains(SETTINGS_GPS_PROVIDED_TEXT).click();
                        break;
                    }
                default:
                    if (getObjectByTextContains(SETTINGS_OFF_TEXT).exists()) {
                        Thread.sleep(500);
                        break;
                    } else {
                        getObjectByTextContains(SETTINGS_AUTOMATIC_DATETIME_TEXT).clickAndWaitForNewWindow();
                        getObjectByTextContains(SETTINGS_OFF_TEXT).click();
                        break;
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDate(String date){ //年-月-日 格式：YYYY-MM-DD
        try{
            String[] timeDate =date.split("-");
            int months = parseInt(timeDate[1]);
            getUiObjectByText(SETTINGS_SET_DATE_TEXT).clickAndWaitForNewWindow();
            String year =getObjectById(SETTINGS_DATE_HEADER_YEAR_ID).getText();
            if(!year.equals(timeDate[0])) {
                getObjectById(SETTINGS_DATE_HEADER_YEAR_ID).click();
                scrollToBegin(15);
                while (!getObjectByIdText(SETTINGS_DATE_YEAR_TEXT1_ID, timeDate[0]).exists()) {
                    scrollByVerticalForward(30);
                }
                getObjectByIdText(SETTINGS_DATE_YEAR_TEXT1_ID, timeDate[0]).click();
            }else {
                String[] month = getObjectById(SETTINGS_DATE_HEADER_DATE_ID).getText().split(" ");
                while (!month[2].equals("Jan")) {
                    getObjectById(SETTINGS_DATE_MONTH_PREV_ID).click();
                    getUiObjectByClassText(SETTING_DATE_DAY_VIEW_CLASS, "1").click();
                    String[] month1 = getObjectById(SETTINGS_DATE_HEADER_DATE_ID).getText().split(" ");
                    month[2] = month1[2];
                }
                for (int i = 1; i < months; i++) {
                    getObjectById(SETTINGS_DATE_MONTH_NEXT_ID).click();
                    Thread.sleep(100);
                }
                int days =Integer.parseInt(timeDate[2]);
                String sDays = String.valueOf(days);
                getUiObjectByClassText(SETTING_DATE_DAY_VIEW_CLASS,sDays).click();
                Thread.sleep(1000);
                getUiObjectByText(SETTINGS_SET_DATE_OK_TEXT).clickAndWaitForNewWindow();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void setTime(String time){ //时间格式：hh:mm
        try{
            getUiObjectByText(SETTING_SET_TIME_TEXT).clickAndWaitForNewWindow();
            String[] times =time.split(":");
            int hour = Integer.valueOf(times[0]);
            String sHour = String.valueOf(hour);
            getObjectById(SETTINGS_TIME_HOURS_ID).click();
            Thread.sleep(1000);
            gDevice.findObject(new UiSelector().className(SETTING_HOUR_TOUCHHELPER_CLASS).description(sHour)).click();
            Thread.sleep(1000);
            getObjectById(SETTINGS_TIME_MINUTES_ID).click();
            Thread.sleep(1000);
           // gDevice.findObject(new UiSelector().description("25")).click();
            Thread.sleep(1000);
            getUiObjectByText(SETTINGS_SET_DATE_OK_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
