package doro.action;

import ckt.base.VP4;

/**
 * Created by user on 2016/12/07   .
 */

public class AlarmAction extends VP4 {
    /*
    * 进入与退出闹钟界面，反复执行 counts 次。
    * */
    public void enterAndExitAlarm(int counts){
        try{
            for(int i=0;i<counts;i++){
                pressKey("back");
                getObjectByTextContains("Alarm").clickAndWaitForNewWindow();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addTimeAlarm(String hours,String mins){
        try{
            getObjectByTextContains("Add alarm").click();
            Thread.sleep(2000);
            getObjectById("com.doro.apps.alarm:id/time_field").click();
            specialTime(hours,mins);
            getObjectByTextContains("Confirm").click();
            getObjectByTextContains("Next").click();
            getObjectByTextContains("Save").click();
            Thread.sleep(6000);
            gDevice.pressBack();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
