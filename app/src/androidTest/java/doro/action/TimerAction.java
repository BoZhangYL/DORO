package doro.action;


import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import ckt.base.VP4;
import static doro.page.TimerPage.*;

/**
 * Created by admin on 2016/12/8.
 */

public class TimerAction extends VP4 {
    public void SetTimerNumber(int TimerNumber){//输入数字
        initDoro();
        String Number=TimerNumber+"";
        int i;
        int j=Number.length();
        for (i=0;i<j;i++) {
            String Number1 = Number.charAt(i) + "";
            UiObject NamberButton=getUiObjectByClassText(Timer_Class,Number1);
          try {
              NamberButton.click();
          }catch (UiObjectNotFoundException e){
              e.printStackTrace();
          }
        }
    }
    public void ClickTimerButton(String ButtonName){//按键Start/Stop/Pause/Reset/Set
        try {
            initDoro();
            if (ButtonName.equals(Reset_Text)) {
                UiObject TimerButton =getObjectById(Reset_ID);
                TimerButton.click();
            } else {
                if (ButtonName.equals(Set_Text)) {
                    UiObject TimerButton =getObjectById(Set_ID);
                    TimerButton.click();
                } else {
                    UiObject TimerButton =getObjectByIdText(Other_Button_ID,ButtonName);
                    TimerButton.click();
                }
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void DeleteNumber(){//按五次删除键
        int a;
        for(a=0;a<=4;a++){
            UiObject DeleteButton=getUiObjectByClassID(Timer_Class,Delete_ID);
            try {
                DeleteButton.click();
            }catch (UiObjectNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
