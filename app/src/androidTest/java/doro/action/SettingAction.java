package doro.action;

import ckt.base.VP4;
import doro.page.SettingPage;

import static doro.page.SettingPage.SETTINGS_AUTOMATIC_DATETIME_TEXT;
import static doro.page.SettingPage.SETTINGS_DATE_TIME_TEXT;
import static doro.page.SettingPage.SETTINGS_GPS_PROVIDED_TEXT;
import static doro.page.SettingPage.SETTINGS_NETWORK_PROVIDED_TIME_TEXT;
import static doro.page.SettingPage.SETTINGS_OFF_TEXT;

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

    public void whatProvidedTime(int style) { //选择使用什么方式提供时间：0：个人设置；1：网络提供时间；2：GPS提供时间。
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
}
