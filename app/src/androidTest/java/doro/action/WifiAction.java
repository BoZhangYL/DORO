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
        //String ConnectState = null;
        //String WiFiSignal = null;
        String nextWifiName = null;
        try {
            while (!(WIFIList.getChildByInstance(new UiSelector().resourceId("android:id/title"),
                    (WIFIList.getChildCount() - 2)).getText().equals("Add network"))) {
                for (int i = 0; i < WIFIList.getChildCount(); i++) {
                    UiObject wifiname = WIFIList.getChildByInstance(new UiSelector().resourceId("android:id/title"), i);
                    UiObject wifidescrption = WIFIList.getChildByInstance(new UiSelector().resourceId("android:id/summary"), i);
                    // String[] wifiDescrption = wifidescrption.getContentDescription().split(",");
                    //WifiName = wifiDescrption[0];
                    //ConnectState = wifiDescrption[1];
                    //WiFiSignal = wifiDescrption[2];
                    if (wifiname.getText().equals(name)) {
                        getObjectByText(name).clickAndWaitForNewWindow();
                        if (getObjectById("com.android.settings:id/show_password").exists()) {
                            WifiAction.getPassword(password);//输入密码
                            clickByText(CONNECT_WIFI);//连接
                            waitTime(10);
                            break;
                        } else {
                            getObjectByText("FORGET").clickAndWaitForNewWindow();
                            getObjectByText(name).clickAndWaitForNewWindow();
                            WifiAction.getPassword(password);//输入密码
                            clickByText(CONNECT_WIFI);//连接
                            waitTime(10);
                            break;
                        }
                    }
                }
                if (getObjectByText(CONNECTED_WIFI).exists()) {
                    break;
                } else
                    scrollForward(20);
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        scrollToBegin(10);
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
