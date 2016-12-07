package ckt.base;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
/**
 * Created by admin on 2016/12/2.
 */

public class VP4 extends VP2 {
    public static final int STEP_NORMAL= 10;//步长
    public static final String HOME_SHADOW_BUTTON=
            "com.doro.apps.launcher3:id/content_shadow_bottom";//Home界面向下箭头按钮
    public static final String LAUNCH3_APP= "com.doro.apps.launcher3:id/icon";//应用列表
    public VP4(){
    }
    private static UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    public static UiDevice initDoro() {//初始化
        if (mDevice != null) {
            return mDevice;
        } else {
            mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            return mDevice;

        }
    }
    public void unLock() throws UiObjectNotFoundException,RemoteException {//解锁
            mDevice.wakeUp();
            UiObject UnlockBTN=getUiObjectByDes("Unlock");
            if (UnlockBTN!=null) {
                Rect z = UnlockBTN.getBounds();
                int centerX = z.centerX();
                int centerY = z.centerY();
                mDevice.swipe(centerX, centerY, centerX, 0, 10);
            }
        }
    public void openAppliction(String AppName) throws InterruptedException {//打开应用
        switchToApplistPage();
        scrollToBegin(STEP_NORMAL);
        while(!getObjectByIdText(LAUNCH3_APP, AppName).exists()){
            scrollByVerticalForward(STEP_NORMAL);
        }
        try {
            getObjectByIdText(LAUNCH3_APP, AppName).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Home page, menu page, applist page
    public void switchToHomePage(){//回到主界面
        try {
            pressKey("home/back/back");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        if(getUiObjectById(HOME_SHADOW_BUTTON).exists()) {
            scrollByVerticalBackward(STEP_NORMAL);
        }
    }
    public void switchToMenuPage(){//回到快捷应用图标界面
        switchToHomePage();
        scrollByVerticalForward(STEP_NORMAL);
    }
    public void switchToApplistPage(){//进入应用列表
        switchToMenuPage();
        scrollByVerticalForward(STEP_NORMAL);
    }
    public void iWantTo(){//dlj

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
            scr.setAsVerticalList();
            scr.scrollForward(steps);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void scrollToBegin(int steps) {//滑动到当前页面开始位置
        initDevice();
        try {
            UiScrollable scr = new UiScrollable(new UiSelector().scrollable(true));
            scr.setAsVerticalList();
            scr.scrollToBeginning(steps);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void scrollToEnd(int steps) {//滑动到当前页面结束位置
        initDevice();
        try {
            UiScrollable scr = new UiScrollable(new UiSelector().scrollable(true));
            scr.setAsVerticalList();
            scr.scrollToEnd(50,steps);
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
}
