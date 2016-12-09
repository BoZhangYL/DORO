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
    /*
    * 添加一个指定时间的闹钟,注意时间格式是String类型
    * */
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
    /*
    * 添加一个指定重复天数的闹钟，范围0~7.0代表不重复，1代表周一，2代表周一，二，....7代表每天。
    * */
    public void addRepeatAlarm(int i){
        try{
            getObjectByTextContains("Add alarm").click();
            Thread.sleep(2000);
            switch(i){
                case 0:getObjectByTextContains("Next").click();
                    break;
                case 1:
                    getObjectById("com.doro.apps.alarm:id/frequency_field").click();
                    getObjectByTextContains("Monday").click();
                    getObjectByTextContains("Confirm").click();
                    getObjectByTextContains("Next").click();
                    break;
                case 2:
                    getObjectById("com.doro.apps.alarm:id/frequency_field").click();
                    getObjectByTextContains("Monday").click();
                    getObjectByTextContains("Tuesday").click();
                    getObjectByTextContains("Confirm").click();
                    getObjectByTextContains("Next").click();
                    break;
                case 3:
                    getObjectById("com.doro.apps.alarm:id/frequency_field").click();
                    getObjectByTextContains("Monday").click();
                    getObjectByTextContains("Tuesday").click();
                    getObjectByTextContains("Wednesday").click();
                    getObjectByTextContains("Confirm").click();
                    getObjectByTextContains("Next").click();
                    break;
                case 4:
                    getObjectById("com.doro.apps.alarm:id/frequency_field").click();
                    getObjectByTextContains("Monday").click();
                    getObjectByTextContains("Tuesday").click();
                    getObjectByTextContains("Wednesday").click();
                    getObjectByTextContains("Thursday").click();
                    getObjectByTextContains("Confirm").click();
                    getObjectByTextContains("Next").click();
                    break;
                case 5:
                    getObjectById("com.doro.apps.alarm:id/frequency_field").click();
                    getObjectByTextContains("Monday").click();
                    getObjectByTextContains("Tuesday").click();
                    getObjectByTextContains("Wednesday").click();
                    getObjectByTextContains("Thursday").click();
                    getObjectByTextContains("Friday").click();
                    getObjectByTextContains("Confirm").click();
                    getObjectByTextContains("Next").click();
                    break;
                case 6:
                    getObjectById("com.doro.apps.alarm:id/frequency_field").click();
                    getObjectByTextContains("Monday").click();
                    getObjectByTextContains("Tuesday").click();
                    getObjectByTextContains("Wednesday").click();
                    getObjectByTextContains("Thursday").click();
                    getObjectByTextContains("Friday").click();
                    getObjectByTextContains("Saturday").click();
                    getObjectByTextContains("Confirm").click();
                    getObjectByTextContains("Next").click();
                    break;
                default:
                    getObjectById("com.doro.apps.alarm:id/frequency_field").click();
                    getObjectByTextContains("Monday").click();
                    getObjectByTextContains("Tuesday").click();
                    getObjectByTextContains("Wednesday").click();
                    getObjectByTextContains("Thursday").click();
                    getObjectByTextContains("Friday").click();
                    getObjectByTextContains("Saturday").click();
                    scrollToEnd(10);
                    getObjectByTextContains("Sunday").click();
                    getObjectByTextContains("Confirm").click();
                    getObjectByTextContains("Next").click();
                    break;
            }
            getObjectByTextContains("Save").click();
            Thread.sleep(6000);
            gDevice.pressBack();
        }catch(Exception e){e.printStackTrace();}
    }
}
