package doro.action;

import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;

import org.hamcrest.Asst;

import java.io.IOException;
import java.util.Random;

import ckt.base.VP4;
import doro.page.APPMenuPage;
import doro.page.InternetPage;

/**
 * Created by Caibing.yin on 2017/1/12.
 */

public class APPMenuAction extends VP4 {


    /*
   * 处理异常
   * */
    public static void watchexception() {
        gDevice.registerWatcher("Alarm", new UiWatcher() {
            @Override
            public boolean checkForCondition() throws UiObjectNotFoundException {
                if (getObjectByText("SNOOZE").exists()) {
                    getObjectByText("SNOOZE").clickAndWaitForNewWindow();
                    return true;
                }
                if (getObjectByText("DISMISS").exists()) {
                    getObjectByText("DISMISS").clickAndWaitForNewWindow();
                    return true;
                }
                return false;
            }
        });
    }

    //进入菜单界面
    public static void navToMenu() {
        gDevice.pressHome();
        clickById(APPMenuPage.More);
        clickByText("My applications");
    }

    //从应用名列表中随机产生一个应用程序名
    public static String getRandomName() { //length表示生成字符串的长度
        Random Rand = new Random();
        int length = APPMenuPage.AppNameList.length;
        int Num = Rand.nextInt(length - 1);
        String RandName = APPMenuPage.AppNameList[Num];
        return RandName;
    }

    //根据应用程序的序号得到包名
    public static String getPkgName(int index) {
        if (index < APPMenuPage.AppNameList.length) {
            return APPMenuPage.PkgNameList[index];
        } else {
            return "Error Name,Please input App Name Again!";
        }
    }

    //初始化应用权限
    public static void initAppPermissions() {
        try {
            openAppliction("Settings");
            while (!getObjectByText("Apps").exists()) {
                scrollByVerticalForward(25);
            }
            getObjectByText("Apps").clickAndWaitForNewWindow();
            int j = 0;
            while (!getObjectByText("DORO").exists() && j< 25) {
                gDevice.swipe(gDevice.getDisplayWidth() / 2, gDevice.getDisplayHeight() * 3 / 4,
                        gDevice.getDisplayWidth() / 2, gDevice.getDisplayHeight() / 2, 20);
                j++;
            }
            getObjectByText("DORO").clickAndWaitForNewWindow();
            getObjectByText("Permissions").clickAndWaitForNewWindow();
            UiObject PermissionSwitch = getObjectById("android:id/switch_widget");
            UiCollection PermissionsList = new UiCollection(new UiSelector().resourceId("android:id/list"));
            for (int i = 0; i < PermissionsList.getChildCount(); i++) {
                if (!gDevice.findObject(new UiSelector().resourceId("android:id/switch_widget").
                        instance(i)).isChecked()) {
                    gDevice.findObject(new UiSelector().resourceId("android:id/switch_widget").
                            instance(i)).clickAndWaitForNewWindow();
                }
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        gDevice.registerWatcher("ALLOW", new UiWatcher() {
            @Override
            public boolean checkForCondition() throws UiObjectNotFoundException {
                if(getObjectByText("ALLOW").exists()){
                    getObjectByText("ALLOW").clickAndWaitForNewWindow();
                    return true;
                }
                return false;
            }
        });
    }



}
