package doro.action;


import android.hardware.camera2.CameraCharacteristics;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.view.KeyEvent;

import junit.framework.Assert;

import org.hamcrest.Asst;

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
        scrollToBegin(10);
        WifiAction WifiAction=new WifiAction();
        WifiAction.turnOnWifi(true);
        try{
           /* if(getObjectByText(name).exists()&&getObjectByText(CONNECTED_WIFI).exists()) {//判断所需连接wifi是否存在
                clickByText(name);
                clickByText(FORGET_WIFI);
            }*/
            UiScrollable scroll=new UiScrollable(new UiSelector().className(WIFI_LIST_CLASS));
            scroll.scrollTextIntoView(name);//在wifi列表滚到到对应的wifi
            clickByText(name);
            waitTime(10);
            UiObject PasswordBox = getObjectById(INPUT_PASSWORDBOX);
            PasswordBox.click();
            PasswordBox.setText(password);//输入密码
           /* gDevice.pressKeyCode(KeyEvent.KEYCODE_T);
            gDevice.pressKeyCode(KeyEvent.KEYCODE_E);
            gDevice.pressKeyCode(KeyEvent.KEYCODE_S);
            gDevice.pressKeyCode(KeyEvent.KEYCODE_T);
            gDevice.pressKeyCode(KeyEvent.KEYCODE_7);
            gDevice.pressKeyCode(KeyEvent.KEYCODE_8);
            gDevice.pressKeyCode(KeyEvent.KEYCODE_7);
            gDevice.pressKeyCode(KeyEvent.KEYCODE_8);
            gDevice.pressEnter();*/
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
            getObjectByText("INSTALL").clickAndWaitForNewWindow();
            int i=0;
            while(!getObjectByText("OPEN").exists()&& i++<100){
                waitTime(10);
                VP4.unLock();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void searchApp(String Name){
        try {
            getObjectById(PLAYSTORE_SEARCHBOX_ID).clickAndWaitForNewWindow();
            getObjectById(PLAYSTORE_SEARCHBOX_INPUT_ID).setText(Name);
            getObjectByText("fruit ninja").clickAndWaitForNewWindow();
           // getObjectByClassIndex(PLAYSTORE_SEARCHRESULT_CLASS,0).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void checkInstallApp(){
        try {
            int i=0;
            while(getObjectByText("OPEN").exists() && i++<5)
            getObjectByText("OPEN").clickAndWaitForNewWindow();
            waitTime(10);
            Asst.assertEquals("Download error","com.halfbrick.fruitninjafree",gDevice.getCurrentPackageName());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }




























}
