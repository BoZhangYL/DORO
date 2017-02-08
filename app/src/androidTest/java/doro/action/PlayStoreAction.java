package doro.action;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;

import ckt.base.VP4;

import static doro.page.WifiPage.*;

/**
 * Created by Tao.Wang on 2017/2/8.
 */

public class PlayStoreAction extends VP4 {
    public void connectWifi(String name,String password){
        WifiAction WifiAction=new WifiAction();
        openAppliction(SETTINGS);
        clickByText(WIFI);
        WifiAction.turnOnWifi(true);
        WifiAction.connectWifi(name,password);
    }

    public void signInGmailAccount(String name,String password){
        try {
            openAppliction("Play Store");
            UiObject Name=getObjectById("identifierId");
            Name.clickAndWaitForNewWindow();
            Name.setText(name);
            clickById("identifierNext");
            UiObject Password=getObjectById("password");
            Password.clickAndWaitForNewWindow();
            Password.setText(password);
            clickById("passwordNext");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }



    }
























}
