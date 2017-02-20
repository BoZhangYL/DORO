package doro.action;

import junit.framework.Assert;

import ckt.base.VP4;

import static doro.page.SettingPage.SETTINGS_APPS_ADVANCED_ID;
import static doro.page.SettingPage.SETTINGS_APPS_TEXT;
import static doro.page.SettingPage.SETTINGS_APP_INFO_TEXT;
import static doro.page.SettingPage.SETTINGS_AUTOMATIC_DATETIME_TEXT;
import static doro.page.SettingPage.SETTINGS_AUTO_TIME_ZONE_TEXT;
import static doro.page.SettingPage.SETTINGS_BLUETOOTHSWITCH_TEXT;
import static doro.page.SettingPage.SETTINGS_CHOOSE_12HOUR_FORMAT_TEXT;
import static doro.page.SettingPage.SETTINGS_CHOOSE_24HOUR_FORMAT_TEXT;
import static doro.page.SettingPage.SETTINGS_CONFIGURE_APPS_TEXT;
import static doro.page.SettingPage.SETTINGS_DATE_HEADER_DATE_ID;
import static doro.page.SettingPage.SETTINGS_DATE_HEADER_YEAR_ID;
import static doro.page.SettingPage.SETTINGS_DATE_MONTH_NEXT_ID;
import static doro.page.SettingPage.SETTINGS_DATE_MONTH_PREV_ID;
import static doro.page.SettingPage.SETTINGS_DATE_TIME_TEXT;
import static doro.page.SettingPage.SETTINGS_DATE_YEAR_TEXT1_ID;
import static doro.page.SettingPage.SETTINGS_FLIGHTMODE_ON_TEXT;
import static doro.page.SettingPage.SETTINGS_FLIGHTMODE_TURNOFF_TEXT;
import static doro.page.SettingPage.SETTINGS_HOME_TEXT;
import static doro.page.SettingPage.SETTINGS_SELECT_TIME_ZONE_TEXT;
import static doro.page.SettingPage.SETTINGS_SET_DATE_OK_TEXT;
import static doro.page.SettingPage.SETTINGS_SET_DATE_TEXT;
import static doro.page.SettingPage.SETTINGS_SET_TIME_TEXT;
import static doro.page.SettingPage.SETTINGS_STORAGE_LINEARLAYOUT_CLASS;
import static doro.page.SettingPage.SETTINGS_STORAGE_RADIOBUTTON_CLASS;
import static doro.page.SettingPage.SETTINGS_STORAGE_RECYCLERVIEWER_CLASS;
import static doro.page.SettingPage.SETTINGS_STORAGE_SETTINGS_TEXT;
import static doro.page.SettingPage.SETTINGS_STORAGE_TEXT;
import static doro.page.SettingPage.SETTINGS_TIME_HOURS_ID;
import static doro.page.SettingPage.SETTINGS_TIME_MINUTES_ID;
import static doro.page.SettingPage.SETTINGS_TITLE_ID;
import static doro.page.SettingPage.SETTINGS_USE_24HOUR_FORMAT_TEXT;
import static doro.page.SettingPage.SETTINGS_WIDGET_IMAGEBUTTON_CLASS;
import static doro.page.SettingPage.SETTINGS_WIDGET_LINEARLAYOUT_CLASS;
import static doro.page.SettingPage.SETTINGS_WIDGET_LISTVIEW_CLASS;
import static doro.page.SettingPage.SETTINGS_WIDGET_SWITCH_CLASS;
import static doro.page.SettingPage.SETTING_DATE_DAY_VIEW_CLASS;
import static doro.page.SettingPage.SETTING_HOUR_TOUCHHELPER_CLASS;
import static java.lang.Integer.parseInt;

/**
 * Created by lingjiang.du on 2016/12/1.
 */
