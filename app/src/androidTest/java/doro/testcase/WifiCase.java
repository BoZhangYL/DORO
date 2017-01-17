package doro.testcase;

import junit.framework.Assert;

import org.junit.Test;

import ckt.base.VP4;
import doro.action.WifiAction;

import static doro.page.WifiPage.WIFI_ONANDOFFID;

/**
 * Created by admin on 2017/1/1.
 */

public class WifiCase extends VP4 {
    @Test
    public void OpenWifi() {//从菜单进入wifi
        openAppliction("Settings");
        clickByText("Wi‑Fi");
        Assert.assertTrue("未能成功从菜单进入wifi界面", getObjectById(WIFI_ONANDOFFID).exists());
    }
    @Test
    public void ConnectWifi(){//连接一个wifi
        WifiAction WifiAction=new WifiAction();
        openAppliction("Settings");
        clickByText("Wi‑Fi");
        if(!getObjectByText("On").exists()){//判断是否需要开启wifi
            clickById(WIFI_ONANDOFFID);
        }
        try{
        if(getObjectByText("CKT").click()){//判断所需连接wifi是否存在
            if(getObjectByText("Connected").exists()){
                clickByText("CKT");
                clickByText("FORGET");
            }
            clickByText("CKT");
            WifiAction.getPassword("ck88888!");//输入密码
            clickByText("CONNECT");//连接
        }
            waitTime(10);
          Assert.assertTrue("未能成功连接wifi",getObjectByText("Connected").exists());//判断连接是否成功
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
