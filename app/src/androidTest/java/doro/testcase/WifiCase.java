package doro.testcase;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import junit.framework.Assert;

import org.junit.Test;
import org.w3c.dom.Text;

import ckt.base.VP4;
import doro.action.WifiAction;

import static android.R.attr.id;
import static android.R.id.text1;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static doro.page.CalednarPage.CALENDAR_EVENT_TITLE_ID;
import static doro.page.CalednarPage.DORO_HIDE_KEYBOARD_BUTTON;
import static doro.page.WifiPage.WIFI_ONANDOFFID;

/**
 * Created by admin on 2017/1/1.
 */

public class WifiCase extends VP4{
    WifiAction wifiAction=new WifiAction();
    @Test
        public void OpenWifi(){//从菜单进入wifi
            openAppliction("Settings");
            clickByText("Wi‑Fi");
            Assert.assertTrue("未能成功从菜单进入wifi界面",getObjectById(WIFI_ONANDOFFID).exists());
        }

    @Test
    public void ConnectWifi() {//连接一个wifi

        openAppliction("Settings");
        clickByText("Wi‑Fi");
        try{
        if(getObjectByTextContains("Off").exists()){
            clickById(WIFI_ONANDOFFID);
        }}catch (Exception e){
            e.printStackTrace();
        }
        clickByText("CKT");
        waitTime(5);
        if(getObjectByText("FORGET").exists()){//断开已连接wifi
            clickByText("FORGET");
            clickByText("CKT");//点击要连接wifi 名字
        }
        wifiAction.getPasswprd("ck88888!");//输入密码
        clickByText("CONNECT");//连接
        waitTime((5));
        Assert.assertTrue("未成功连接wifi",getObjectByText("Connected").exists());//判断是否成功连接
    }
}