public class SettingAction extends VP4 {
    public void bluetooth(boolean bluetooth){ //打开或者关闭蓝牙
        try{
            String status =getObjectByText(SETTINGS_BLUETOOTHSWITCH_TEXT).getText();
            if(bluetooth){
                if(status.equals("Off")){
                    getObjectByText(SETTINGS_BLUETOOTHSWITCH_TEXT).click();
                }
            }else{
                if(status.equals("On")){
                    getObjectByText(SETTINGS_BLUETOOTHSWITCH_TEXT).click();
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    public void mobileData(boolean data){ //开启或者关闭流量
        try{
            String status =getObjectByClass(SETTINGS_WIDGET_SWITCH_CLASS).getText();
            if(data){
                if(status.equals("OFF")){
                    getLinearLayout(5,SETTINGS_STORAGE_RECYCLERVIEWER_CLASS,
                            SETTINGS_STORAGE_LINEARLAYOUT_CLASS).click();
                }
            }else{
                if(status.equals("ON")){
                    getLinearLayout(5,SETTINGS_STORAGE_RECYCLERVIEWER_CLASS,
                            SETTINGS_STORAGE_LINEARLAYOUT_CLASS).click();
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    public void switchFlightMode(boolean flightMode){ //开启或者关闭飞行模式
        try{
            String status =getLinearLayout(0,SETTINGS_WIDGET_LINEARLAYOUT_CLASS,
                    SETTINGS_WIDGET_SWITCH_CLASS).getText();
            if(flightMode){
                if(status.equals("OFF")){
                    getLinearLayout(0,SETTINGS_STORAGE_RECYCLERVIEWER_CLASS,
                            SETTINGS_STORAGE_LINEARLAYOUT_CLASS).click();
                }
            }else{
                if(status.equals("ON")){
                    getLinearLayout(0,SETTINGS_STORAGE_RECYCLERVIEWER_CLASS,
                            SETTINGS_STORAGE_LINEARLAYOUT_CLASS).click();
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void storageIsSD(boolean sdCard){ //设置手机的存储位置是否为SD
        try{
            if(getObjectByText(SETTINGS_STORAGE_SETTINGS_TEXT).exists()){
                if(sdCard){
                    getLinearLayout(2,SETTINGS_STORAGE_RECYCLERVIEWER_CLASS,
                            SETTINGS_STORAGE_LINEARLAYOUT_CLASS).click();
                }else{
                    getLinearLayout(1,SETTINGS_STORAGE_RECYCLERVIEWER_CLASS,
                            SETTINGS_STORAGE_LINEARLAYOUT_CLASS).click();
                }
            }else{
                System.out.println("The phone don't have the SD card ");
            }
        }catch(Exception e){e.printStackTrace();}
    }
    public void findSettingChildren(String childName){ //通过名字来找到列表中的选项
        try {
            while (!getObjectByTextContains(childName).exists()) {
                scrollByVerticalForward(25);
            }
            getObjectByTextContains(childName).clickAndWaitForNewWindow();
        } catch (Exception e) {e.printStackTrace();}
    }
    public void findSettingChildren(String id,String childName){//通过名字以及ID来找到列表中的选项
        try {
            while (!getObjectByIdText(id,childName).exists()) {
                scrollByVerticalForward(25);
            }
            getObjectByIdText(id,childName).clickAndWaitForNewWindow();
        } catch (Exception e) {e.printStackTrace();}
    }
    public void useNetworkTime(boolean status){ //选择是否使用网络提供的时间.true:是；
        try{
            if(status){
                if(getUiObjectByText(SETTINGS_SET_DATE_TEXT).isEnabled()){
                    getUiObjectByText(SETTINGS_AUTOMATIC_DATETIME_TEXT).click();
                }
            }else{
                if(!getUiObjectByText(SETTINGS_SET_DATE_TEXT).isEnabled()){
                    getUiObjectByText(SETTINGS_AUTOMATIC_DATETIME_TEXT).click();
                }
            }
        }catch(Exception e){e.printStackTrace();}
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
    public void setWeek(String specialWeek){ // 设置到指定星期的日期
        try{
            getUiObjectByText(SETTINGS_SET_DATE_TEXT).clickAndWaitForNewWindow();
            String[] weeks =getObjectById(SETTINGS_DATE_HEADER_DATE_ID).getText().split(",");
            String[] days =getObjectById(SETTINGS_DATE_HEADER_DATE_ID).getText().split(" ");
            int day =Integer.parseInt(days[1]);
            while(!specialWeek.contains(weeks[0])){
                day = day +1;
                String sDays = String.valueOf(day);
                getUiObjectByClassText(SETTING_DATE_DAY_VIEW_CLASS, sDays).click();
                String[] newWeeks =getObjectById(SETTINGS_DATE_HEADER_DATE_ID).getText().split(",");
                if(specialWeek.contains(newWeeks[0])){
                    break;
                }
            }
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
            while (!month[1].equals("Jan")) {
                getObjectById(SETTINGS_DATE_MONTH_PREV_ID).click();
                getUiObjectByClassText(SETTING_DATE_DAY_VIEW_CLASS,"1").click();
                String[] month1 = getObjectById(SETTINGS_DATE_HEADER_DATE_ID).getText().split(" ");
                month[1] = month1[1];
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
            useNetworkTime(false);
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
* Settings下的基本操作
    * */
    public void settingChild(String name){ //在Setting界面通过名字找到子列表，并进入
        findSettingChildren(name);
    }
    public void settingChild(String id,String name){ //在Setting界面通过名字与ID找到子列表，并进入
        findSettingChildren(id,name);
    }
    public void apps(){ //进入手机设置中的Apps
        settingChild(SETTINGS_TITLE_ID,SETTINGS_APPS_TEXT);
    }

    public void configureApps(){ //进入配置App信息界面
        try{
            if(getObjectByText(SETTINGS_APPS_TEXT).exists()){
                getObjectById(SETTINGS_APPS_ADVANCED_ID).clickAndWaitForNewWindow();
            }
        }catch(Exception e){e.printStackTrace();}
    }
    public void CheckConfigureApps(){
        Assert.assertTrue("Can't into the Configure apps",getObjectByText(SETTINGS_CONFIGURE_APPS_TEXT).exists());
    }
    public void appsInfo(int x){ //进入Apps下的第X个 配置App info界面
        try{
            if(getObjectByText(SETTINGS_APPS_TEXT).exists()){
                getLinearLayout(x-1,SETTINGS_WIDGET_LISTVIEW_CLASS,
                        SETTINGS_WIDGET_LINEARLAYOUT_CLASS).clickAndWaitForNewWindow();
            }
        }catch(Exception e){e.printStackTrace();}
    }
    public void CheckAppsInfo(){
        Assert.assertTrue("Can't into App info",getObjectByText(SETTINGS_APP_INFO_TEXT).exists());
    }
    public void CheckFlightModeOnInfo(boolean flightMode ){ //检查飞行模式下的提示信息是否存在
        try{
            openAppliction("Settings"); //找到设置应用
            if(flightMode){
                Assert.assertTrue("Flight mode info isn't on",getObjectByText(SETTINGS_FLIGHTMODE_ON_TEXT).exists());
                getObjectByText(SETTINGS_FLIGHTMODE_ON_TEXT).click();
                Assert.assertTrue("Don't have the Flight mode info ",getObjectByText(SETTINGS_FLIGHTMODE_TURNOFF_TEXT).exists());
                getObjectByText(SETTINGS_FLIGHTMODE_TURNOFF_TEXT).click();
            }else{
                Assert.assertFalse("Flight mode info isn't off",getObjectByText(SETTINGS_FLIGHTMODE_ON_TEXT).exists());
            }
        }catch(Exception e){e.printStackTrace();}
    }
    private void wifiStatus(boolean status){ //wifi状态
        try{
            if(status){
                Assert.assertTrue(1==1);
            }else{
                Assert.assertTrue("Wi-Fi isn't off",getObjectByText("OFF").exists());
            }
        }catch(Exception e){e.printStackTrace();}
    }
    private void bluetoothStatus(boolean status){ //飞行模式下蓝牙状态
        try{
            if(status){
                Assert.assertTrue(1==1);
            }else{
                Assert.assertTrue("Bluetooth isn't off",getObjectByText("OFF").exists());
            }
        }catch(Exception e){e.printStackTrace();}
    }
    private void mobileDataStatus(boolean status){ //数据流量状态
        try{
            boolean enabled =getLinearLayout(5,SETTINGS_STORAGE_RECYCLERVIEWER_CLASS,
                    SETTINGS_STORAGE_LINEARLAYOUT_CLASS).isEnabled();
            if(status){
                Assert.assertTrue("Mobiledata can't use",enabled);
            }else{
                Assert.assertTrue("Mobiledata can use",!enabled);
            }
        }catch(Exception e){e.printStackTrace();}
    }
    public void CheckStatus(boolean status){ //检查飞行模式下wifi，蓝牙，流量的状态
        try{
            getObjectByClass(SETTINGS_WIDGET_IMAGEBUTTON_CLASS).click();
            getObjectByText(SETTINGS_HOME_TEXT).clickAndWaitForNewWindow();
            settingChild("Wi‑Fi");
            wifiStatus(status);
            getObjectByClass(SETTINGS_WIDGET_IMAGEBUTTON_CLASS).click();
            getObjectByText(SETTINGS_HOME_TEXT).clickAndWaitForNewWindow();
            settingChild("Bluetooth");
            bluetoothStatus(status);
            getObjectByClass(SETTINGS_WIDGET_IMAGEBUTTON_CLASS).click();
            getObjectByText(SETTINGS_HOME_TEXT).clickAndWaitForNewWindow();
            settingChild("Data usage");
            mobileDataStatus(status);
        }catch(Exception e){e.printStackTrace();}

    }
    /*
* 一些基本的关于时间日期的设置
* */
    public void setSpecialTime(String time){ //去设置中设置一个时间
        openAppliction("Settings"); //找到设置应用
        settingChild(SETTINGS_DATE_TIME_TEXT); //找到时间设置
        useNetworkTime(false);//使用自定义时间
        setTime24(time);//设置手机时间
    }
    public void setSpecialDate(String date){ //去设置中设置一个日期
        openAppliction("Settings"); //找到设置应用
        settingChild(SETTINGS_DATE_TIME_TEXT); //找到时间设置
        useNetworkTime(false);//使用自定义时间
        setDate(date);//设置手机日期
    }
    public void setDateTime(String date,String time){ //去设置中设置日期与时间
        openAppliction("Settings"); //找到设置应用
        settingChild(SETTINGS_DATE_TIME_TEXT); //找到时间设置
        useNetworkTime(false);//使用自定义时间
        setDate(date);//设置手机日期
        setTime24(time);//设置手机时间
    }
    public void setNextdayTime(String time){ //去设置中设置下一天的时间
        openAppliction("Settings"); //找到设置应用
        settingChild(SETTINGS_DATE_TIME_TEXT); //找到时间设置
        useNetworkTime(false);//使用自定义时间
        setNextDay();//更改日期为下一天
        setTime24(time);//设置手机时间为18:21
    }
    public void setSpecialWeek(String week,String time){ //去设置中特定星期
        openAppliction("Settings"); //找到设置应用
        settingChild(SETTINGS_DATE_TIME_TEXT); //找到时间设置
        useNetworkTime(false);//使用自定义时间
        setWeek(week);//特定星期
        setTime24(time);//设置手机时间
    }
    public void setSpecialWeeks(String time,String week,double waitTime){ //去设置中特定星期(星期，时间，等待时间)
        setSpecialWeek(week,time);
        phoneWaitTime(waitTime);//手机等待分钟
        new AlarmAction().checkAlarmComing();//判断闹钟是否到来
        new AlarmAction().alarmComingStop();//闹钟到来后选择关闭闹钟
    }

    public void setEverydayTime(String time ,double waitTime){ //去设置中设置一周的时间
        for(int i=1;i<=7;i++){
            setNextdayTime(time);//设置手机时间
            phoneWaitTime(waitTime);//手机等待分钟
            new AlarmAction().checkAlarmComing();//判断闹钟是否到来
            new AlarmAction().alarmComingStop();//闹钟到来后选择关闭闹钟
        }
    }
    public void setStorageIsSD(boolean sdCard){ //设置默认存储位置是否为SD卡
        settingChild(SETTINGS_STORAGE_TEXT);//进入Storage
        storageIsSD(sdCard);
    }
    public void CheckStorageIsSD(boolean sdCard){ //检查默认设置是否为SD ，存储位置不是SD 就是手机
        try{
            if(sdCard){
                Assert.assertTrue("Default write disk isn't SD card",
                        !getObjectByClass(SETTINGS_STORAGE_RADIOBUTTON_CLASS).isChecked());
            }else{
                Assert.assertTrue("Default write disk isn't phone",
                        getObjectByClass(SETTINGS_STORAGE_RADIOBUTTON_CLASS).isChecked());
            }
        }catch(Exception e){e.printStackTrace();}

    }
}


