package doro.action;


import android.support.test.uiautomator.UiObject;
import org.junit.Assert;
import ckt.base.VP4;
import static doro.page.TimerPage.*;

/**
 * Created by admin on 2016/12/8.
 */

public class TimerAction extends VP4 {
    public void startTimer(int TimerNumber){//输入数字
        initDevice();
        try {
            UiObject SetButton=getObjectById(Set_ID);
        if (SetButton.exists()) {
            SetButton.click();
        }
        String Number=TimerNumber+"";
        int i;
        int j=Number.length();
        for (i=0;i<j;i++) {
            String Number1 = Number.charAt(i) + "";
            UiObject NamberButton=getUiObjectByClassText(Timer_Class,Number1);
              NamberButton.click(); }
            clickTimerButton(Start_Text);
            Thread.sleep(TimerNumber*1000+1000);
        }
          catch (Exception e){
              e.printStackTrace();
          }
    }
    public void clickTimerButton(String ButtonName){//按键Start/Stop/Pause/Reset/Set
        try {
            initDevice();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteNumber(){//按五次删除键
        int a;
        for(a=0;a<=4;a++){
            UiObject DeleteButton=getUiObjectByClassID(Deletet_Class,Delete_ID);
            try {
                DeleteButton.click();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void verifyResultByID(String Case_Name,String Expected_Text,String Actual_ID){
        try {
            Assert.assertEquals(Case_Name, Expected_Text, getObjectById(Actual_ID).getText());
        }catch (Exception e) {
        e.printStackTrace();
        }
    }
    public void openTimer(){
        openAppliction(Timer_Title_Text);
    }
    public void clickStopButton(){
        clickTimerButton(Stop_Text);
    }
    public void checkLaunchTimerResult(){
        verifyResultByID("Case1_launchTimer",Timer_Title_Text,Title_ID);
    }
    public void check30sTimerResult(){
        verifyResultByID("Case2_setTimerto30s",Stop_Text,Other_Button_ID);
    }
}
