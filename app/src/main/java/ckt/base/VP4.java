package ckt.base;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
/**
 * Created by admin on 2016/12/2.
 */

public class VP4 extends VP2 {
    public static final int STEP_NORMAL= 10;
    public static final String HOME_SHADOW_BUTTON=
            "com.doro.apps.launcher3:id/content_shadow_bottom";
    public static final String LAUNCH3_APP= "com.doro.apps.launcher3:id/icon";
    public VP4(){
    }
    public void initDoro(){//wt

    }
    public void unlock(){//wt

    }
    public void openAppliction(String AppName) throws InterruptedException {//zb
        switchToApplistPage();
        while(!getObjectByIdText(LAUNCH3_APP, AppName).exists()){
            scrollByVerticalForward(STEP_NORMAL);
        }
    }

    //Home page, menu page, applist page
    public void switchToHomePage(){//zb
        try {
            pressKey("home/back");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        if(getUiObjectById(HOME_SHADOW_BUTTON).exists()) {
            scrollByVerticalBackward(STEP_NORMAL);
        }
    }
    public void switchToMenuPage(){//zb
        switchToHomePage();
        scrollByVerticalForward(STEP_NORMAL);
    }
    public void switchToApplistPage(){//zb
        switchToMenuPage();
        scrollByVerticalForward(STEP_NORMAL);
    }
    public void iWantTo(){//dlj

    }
    public static void scrollByVerticalBackward(int steps) {
        initDevice();
        try {
            UiScrollable scr = new UiScrollable(new UiSelector().scrollable(true));
            scr.setAsVerticalList();
            scr.scrollBackward(steps);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void scrollByVerticalForward(int steps) {
        initDevice();
        try {
            UiScrollable scr = new UiScrollable(new UiSelector().scrollable(true));
            scr.setAsVerticalList();
            scr.scrollForward(steps);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static UiObject getObjectByIdText(String ResourceID, String text) {
        initDevice();
        return gDevice.findObject(new UiSelector().resourceId(ResourceID).text(text));
    }
}
