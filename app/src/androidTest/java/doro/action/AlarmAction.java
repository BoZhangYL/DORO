package doro.action;

import android.support.test.uiautomator.UiSelector;

import ckt.base.VP4;

import static doro.page.AlarmPage.ALARM_ACTIVATE_VIBRATION;
import static doro.page.AlarmPage.ALARM_CLICK_ADD_ALARM_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_CANCEL_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_CONFIRM_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_DELETEALARM_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_IWANTTO_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_NEXT_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_OK_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_SAVE_TEXT;
import static doro.page.AlarmPage.ALARM_CLICK_SELECTALL_TEXT;
import static doro.page.AlarmPage.ALARM_FREQUENCY_FIELD_ID;
import static doro.page.AlarmPage.ALARM_HOUR_DECREASE_ID;
import static doro.page.AlarmPage.ALARM_HOUR_EDIT_ID;
import static doro.page.AlarmPage.ALARM_HOUR_INCREASE_ID;
import static doro.page.AlarmPage.ALARM_ID_TEXT_ID;
import static doro.page.AlarmPage.ALARM_MELODY_FIELD_ID;
import static doro.page.AlarmPage.ALARM_MINUTE_DECREASE_ID;
import static doro.page.AlarmPage.ALARM_MINUTE_EDIT_ID;
import static doro.page.AlarmPage.ALARM_MINUTE_INCREASE_ID;
import static doro.page.AlarmPage.ALARM_RELATIVELAYOUT_CLASS;
import static doro.page.AlarmPage.ALARM_TIME_FIELD_ID;

/**
 * Created by Lingjiang.Du on 2016/12/07.
 */

public class AlarmAction extends VP4 {
    public void enterAndExitAlarm(int counts){ //进入与退出闹钟界面，反复执行 counts 次。
        try{
            for(int i=0;i<counts;i++){
                pressKey("back");
                getObjectByTextContains("Alarm").clickAndWaitForNewWindow();
            }
        }catch(Exception e){
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
    public void repeatAlarmSetting(String monday,String tuesday,String wednesday,
                                String thursday, String friday,String saturday,String sunday){  //选择手机在周几响铃。
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
    public void specialTime(String time){//得到指定的时间
        String[] hourMinTime = time.split(":");
        try{
            String hour =getObjectById(ALARM_HOUR_EDIT_ID).getText();
            int h = Integer.valueOf(hour);
            String min =getObjectById(ALARM_MINUTE_EDIT_ID).getText();
            int m = Integer.valueOf(min);
            int countH =h-Integer.valueOf(hourMinTime[0]);
            int countM =m-Integer.valueOf(hourMinTime[1]);
            if(countH<0){
                for(int i=0;i<countH;i++){
                    getObjectById(ALARM_HOUR_INCREASE_ID).click();
                }
            }else{
                for(int j=0;j<countH;j++) {
                    getObjectById(ALARM_HOUR_DECREASE_ID).click();
                }
            }
            if(countM<0){
                for(int i=0;i<countM;i++){
                    getObjectById(ALARM_MINUTE_INCREASE_ID).click();
                }
            }else{
                for(int j=0;j<countM;j++){
                    getObjectById(ALARM_MINUTE_DECREASE_ID).click();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setRingtone(String tone,boolean vibrate){ //更改闹钟铃声以及选择是否震动
        try{
            getObjectById(ALARM_MELODY_FIELD_ID).clickAndWaitForNewWindow();
            scrollToBegin(15);
            while(!getObjectByIdText(ALARM_ID_TEXT_ID,tone).exists()){
                scrollByVerticalForward(15);
            }
            getObjectByIdText(ALARM_ID_TEXT_ID,tone).click();
            Thread.sleep(10000);
            getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
            if(vibrate){
                if(mDevice.findObject(new UiSelector().checkable(true)).exists()){
                    Thread.sleep(500);
                }else{
                    getObjectById(ALARM_ACTIVATE_VIBRATION).click();
                }
            }else{
                if(mDevice.findObject(new UiSelector().checkable(true)).exists()){
                    getObjectById(ALARM_ACTIVATE_VIBRATION).click();
                }else{
                    Thread.sleep(500);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    * 增加一个指定时间的闹钟
    * */
    public void addTimeAlarm(String time ){
        try{
            getObjectByTextContains(ALARM_CLICK_ADD_ALARM_TEXT).clickAndWaitForNewWindow();
            getObjectById(ALARM_TIME_FIELD_ID).clickAndWaitForNewWindow();
            specialTime(time);
            getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_NEXT_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_SAVE_TEXT).clickAndWaitForNewWindow();
            Thread.sleep(6000);
            gDevice.pressBack();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    * 增加一个指定周几响闹的闹钟
    * */
    public void addRepeatAlarm(String monday,String tuesday,String wednesday,
                               String thursday, String friday,String saturday,String sunday){
        try{
            getObjectByTextContains(ALARM_CLICK_ADD_ALARM_TEXT).clickAndWaitForNewWindow();
            repeatAlarmSetting(monday,tuesday,wednesday,thursday,friday,saturday,sunday);
            getObjectByTextContains(ALARM_CLICK_NEXT_TEXT).clickAndWaitForNewWindow();
            getObjectByTextContains(ALARM_CLICK_SAVE_TEXT).clickAndWaitForNewWindow();
            Thread.sleep(6000);
            gDevice.pressBack();
        }catch(Exception e){e.printStackTrace();}
    }

    public void deleteAllAlarm(){ //删除所有闹钟操作
        try{
            if(!getObjectByTextContains(ALARM_CLICK_IWANTTO_TEXT).exists()){
                System.out.println("没有闹钟可以删除");
            }else{
                getObjectByTextContains(ALARM_CLICK_IWANTTO_TEXT).clickAndWaitForNewWindow();
                getObjectByTextContains(ALARM_CLICK_DELETEALARM_TEXT).click();
                getObjectByTextContains(ALARM_CLICK_SELECTALL_TEXT).click();
                getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
                getObjectByTextContains(ALARM_CLICK_CANCEL_TEXT).clickAndWaitForNewWindow();
                getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
                getObjectByTextContains(ALARM_CLICK_OK_TEXT).clickAndWaitForNewWindow();

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteOneAlarm(){ //删除第一闹钟操作
        try{
            if(!getObjectByTextContains(ALARM_CLICK_IWANTTO_TEXT).exists()){
                System.out.println("没有闹钟可以删除");
            }else{
                getObjectByTextContains(ALARM_CLICK_IWANTTO_TEXT).clickAndWaitForNewWindow();
                getObjectByTextContains(ALARM_CLICK_DELETEALARM_TEXT).click();
                mDevice.findObject(new UiSelector().className(ALARM_RELATIVELAYOUT_CLASS).index(1)).click();
                getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
                getObjectByTextContains(ALARM_CLICK_CANCEL_TEXT).clickAndWaitForNewWindow();
                getObjectByTextContains(ALARM_CLICK_CONFIRM_TEXT).clickAndWaitForNewWindow();
                getObjectByTextContains(ALARM_CLICK_OK_TEXT).clickAndWaitForNewWindow();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

