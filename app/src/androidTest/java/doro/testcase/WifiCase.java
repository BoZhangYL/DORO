package doro.testcase;

import junit.framework.Assert;

import org.junit.Test;

import ckt.base.VP4;
import doro.action.WifiAction;

import static doro.page.WifiPage.SETTINGS;
import static doro.page.WifiPage.WIFI;
import static doro.page.WifiPage.WIFI_ONANDOFFID;

/**
 * Created by admin on 2017/1/1.
 */

public class WifiCase extends VP4 {
    @Test
    public void OpenWifi() {//从菜单进入wifi
        openAppliction(SETTINGS);
        clickByText(WIFI);
        Assert.assertTrue("未能成功从菜单进入wifi界面", getObjectById(WIFI_ONANDOFFID).exists());
    }
    @Test
    public void ConnectWifi(){//连接一个wifi
        WifiAction WifiAction=new WifiAction();
        OpenWifi();
        WifiAction.turnOnWifi(true);
        WifiAction.connectWifi("CKT","ck88888!");
    }
}
