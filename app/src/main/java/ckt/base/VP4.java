package ckt.base;

import android.graphics.Rect;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import org.hamcrest.Asst;

import java.io.IOException;

/**
 * Created by admin on 2016/12/2.
 */

public class VP4 extends VP2 {
    protected static final int STEP_NORMAL = 20;//步长
    private static final String HOME_SHADOW_BUTTON =
            "com.doro.apps.launcher3:id/content_shadow_bottom";//Home界面向下箭头按钮
    protected static final String LAUNCH3_APP = "com.doro.apps.launcher3:id/icon";//应用列表

    public VP4() {
    }

    public static UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    public static UiDevice initDoro() {//初始化
        if (mDevice != null) {
            return mDevice;
        } else {
            mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            return mDevice;

        }
    }

    public static void unLock() {//解锁
        try {
            initDevice();
            gDevice.wakeUp();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        UiObject UnlockBTN = getUiObjectByDes("Unlock");
        while (UnlockBTN.exists()) {
            try {
                Rect z = UnlockBTN.getBounds();
                int centerX = z.centerX();
                int centerY = z.centerY();
                gDevice.swipe(centerX, centerY, centerX, 0, 10);
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearNoTifcation() {
        gDevice.openNotification();
        if(getObjectByText("Clear all").exists())
            try {
                getObjectByText("Clear all").clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        getObjectByText("Clear all");
        /*  UiCollection Notification = new UiCollection(new UiSelector().
                  resourceId("com.android.systemui:id/notification_stack_scroller"));


              int number = Notification.getChildCount(new UiSelector()
                      .resourceId("com.android.systemui:id/fake_shadow"));
              for (int i = 0; i < number - 1; i++) {
                  Notification.getChildByInstance(new UiSelector()
                          .resourceId("com.android.systemui:id/fake_shadow"),i).swipeLeft(5);
               *//*   gDevice.findObject(new UiSelector()
                          .resourceId("com.android.systemui:id/notification_stack_scroller")
                          .index(i))
                          .swipeLeft(10);*//*
              }*/
        gDevice.swipe(gDevice.getDisplayWidth() / 2, gDevice.
                getDisplayHeight(), gDevice.getDisplayWidth() / 2, 0, 10);

    }

    public static void openAppliction(String AppName) {//打开应用
        clearNoTifcation();
        String FinallyAppName = "YouTube";
        UiObject SearchBox = getObjectById("com.doro.apps.launcher3:id/search_box_input");
        UiCollection APPList = new UiCollection(new UiSelector().
                resourceId("com.doro.apps.launcher3:id/apps_list_view"));
        UiSelector Apps = new UiSelector().resourceId("com.doro.apps.launcher3:id/icon");
        int x0 = 0, y0 = 0, x1 = 0, y1 = 0;
        try {
            switchToApplistPage();
            SearchBox.setText("");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        try {
            x0 = APPList.getChildByInstance(Apps, 1).getBounds().centerX();
            y0 = APPList.getChildByInstance(Apps, 1).getBounds().bottom;
            x1 = APPList.getChildByInstance(Apps, 1).getBounds().centerX();
            y1 = APPList.getChildByInstance(Apps, 1).getBounds().top;
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        while (!getObjectByIdText(LAUNCH3_APP, AppName).exists() && !
                getObjectByText(FinallyAppName).exists()) {
            gDevice.swipe(x0, y0, x1, y1, 5);
        }
        try {
            Asst.assertTrue("在应用列表没有找到应用", getObjectByIdText(LAUNCH3_APP, AppName).exists());
            getObjectByIdText(LAUNCH3_APP, AppName).clickAndWaitForNewWindow();
            while (getObjectByText("ALLOW").exists()) {
                getObjectByText("ALLOW").clickAndWaitForNewWindow();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Home page, menu page, applist page
    public static void switchToHomePage() {//回到主界面
        initRent();
    }

    public static void switchToMenuPage() {//回到快捷应用图标界面
        switchToHomePage();
        try {
            getObjectById(HOME_SHADOW_BUTTON).click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        String shortcutScreen = "android:id/list";
        Asst.assertTrue("Switch to Menu Page fail", waitObjectTenMinuite(getObjectById(shortcutScreen)));
    }

    public static void switchToApplistPage() {//进入应用列表
        switchToMenuPage();
        try {
            getObjectById(HOME_SHADOW_BUTTON).click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        String appListScreen = "com.doro.apps.launcher3:id/apps_list_view";
        Asst.assertTrue("Switch to Applist fail", waitObjectTenMinuite(getObjectById(appListScreen)));
    }

    public void iWantTo() {//dlj

    }

    public static void scrollByVerticalBackward(int steps) {//垂直向下滑动
        initDevice();
        try {
            UiScrollable scr = new UiScrollable(new UiSelector().scrollable(true));
            scr.setAsVerticalList();
            scr.scrollBackward(steps);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void scrollByVerticalForward(int steps) {//垂直向上滑动
        initDevice();
        try {
            UiScrollable scr = new UiScrollable(new UiSelector().scrollable(true));
            if (scr.exists()) {
                scr.setAsVerticalList();
                scr.scrollForward(steps);
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void scrollBackward(int steps) {
        initDevice();
        try {
            UiScrollable scr = new UiScrollable(new UiSelector().scrollable(true));
            if (scr.exists()) {
                scr.setAsVerticalList();
                scr.scrollBackward(steps);
                logger.info("-scrollBackward success-");
            }
        } catch (UiObjectNotFoundException e) {
            logger.info("-scrollBackward Failed-");
            e.printStackTrace();
        }
    }

    public static void scrollForward(int steps) {
        initDevice();
        try {
            UiScrollable scr = new UiScrollable(new UiSelector().scrollable(true));
            if (scr.exists()) {
                scr.setAsVerticalList();
                scr.scrollForward(steps);
                logger.info("-scrollForward success-");
            }
        } catch (UiObjectNotFoundException e) {
            logger.info("-scrollForward Failed-");
            e.printStackTrace();
        }
    }

    public static void scrollToBegin(int steps) {//滑动到当前页面开始位置
        initDevice();
        try {
            UiScrollable scr = new UiScrollable(new UiSelector().scrollable(true));
            if (scr.exists()) {
                scr.setAsVerticalList();
                scr.scrollToBeginning(steps);
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void scrollToEnd(int steps) {//滑动到当前页面结束位置
        initDevice();
        try {
            UiScrollable scr = new UiScrollable(new UiSelector().scrollable(true));
            if (scr.exists()) {
                scr.setAsVerticalList();
                scr.scrollToEnd(50, steps);
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void scrollLeft(UiObject object, int steps) {//向左滑动
        initDevice();
        try {
            object.swipeLeft(steps);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void scrollRight(UiObject object, int steps) {//向右滑动
        initDevice();
        try {
            object.swipeRight(steps);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static UiObject getObjectByIdText(String ResourceID, String text) {
        //得到指定ResourceID、Text的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().resourceId(ResourceID).text(text));
    }

    public static UiObject getObjectByPackage(String Package) {
        //得到指定package的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().packageName(Package));
    }

    public static UiObject getUiObjectByClassText(String TragetClass, String TragetObject) {
        //得到指定Class、Text的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().className(TragetClass).text(TragetObject));
    }

    public static UiObject getUiObjectByClassID(String TragetClass, String ResourceID) {
        //得到指定Class、ResourceID的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().className(TragetClass).resourceId(ResourceID));
    }

    public static UiObject getObjectByDesc(String TragetObject) {
        //得到指定Content-desc的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().description(TragetObject));
    }

    public static UiObject getObjectByClass(String TragetObject) {
        //得到指定Class的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().className(TragetObject));
    }

    public static UiObject getObjectByClassDesc(String ClassObject, String DescObject) {
        //得到指定Class，Content-desc的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().className(ClassObject).description(DescObject));
    }

    public static UiObject getObjectByEnabled(boolean openClose) {
        //得到指定Enabled的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().enabled(openClose));
    }

    public static UiObject getObjectByTextEnabled(String TragetObject, boolean openClose) {
        //得到指定,Text,Enabled的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().text(TragetObject).enabled(openClose));
    }

    public static UiObject getObjectByText(String Text) {
        //得到指定Text对应object
        initDevice();
        return gDevice.findObject(new UiSelector().text(Text));
    }

    public static UiObject getObjectByClassPackage(String classObject, String packageObject) {
        //得到指定的class与package的 Object
        initDevice();
        return gDevice.findObject(new UiSelector().className(classObject).packageName(packageObject));
    }

    public static UiObject getObjectByClassIndex(String classObject, int num) {
        //得到指定的class与index的 Object
        initDevice();
        return gDevice.findObject(new UiSelector().className(classObject).index(num));
    }

    public static UiSelector getSelectorByClass(String classSelector) {
        //得到指定的class的 UiSelector
        initDevice();
        return (new UiSelector().className(classSelector));
    }

    public static UiCollection getCollectionByClass(String classCollection) {
        //得到指定的class的 UiCollection
        initDevice();
        return (new UiCollection(getSelectorByClass(classCollection)));
    }

    public static UiObject getLinearLayout(int index, String classFather, String classChild) {
        //通过指定的ListView(classFather)和LinearLayout(classChild)的class 得到UiCollection
        UiObject uiObject = null;
        initDevice();
        UiCollection uiCollection = getCollectionByClass(classFather);
        UiSelector uiSelector = getSelectorByClass(classChild);
        try {
            uiObject = uiCollection.getChildByInstance(uiSelector, index);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        return uiObject;
    }

    public static void initRent() {//清除recent
        try {
            String homeScreen = "com.doro.apps.launcher3:id/shortcut_list";
            pressKey("Home/menu");
            /*while (getObjectById("com.android.systemui:id/task_view_thumbnail").exists()) {
                scrollLeft(getObjectById("com.android.systemui:id/task_view_thumbnail"),
                        5);
            }*/
            int i = 0;
            while (!getObjectById(homeScreen).exists() && i < 2) {
                if (getObjectByText("No recent items").exists()) {
                    try {
                        pressKey("Menu");
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (getObjectByText("Clear all").exists())
                    getObjectByText("Clear all").clickAndWaitForNewWindow();
                i++;
            }

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean waitObjectTenMinuite(UiObject object) {
        int i = 1;
        while (!object.exists() && i < 5) {
            waitTime(i++);
        }
        if (i < 5)
            return true;
        else
            return false;
    }

    public void phoneWaitTime(double mins) {
        try {
            Thread.sleep((long) (mins * 60 * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void longPressKey(int keyCode) {//长按指定的按键
        try {
            gDevice.executeShellCommand("input keyevent --longpress " + keyCode + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
