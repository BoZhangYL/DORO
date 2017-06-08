package doro.action;

import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import junit.framework.Assert;

import org.hamcrest.Asst;

import ckt.base.VP4;

import static doro.page.WifiPage.CONNECTED_WIFI;
import static doro.page.WifiPage.CONNECT_WIFI;
import static doro.page.WifiPage.FORGET_WIFI;
import static doro.page.WifiPage.INPUT_PASSWORDBOX;
import static doro.page.WifiPage.TURN_ON_WIFI;
import static doro.page.WifiPage.WIFI_ONANDOFFID;

/**
 * Created by admin on 2017/1/10.
 */

public class WifiAction extends VP4 {

    public void turnOnWifi(boolean yesOrNo) {
        if (yesOrNo) {
            if (!getObjectByText(TURN_ON_WIFI).exists()) {//判断是否需要开启wifi
                clickById(WIFI_ONANDOFFID);
            }
        } else {
            if (getObjectByText(TURN_ON_WIFI).exists()) {
                clickById(WIFI_ONANDOFFID);
            }
        }
    }

    public void connectWifi(String name, String password) {

        UiCollection WIFIList = new UiCollection(new UiSelector()
                .resourceId("com.android.settings:id/list"));
        try {
            while (WIFIList.getChildCount() < 3) {
                waitTime(1);
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        String WifiName = null;
        String ConnectState = null;
        //String WiFiSignal = null;
        String nextWifiName = null;

        try {
            for (int i = 0; i < WIFIList.getChildCount(); i++) {
                UiObject CurrentWifi = WIFIList.getChild(new UiSelector().index(i));
                String[] wifiDescrption = CurrentWifi.getContentDescription().split(",");
                WifiName = wifiDescrption[0];
                //ConnectState = wifiDescrption[1];
                //WiFiSignal = wifiDescrption[2];
                if (WifiName.equals(name)) {
                    ConnectState = wifiDescrption[1];
                    if (!ConnectState.equals("Connected")) {
                        clickByText(name);
                        WifiAction.getPassword(password);//输入密码
                        clickByText(CONNECT_WIFI);//连接
                        waitTime(10);
                        Assert.assertTrue("未能成功连接wifi", getObjectByText(CONNECTED_WIFI).exists());//判断连接是否成功
                        break;
                    } else break;
                } else if (!getObjectByText("Add network").exists()) {
                    scrollForward(20);
                    connectWifi(name, password);
                }

            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertTrue("未能成功连接wifi", getObjectByText(CONNECTED_WIFI).exists());//判断连接是否成功
    }

    private static void doroInput(UiObject object, String text) {//输入键盘的操作
        try {
            object.click();
            waitTime(1);
            object.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getPassword(String Passwords) {//输入密码
        UiObject PasswordBox = getObjectById(INPUT_PASSWORDBOX);
        doroInput(PasswordBox, Passwords);
    }
}
