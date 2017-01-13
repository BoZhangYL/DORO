package doro.action;

import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiObjectNotFoundException;

import ckt.base.VP4;
import doro.page.ContactsPage;
import doro.page.ViewPage;

/**
 * Created by qiang.zhang on 2017/1/13.
 */
public class MainAction extends VP4{
    /*clear all apps*/
    public static void clearAllApp() throws UiObjectNotFoundException {
        initDevice();
        unLock();
        gDevice.pressMenu();
        if (text_exists(ViewPage.NO_RECENT_ITEMS)){
            gDevice.pressHome();
        }else {
            waitUntilFind(ViewPage.CLEAR_APP_ID,5000);
            while (id_exists(ViewPage.CLEAR_APP_ID)) {
                clickById(ViewPage.CLEAR_APP_ID);
                waitTime(1);
            }
        }
    }
    public static void startApp(String AppName) throws UiObjectNotFoundException {
        gDevice.pressHome();
        waitUntilFind(ViewPage.MORE_ACTIONS_ID,5000);
        clickById(ViewPage.MORE_ACTIONS_ID);
        //推荐的app list中是否存在
        if (text_exists(AppName)){
            clickByText(AppName);
        }else{
            //滑动查找App
            waitUntilFind(ViewPage.APP_LIST_SEARCH,10000);
            clickById(ViewPage.APP_LIST_SEARCH);
            if (id_exists(ContactsPage.RecyclerView)){
                getObject2ById(ContactsPage.RecyclerView).swipe(Direction.UP,(float)0.5);
            }
            ScrollViewByText(AppName);
            getObjectByIdText(LAUNCH3_APP, AppName).clickAndWaitForNewWindow();
            logger.info("current-package is - "+gDevice.getCurrentPackageName());
        }
    }
}
