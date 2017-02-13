package doro.action;


import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import junit.framework.Assert;

import ckt.base.VP4;

import static doro.page.PlayStorePage.*;

import static doro.page.WifiPage.*;

/**
 * Created by Tao.Wang on 2017/2/8.
 */

public class PlayStoreAction extends VP4 {
    public static void connectWifi(String name,String password){
        openAppliction(SETTINGS);
        clickByText(WIFI);
        WifiAction WifiAction=new WifiAction();
        WifiAction.turnOnWifi(true);
        try{
            if(getObjectByText(name).exists()&&getObjectByText(CONNECTED_WIFI).exists()) {//判断所需连接wifi是否存在
                clickByText(name);
                clickByText(FORGET_WIFI);
            }
            UiScrollable scroll=new UiScrollable(new UiSelector().className(WIFI_LIST_CLASS));
            scroll.scrollTextIntoView(name);//在wifi列表滚到到对应的wifi
            clickByText(name);
            UiObject PasswordBox = getObjectById(INPUT_PASSWORDBOX);
            PasswordBox.click();
            waitTime(1);
            PasswordBox.setText(password);//输入密码
            clickByText(CONNECT_WIFI);//连接
            waitTime(10);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void signInGmailAccount(String name,String password){
        try {
            waitTime(10);
            UiObject Name=getObjectById(PLAYSTORE_ACCOUNT_ID);
            Name.clickAndWaitForNewWindow();
            Name.setText(name);
            clickById(PLAYSTORE_ACCOUNT_NEXTBUTTON_ID);
            UiObject Password=getObjectById(PLAYSTORE_PASSWORD_ID);
            Password.clickAndWaitForNewWindow();
            Password.setText(password);
            getObjectById(PLAYSTORE_PASSWORD_NEXTBUTTON_ID).clickAndWaitForNewWindow();
            getObjectById(PLAYSTORE_ACCEPTBUTTON_ID).clickAndWaitForNewWindow();
            waitTime(10);
            getObjectById(PLAYSTORE_NEXTBUTTON_ID).clickAndWaitForNewWindow();
            waitTime(10);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void downloadAndInstallApp(String App){
        try {
            searchApp(App);
            getObjectById(PLAYSTORE_INSTALLBUTTON_ID).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchApp(String Name){
        try {
            getObjectById(PLAYSTORE_SEARCHBOX_ID).clickAndWaitForNewWindow();
            getObjectById(PLAYSTORE_SEARCHBOX_INPUT_ID).setText(Name);
            getObjectByClassIndex(PLAYSTORE_SEARCHRESULT_CLASS,0).clickAndWaitForNewWindow();
            getObjectById(PLAYSTORE_APPLIST_ID,0).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void checkInstallApp(){
        try {
            String name=getObjectById(PLAYSTORE_APP_ID).getText();
            for (int i=1;getObjectById(PLAYSTORE_INSTALLPROGRESS_ID).exists();){
                waitTime(i);
                mDevice.wakeUp();
            }
            openAppliction(name);
            waitTime(3);
            mDevice.pressMenu();
            Assert.assertTrue("下载安装应用失败！",getObjectByText(name).exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




























}
