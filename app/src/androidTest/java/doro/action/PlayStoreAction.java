package doro.action;

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

    public void createGmailAccout (){
        openAppliction("Play Store");
        clickById("createAccount");
    }
























}
