package doro.action;

import android.support.test.uiautomator.UiObject;

import ckt.base.VP4;

import static doro.page.LockScreenPage.*;

/**
 * Created by Tao.Wang on 2017/1/19.
 */

public class LockScreenAction extends VP4 {
    public void clearAllApplications(){
        try {
            unLock();
            mDevice.pressMenu();
            waitTime(3);
            UiObject Object = getObjectById(LOCKSCREEN_DISMISSTASK_ID);
            while (Object.exists()) {
                Object.clickAndWaitForNewWindow();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void lockScreen(){
        try {
            waitTime(1);
            mDevice.sleep();
            waitTime(2);
            mDevice.wakeUp();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
