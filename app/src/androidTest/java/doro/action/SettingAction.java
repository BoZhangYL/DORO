package doro.action;

import ckt.base.VP4;
import doro.page.SettingPage;

import static doro.page.SettingPage.SETTINGS_AUTOMATIC_DATETIME_TEXT;
import static doro.page.SettingPage.SETTINGS_AUTO_TIME_ZONE_TEXT;
import static doro.page.SettingPage.SETTINGS_CHOOSE_12HOUR_FORMAT_TEXT;
import static doro.page.SettingPage.SETTINGS_CHOOSE_24HOUR_FORMAT_TEXT;
import static doro.page.SettingPage.SETTINGS_DATE_HEADER_DATE_ID;
import static doro.page.SettingPage.SETTINGS_DATE_HEADER_YEAR_ID;
import static doro.page.SettingPage.SETTINGS_DATE_MONTH_NEXT_ID;
import static doro.page.SettingPage.SETTINGS_DATE_MONTH_PREV_ID;
import static doro.page.SettingPage.SETTINGS_DATE_TIME_TEXT;
import static doro.page.SettingPage.SETTINGS_DATE_YEAR_TEXT1_ID;
import static doro.page.SettingPage.SETTINGS_GPS_PROVIDED_TEXT;
import static doro.page.SettingPage.SETTINGS_NETWORK_PROVIDED_TIME_TEXT;
import static doro.page.SettingPage.SETTINGS_OFF_TEXT;
import static doro.page.SettingPage.SETTINGS_SELECT_TIME_ZONE_TEXT;
import static doro.page.SettingPage.SETTINGS_SET_DATE_OK_TEXT;
import static doro.page.SettingPage.SETTINGS_SET_DATE_TEXT;
import static doro.page.SettingPage.SETTINGS_SET_TIME_TEXT;
import static doro.page.SettingPage.SETTINGS_TIME_HOURS_ID;
import static doro.page.SettingPage.SETTINGS_TIME_MINUTES_ID;
import static doro.page.SettingPage.SETTINGS_USE_24HOUR_FORMAT_TEXT;
import static doro.page.SettingPage.SETTING_DATE_DAY_VIEW_CLASS;
import static doro.page.SettingPage.SETTING_HOUR_TOUCHHELPER_CLASS;
import static java.lang.Integer.parseInt;

/**
 * Created by lingjiang.du on 2016/12/1.
 */
public class SettingAction extends VP4 {
    public static void openBluetooth() {
        clickById(SettingPage.BLUETOOTH_SWITCH);
    }

    public static void closeBluetooth() {
    }

    public void dateAndTime() { //进入手机设置中的 Date&Time
        try {
            while (!getObjectByTextContains(SETTINGS_DATE_TIME_TEXT).exists()) {
                scrollByVerticalForward(15);
            }
            getObjectByTextContains(SETTINGS_DATE_TIME_TEXT).clickAndWaitForNewWindow();
        } catch (Exception e) {e.printStackTrace();}
    }

