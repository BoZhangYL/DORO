package doro.action;

import android.os.RemoteException;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import ckt.base.VP4;

/**
 * Created by admin on 2016/12/8.
 */

public class TimerAction extends VP4 {
    public void SetTimerNumber(int TimerNumber) throws RemoteException{//输入数字
        initDoro();
        String Number=TimerNumber+"";
        int i;
        int j=Number.length();
        for (i=0;i<j;i++) {
            String Number1 = Number.charAt(i) + "";
        UiObject NamberButton=mDevice.findObject(new UiSelector().className("android.widget.Button").text(Number1));
          try {
              NamberButton.click();
          }catch (UiObjectNotFoundException e){
              e.printStackTrace();
          }
        }
    }
    public void ClickTimerButton(String ButtonName) throws RemoteException,UiObjectNotFoundException {//按键Start/Stop/Pause/Reset/Set
        initDoro();
        if (ButtonName.equals("Reset")) {
            UiObject TimerButton = mDevice.findObject(new UiSelector().resourceId("com.doro.apps.timer:id/left_button"));
            TimerButton.click();
        } else {
            if (ButtonName.equals("Set")) {
                UiObject TimerButton = mDevice.findObject(new UiSelector().resourceId("com.doro.apps.timer:id/right_button"));
                TimerButton.click();
            } else {
                UiObject TimerButton = mDevice.findObject(new UiSelector().resourceId("com.doro.apps.timer:id/fab").text(ButtonName));
                TimerButton.click();
            }
        }
    }

    public void DeleteNumber(){//按五次删除键
        int a;
        for(a=0;a<=4;a++){
            UiObject DeleteButton=mDevice.findObject(new UiSelector().className("android.widget.ImageButton").resourceId("com.doro.apps.timer:id/delete"));
            try {
                DeleteButton.click();
            }catch (UiObjectNotFoundException e){
                e.printStackTrace();
            }

        }
    }
}
