package doro.action;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;

import java.io.IOException;

import ckt.base.VP4;
import doro.page.ViewPage;

/**
 * Created by qiang.zhang on 2017/1/13.
 */
public class MainAction extends VP4 {
    /*clear all apps*/
    public static void clearAllApp() throws UiObjectNotFoundException {
        String homeScreen = "com.doro.apps.launcher3:id/shortcut_list";
        while(!getObjectById(homeScreen).exists()) {
            try {
                pressKey("Home/menu");
                if (getObjectByText("No recent items").exists()) {
                    try {
                        pressKey("Menu");
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (getObjectByText("Clear all").exists())
                    getObjectByText("Clear all").clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void startApp(String AppName) throws UiObjectNotFoundException {
        gDevice.pressHome();
        waitUntilFind(ViewPage.MORE_ACTIONS_ID, 5000);
        clickById(ViewPage.MORE_ACTIONS_ID);
        //推荐的app list中是否存在
        if (text_exists(AppName)) {
            clickByText(AppName);
        } else {
            //滑动查找App
            waitUntilFind(ViewPage.APP_LIST_SEARCH, 10000);
            clickById(ViewPage.APP_LIST_SEARCH);
            getObject2ById(ViewPage.SEARCH_BOX_INPUT).clear();
            waitTime(2);
            getObject2ById(ViewPage.SEARCH_BOX_INPUT).setText(AppName.substring(0, 3));
            waitTime(3);
            //gDevice.pressBack();
            waitUntilFind(LAUNCH3_APP, 30000);
            while(getObjectByIdText(LAUNCH3_APP,AppName).exists())
            clickByIdText(LAUNCH3_APP, AppName);
            logger.info("current-package is - " + gDevice.getCurrentPackageName());
        }
        if (getObjectByText("ALLOW").exists()) {
            while (getObjectByText("ALLOW").exists()) {
                getObjectByText("ALLOW").clickAndWaitForNewWindow();
            }
        }
        waitTime(5);
    }

    /**
     * 启动FILE MANAGER
     */
    public static void startFileManager() throws Exception {
        gDevice.pressHome();
        gDevice.pressHome();
        waitTime(2);
        gDevice.executeShellCommand("am start -n com.mediatek.filemanager/.FileManagerOperationActivity");
        gDevice.wait(Until.findObject(By.pkg("com.mediatek.filemanager")), 40000);
        String pkg = gDevice.getCurrentPackageName();
        logger.info("current-package:" + pkg);
    }

    /**
     * 关闭文件管理器
     */
    public static void stopFileManager() {
        try {
            gDevice.executeShellCommand(
                    "am force-stop com.mediatek.filemanager");
            waitTime(2);
            String name = gDevice.getCurrentPackageName();
            logger.info("current package:" + name);
            logger.info("stop filemanager-Success");
        } catch (IOException e) {
            logger.info("stop filemanager-Fail");
        }
    }

    /**
     * 启动FILE MANAGER
     */
    public static void startMessage() throws Exception {
        gDevice.pressHome();
        gDevice.pressHome();
        waitTime(2);
        gDevice.executeShellCommand("am start -n com.doro.apps.messages/.ui.conversationlist.ConversationListActivity");
        gDevice.wait(Until.findObject(By.pkg("com.doro.apps.messages")), 40000);
        String pkg = gDevice.getCurrentPackageName();
        logger.info("current-package:" + pkg);
    }

    /**
     * 关闭文件管理器
     */
    public static void stopMessage() {
        initDevice();
        try {
            gDevice.executeShellCommand(
                    "am force-stop com.doro.apps.messages");
            waitTime(2);
            String name = gDevice.getCurrentPackageName();
            logger.info("current package:" + name);
            logger.info("stop SMS-Success");
        } catch (IOException e) {
            logger.info("stop SMS-Fail");
        }
    }
}
