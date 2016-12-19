package doro.action;

import android.support.test.uiautomator.Tracer;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

import ckt.base.VP4;

import static doro.page.CameraPage.*;

/**
 * Created by bo.zhang on 2016/12/15   .
 */

public class CameraAction extends VP4{

    public static void launchCameraByShortcut(){
       // switchToHomePage();
        // getUiObjectByIdIndexIndex(HOME_GROUP_VIEW,1,0).clickAndWaitForNewWindow();
        try {
            gDevice.pressHome();
            gDevice.findObject(new UiSelector().resourceId("com.doro.apps.launcher3:id/this_is_da_boss_layout").index(2).index(1)).
                    clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}
