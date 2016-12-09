package doro.action;

import ckt.base.VP4;

import static doro.page.AlarmPage.ALARM_CLICK_CONFIRM_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_NEXT_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_SAVE_TEXT;
import static doro.page.AlarmPage.ALARM_FREQUENCY_FIELD_ID;
import static doro.page.AlarmPage.ALARM_ID_TEXT_ID;

/**
 * Created by Lingjiang.Du on 2016/12/07.
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
    private void chooseWeek(String week){ //选择周几
        try{
            getObjectByIdText(ALARM_ID_TEXT_ID,week).click();
            Thread.sleep(500);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /*
   * 选择手机在周几响铃。
   * */
    public void repeatAlarmSetting(String monday,String tuesday,String wednesday,
                                String thursday, String friday,String saturday,String sunday){
        try{
                getObjectById(ALARM_FREQUENCY_FIELD_ID).clickAndWaitForNewWindow();
                if(monday!=null){
                    chooseWeek(monday);
                }
                if(tuesday!=null){
                    chooseWeek(tuesday);
                }
                if(wednesday!=null){
                    chooseWeek(wednesday);
                }
                if(thursday!=null){
                    chooseWeek(thursday);
                }
                if(friday!=null){
                    chooseWeek(friday);
                }
                if(saturday!=null){
                    chooseWeek(saturday);
                }
                if(sunday!=null){
                    scrollToEnd(10);
                    chooseWeek(sunday);
                }
            getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    /*
    * 增加一个指定周几响闹的闹钟
    * */
    public void addRepeatAlarm(String monday,String tuesday,String wednesday,
                                String thursday, String friday,String saturday,String sunday){
        try{
            getObjectByTextContains("Add alarm").clickAndWaitForNewWindow();
            repeatAlarmSetting(monday,tuesday,wednesday,thursday,friday,saturday,sunday);
            getObjectByTextContains(ALARM_CLICK_NEXT_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_SAVE_TEXT).clickAndWaitForNewWindow();
            Thread.sleep(6000);
            gDevice.pressBack();
        }catch(Exception e){e.printStackTrace();}
    }
}