    public void whatProvidedTime(int style) {
        //选择使用什么方式提供时间：0：个人设置；1：网络提供时间；2：GPS提供时间。
        try {
            switch (style) {
                case 1:
                    if (getUiObjectByText(SETTINGS_NETWORK_PROVIDED_TIME_TEXT).exists()) {
                        Thread.sleep(500);
                        break;
                    } else {
                        getObjectByTextContains(SETTINGS_AUTOMATIC_DATETIME_TEXT).clickAndWaitForNewWindow();
                        getObjectByTextContains(SETTINGS_NETWORK_PROVIDED_TIME_TEXT).click();
                        break;
                    }
                case 2:
                    if (getObjectByTextContains(SETTINGS_GPS_PROVIDED_TEXT).exists()) {
                        Thread.sleep(500);
                        break;
                    } else {
                        getObjectByTextContains(SETTINGS_AUTOMATIC_DATETIME_TEXT).clickAndWaitForNewWindow();
                        getObjectByTextContains(SETTINGS_GPS_PROVIDED_TEXT).click();
                        break;
                    }
                default:
                    if (getObjectByTextContains(SETTINGS_OFF_TEXT).exists()) {
                        Thread.sleep(500);
                        break;
                    } else {
                        getObjectByTextContains(SETTINGS_AUTOMATIC_DATETIME_TEXT).clickAndWaitForNewWindow();
                        getObjectByTextContains(SETTINGS_OFF_TEXT).click();
                        break;
                    }
            }
        } catch (Exception e) {e.printStackTrace();}
    }
    public void setNextDay(){//设置成下一天
        try{
            getUiObjectByText(SETTINGS_SET_DATE_TEXT).clickAndWaitForNewWindow();
            String[] month = getObjectById(SETTINGS_DATE_HEADER_DATE_ID).getText().split(" ");
            int days =Integer.parseInt(month[1])+1;
            String sDays = String.valueOf(days);
            getUiObjectByClassText(SETTING_DATE_DAY_VIEW_CLASS,sDays).click();
            getUiObjectByText(SETTINGS_SET_DATE_OK_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void setDate(String date){ //年-月-日 格式：YYYY-MM-DD
        try{
            String[] timeDate =date.split("-");
            int months = parseInt(timeDate[1]);
            getUiObjectByText(SETTINGS_SET_DATE_TEXT).clickAndWaitForNewWindow();
            String year =getObjectById(SETTINGS_DATE_HEADER_YEAR_ID).getText();
            if(!year.equals(timeDate[0])) {
                getObjectById(SETTINGS_DATE_HEADER_YEAR_ID).click();
                scrollToBegin(15);
                while (!getObjectByIdText(SETTINGS_DATE_YEAR_TEXT1_ID, timeDate[0]).exists()) {
                    scrollByVerticalForward(30);
                }
                getObjectByIdText(SETTINGS_DATE_YEAR_TEXT1_ID, timeDate[0]).click();
            }
            String[] month = getObjectById(SETTINGS_DATE_HEADER_DATE_ID).getText().split(" ");
            while (!month[2].equals("Jan")) {
                getObjectById(SETTINGS_DATE_MONTH_PREV_ID).click();
                getUiObjectByClassText(SETTING_DATE_DAY_VIEW_CLASS, "1").click();
                String[] month1 = getObjectById(SETTINGS_DATE_HEADER_DATE_ID).getText().split(" ");
                month[2] = month1[2];
            }
            for (int i = 1; i < months; i++) {
                getObjectById(SETTINGS_DATE_MONTH_NEXT_ID).click();
                Thread.sleep(100);
            }
            int days =Integer.parseInt(timeDate[2]);
            String sDays = String.valueOf(days);
            getUiObjectByClassText(SETTING_DATE_DAY_VIEW_CLASS,sDays).click();
            Thread.sleep(1000);
            getUiObjectByText(SETTINGS_SET_DATE_OK_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    private void setTime(String time){ //设置时间
        try{
            whatProvidedTime(0);
            getUiObjectByText(SETTINGS_SET_TIME_TEXT).clickAndWaitForNewWindow();
            String[] times =time.split(":");
            int hour = Integer.valueOf(times[0]);
            String sHour = String.valueOf(hour);
            getObjectById(SETTINGS_TIME_HOURS_ID).click();
            getObjectByClassDesc(SETTING_HOUR_TOUCHHELPER_CLASS,sHour).click();
            getObjectById(SETTINGS_TIME_MINUTES_ID).click();
            int iTimes = Integer.valueOf(times[1]);
            String sTime = String.valueOf(iTimes/5*5);
            getObjectByDesc(sTime).click();
        }catch(Exception e){e.printStackTrace();}
    }
    public void setTime12(String time,String timeFormat){//时间格式：hh:mm,12小时制
        try{
            setTime(time);
            getUiObjectByText(timeFormat).click();
            getUiObjectByText(SETTINGS_SET_DATE_OK_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void setTime24(String time){ //时间格式：hh:mm,24小时制
        try{
            setTime(time);
            getUiObjectByText(SETTINGS_SET_DATE_OK_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void useNetworkTimeZone(boolean yesOrNo){ //选择是否使用网络提供的时区.true:是；
        try{
            if(yesOrNo){
                if(getUiObjectByText(SETTINGS_SELECT_TIME_ZONE_TEXT).isEnabled()){
                    getUiObjectByText(SETTINGS_AUTO_TIME_ZONE_TEXT).click();
                }
            }else{
                if(!getUiObjectByText(SETTINGS_SELECT_TIME_ZONE_TEXT).isEnabled()){
                    getUiObjectByText(SETTINGS_AUTO_TIME_ZONE_TEXT).click();
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    public void selectTimeZone(String cityName){ //手动选择时区
        try{
            useNetworkTimeZone(false);
            getUiObjectByText(SETTINGS_SELECT_TIME_ZONE_TEXT).clickAndWaitForNewWindow();
            scrollToBegin(1);
            while(!getUiObjectByText(cityName).exists()){
                scrollByVerticalForward(35);
            }
            getUiObjectByText(cityName).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void Use24HourFormat(boolean yesOrNo){ //是否使用24小时制
        try{
            if(yesOrNo){
                if(getUiObjectByText(SETTINGS_CHOOSE_12HOUR_FORMAT_TEXT).exists()){
                    getUiObjectByText(SETTINGS_USE_24HOUR_FORMAT_TEXT).click();
                }
            }else{
                if(getUiObjectByText(SETTINGS_CHOOSE_24HOUR_FORMAT_TEXT).exists()){
                    getUiObjectByText(SETTINGS_USE_24HOUR_FORMAT_TEXT).click();
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    /*
* 一些基本的关于时间日期的设置
* */
    public void setSpecialTime(String time){ //去设置中设置一个时间
        openAppliction("Settings"); //找到设置应用
        dateAndTime(); //找到时间设置
        whatProvidedTime(0);
        setTime24(time);//设置手机时间
    }
    public void setSpecialDate(String date){ //去设置中设置一个日期
        openAppliction("Settings"); //找到设置应用
        dateAndTime(); //找到时间设置
        whatProvidedTime(0);
        setDate(date);//设置手机日期
    }
    public void setDateTime(String date,String time){ //去设置中设置日期与时间
        openAppliction("Settings"); //找到设置应用
        dateAndTime(); //找到时间设置
        whatProvidedTime(0);
        setDate(date);//设置手机日期
        setTime24(time);//设置手机时间
    }
    public void setNextdayTime(String time){ //去设置中设置下一天的时间
        openAppliction("Settings"); //找到设置应用
        dateAndTime(); //找到时间设置
        whatProvidedTime(0);//使用自定义时间
        setNextDay();//更改日期为下一天
        setTime24(time);//设置手机时间为18:21
    }
    public void setEverydayTime(String time ,double waitTime){ //去设置中设置一周的时间
        for(int i=0;i<7;i++){
            setNextdayTime(time);//设置手机时间
            phoneWaitTime(waitTime);//手机等待分钟
            new AlarmAction().checkAlarmComing();//判断闹钟是否到来
            new AlarmAction().alarmComingStop();//闹钟到来后选择关闭闹钟
        }
    }

}


