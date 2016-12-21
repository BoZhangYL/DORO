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
    public static final int STEP_NORMAL= 20;//步长
    public static final String HOME_SHADOW_BUTTON=
            "com.doro.apps.launcher3:id/content_shadow_bottom";//Home界面向下箭头按钮
    public static final String LAUNCH3_APP= "com.doro.apps.launcher3:id/icon";//应用列表
    public VP4(){}

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
        while(UnlockBTN.exists()) {
            try {
                Rect  z = UnlockBTN.getBounds();
                int centerX = z.centerX();
                int centerY = z.centerY();
                gDevice.swipe(centerX, centerY, centerX, 0, 10);
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void openAppliction(String AppName){//打开应用
        switchToApplistPage();
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
            initRent();
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
    public static UiObject getUiObjectByClassText(String TragetClass,String TragetObject) {
        //得到指定Class、Text的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().className(TragetClass).text(TragetObject));
    }
    public static UiObject getUiObjectByClassID(String TragetClass,String ResourceID) {
        //得到指定Class、ResourceID的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().className(TragetClass).resourceId(ResourceID));
    }
    public static UiObject getObjectByDesc(String TragetObject) {
        //得到指定Content-desc的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().description(TragetObject));
    }
    public static UiObject getObjectByClass(String TragetObject){
        //得到指定Class的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().className(TragetObject));
    }
    public static UiObject getObjectByClassDesc(String ClassObject,String DescObject) {
        //得到指定Class，Content-desc的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().className(ClassObject).description(DescObject));
    }
    public static UiObject getObjectByEnabled(boolean openClose){
        //得到指定Enabled的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().enabled(openClose));
    }
    public static UiObject getObjectByTextEnabled(String TragetObject, boolean openClose){
        //得到指定,Text,Enabled的对应object
        initDevice();
        return gDevice.findObject(new UiSelector().text(TragetObject).enabled(openClose));
    }
    public static UiObject getObjectByText(String Text){
        //得到指定Text对应object
        initDevice();
        return gDevice.findObject(new UiSelector().text(Text));
    }
    public static UiObject getObjectByClassPackage(String classObject,String packageObject ){
        //得到指定的class 与package的Object
        initDevice();
        return gDevice.findObject(new UiSelector().className(classObject).packageName(packageObject));
    }
    public void initRent(){//清除recent
        try {
            initDevice();
            pressKey("menu");
            while (getObjectById("com.android.systemui:id/task_view_thumbnail").exists()) {
                scrollLeft(getObjectById("com.android.systemui:id/task_view_thumbnail"),
                        5);
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void phoneWaitTime(double mins){
        try{
            Thread.sleep((long)(mins*60*1000));
        }catch(Exception e){e.printStackTrace();}
    }
}
