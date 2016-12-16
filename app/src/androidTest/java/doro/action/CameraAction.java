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

    public void launchCameraByShortcut(){
        UiObject CameraShortcut = gDevice.findObject(new UiSelector().
                resourceId(HOME_GROUP_VIEW).index(1).index(1));
        switchToHomePage();
        try {
            CameraShortcut.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}
