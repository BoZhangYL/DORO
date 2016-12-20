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
            UiObject SetButton=getObjectById(Timer_Set_ID);
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
            clickTimerButton(Timer_Start_Text);
            Thread.sleep(TimerNumber*1000+1000);
        }
          catch (Exception e){
              e.printStackTrace();
          }
    }
    public void clickTimerButton(String ButtonName){//按键Start/Stop/Pause/Reset/Set
        try {
            initDevice();
            if (ButtonName.equals(Timer_Reset_Text)) {
                UiObject TimerButton =getObjectById(Timer_Reset_ID);
                TimerButton.click();
            } else {
                if (ButtonName.equals(Timer_Set_Text)) {
                    UiObject TimerButton =getObjectById(Timer_Set_ID);
                    TimerButton.click();
                } else {
                    UiObject TimerButton =getObjectByIdText(Timer_Other_Button_ID,ButtonName);
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
            UiObject DeleteButton=getUiObjectByClassID(Timer_Deletet_Class,Timer_Delete_ID);
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
        clickTimerButton(Timer_Stop_Text);
    }
    public void checkLaunchTimerResult(){
        verifyResultByID("Case1_launchTimer",Timer_Title_Text,Timer_Title_ID);
    }
    public void check30sTimerResult(){
        verifyResultByID("Case2_setTimerto30s",Timer_Stop_Text,Timer_Other_Button_ID);
    }
}

