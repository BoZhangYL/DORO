package doro.action;

import junit.framework.Assert;

import ckt.base.VP4;

import static doro.page.SetPage.SET_ALARM_VALUE_ID;
import static doro.page.SetPage.SET_AN_AUDIO_OPTION_TEXT;
import static doro.page.SetPage.SET_AUTOTIME_CHECKVIEW_ID;
import static doro.page.SetPage.SET_CHANGE_GALLERY_TEXT;
import static doro.page.SetPage.SET_CONFIRM_TEXT;
import static doro.page.SetPage.SET_DATE_AND_TIME_TEXT;
import static doro.page.SetPage.SET_DATE_FILED_ID;
import static doro.page.SetPage.SET_DAY_DECREASE_ID;
import static doro.page.SetPage.SET_DAY_INCREASE_ID;
import static doro.page.SetPage.SET_DISPLAY_OPTION_TEXT;
import static doro.page.SetPage.SET_FORMAT_EDIT_ID;
import static doro.page.SetPage.SET_FORMAT_INCREASE_ID;
import static doro.page.SetPage.SET_GENERAL_OPTION_TEXT;
import static doro.page.SetPage.SET_HOUR_DECREASE_ID;
import static doro.page.SetPage.SET_HOUR_EDIT_ID;
import static doro.page.SetPage.SET_HOUR_INCREASE_ID;
import static doro.page.SetPage.SET_ICON_SET_TEXT;
import static doro.page.SetPage.SET_MEDIA_VALUE_ID;
import static doro.page.SetPage.SET_MINUS_ALARM_ID;
import static doro.page.SetPage.SET_MINUS_MEDIA_ID;
import static doro.page.SetPage.SET_MINUS_RINGTONE_ID;
import static doro.page.SetPage.SET_MINUTE_DECREASE_ID;
import static doro.page.SetPage.SET_MINUTE_EDIT_ID;
import static doro.page.SetPage.SET_MINUTE_INCREASE_ID;
import static doro.page.SetPage.SET_MONTH_DECREASE_ID;
import static doro.page.SetPage.SET_MONTH_INCREASE_ID;
import static doro.page.SetPage.SET_MYHOMESCREEN_TEXT;
import static doro.page.SetPage.SET_MY_AUDIO_SETUP_TEXT;
import static doro.page.SetPage.SET_MY_WALLPAPER_TEXT;
import static doro.page.SetPage.SET_PLUS_ALARM_ID;
import static doro.page.SetPage.SET_PLUS_MEDIA_ID;
import static doro.page.SetPage.SET_PLUS_RINGTONE_ID;
import static doro.page.SetPage.SET_RINGTONE_VALUE_ID;
import static doro.page.SetPage.SET_SCREEN_BRIGHTNESS_TEXT;
import static doro.page.SetPage.SET_SCREEN_TIMEOUT_TEXT;
import static doro.page.SetPage.SET_SCREEN_TIMEOUT_TWO_TEXT;
import static doro.page.SetPage.SET_SELECT_TEXT_SIZE_TEXT;
import static doro.page.SetPage.SET_SUPPORT_RECYCLERVIEW_CLASS;
import static doro.page.SetPage.SET_TEXTSIZE_EXTRALARGE_TEXT;
import static doro.page.SetPage.SET_TEXTSIZE_LARGE_TEXT;
import static doro.page.SetPage.SET_TEXTSIZE_NORMAL_TEXT;
import static doro.page.SetPage.SET_TEXT_SIZE_TEXT;
import static doro.page.SetPage.SET_THE_VOLUME_SETUP_TEXT;
import static doro.page.SetPage.SET_TIMEFORMAT_FIELD_ID;
import static doro.page.SetPage.SET_TIME_FILED_ID;
import static doro.page.SetPage.SET_TONE_SETUP_BTN_ID;
import static doro.page.SetPage.SET_WALLPAPER_CHANGE_TEXT;
import static doro.page.SetPage.SET_WALLPAPER_RESET_TEXT;
import static doro.page.SetPage.SET_WGRIDVIEW_CLASS;
import static doro.page.SetPage.SET_WIDGET_FRAMELAYOUT_CLASS;
import static doro.page.SetPage.SET_YEAR_DECREASE_ID;
import static doro.page.SetPage.SET_YEAR_INCREASE_ID;

/**
 * Created by user on 2017/01/12   .
 */

public class SetAction extends VP4{
    public void findSet(){
        try{
            VP4.unLock();
            switchToMenuPage();//找到快捷菜单界面
            getObjectByText("Set").clickAndWaitForNewWindow();
            //getLinearLayout(8,SET_SUPPORT_RECYCLERVIEW_CLASS,SET_WIDGET_FRAMELAYOUT_CLASS).click();
            //点击"Set"快捷键
        }catch(Exception e){e.printStackTrace();}
    }
    public void findListSubmenu(String Submenu1){ //找到set下的子菜单
        try{
            while(!getObjectByText(Submenu1).exists()){
                scrollByVerticalForward(40);
            }
            getObjectByText(Submenu1).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void wallpaperChange(int x){ //设置背景壁纸
        try{
            int y=x*2-1;
            getObjectByText(SET_WALLPAPER_CHANGE_TEXT).clickAndWaitForNewWindow();
            getObjectByText(SET_CHANGE_GALLERY_TEXT).clickAndWaitForNewWindow();
            getLinearLayout(y,SET_WGRIDVIEW_CLASS,SET_WIDGET_FRAMELAYOUT_CLASS).clickAndWaitForNewWindow();
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
            Thread.sleep(3);
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void wallpaperReset(){ //将壁纸重置成默认设置
        try{
            getObjectByText(SET_WALLPAPER_RESET_TEXT).clickAndWaitForNewWindow();
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}

    }
    public void minusAndPlus(String value,int x,String minus,String plus ){ //调节Set菜单下音频设置的音量
        try{
            String alarmValue =getObjectById(value).getText();
            int alarmValues =Integer.valueOf(alarmValue);
            while(alarmValues!=15){
                alarmValues = alarmValues +1;
                getObjectById(plus).click();
            }
            for(alarmValues=15;x<alarmValues;alarmValues--){
                getObjectById(minus).click();
            }
        }catch(Exception e){e.printStackTrace();}
    }
    public void audioSetup(String audioSetupMode){ //设置音频模式
        findListSubmenu(SET_AN_AUDIO_OPTION_TEXT);
        findListSubmenu(SET_MY_AUDIO_SETUP_TEXT);
        try{
            getObjectById(SET_TONE_SETUP_BTN_ID).clickAndWaitForNewWindow();
            getObjectByText(audioSetupMode).clickAndWaitForNewWindow();
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByText(SET_ICON_SET_TEXT).click();
        }catch(Exception e){e.printStackTrace();}
    }
    public void textSize(String textSize){ //设置 字体大小
        findListSubmenu(SET_DISPLAY_OPTION_TEXT);
        findListSubmenu(SET_TEXT_SIZE_TEXT);
        try{
            getObjectById(SET_TONE_SETUP_BTN_ID).clickAndWaitForNewWindow();
            getObjectByText(textSize).clickAndWaitForNewWindow();
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByText(SET_TEXT_SIZE_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    private int textSizeLog(String name){ //得到name的中心X坐标
        int x =0;
        try{
            x =getObjectByText(name).getBounds().centerX();
        }catch(Exception e){e.printStackTrace();}
        return x;
    }
    public void CheckTextSizeLog(){ //检查字体大小是否改变
        try{
            textSize(SET_TEXTSIZE_EXTRALARGE_TEXT);
            int x1 =textSizeLog(SET_SELECT_TEXT_SIZE_TEXT);
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByText(SET_ICON_SET_TEXT).click();
            textSize(SET_TEXTSIZE_LARGE_TEXT);
            int x2 =textSizeLog(SET_SELECT_TEXT_SIZE_TEXT);
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByText(SET_ICON_SET_TEXT).click();
            textSize(SET_TEXTSIZE_NORMAL_TEXT);
            int x3 =textSizeLog(SET_SELECT_TEXT_SIZE_TEXT);
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByText(SET_ICON_SET_TEXT).click();
            Assert.assertTrue("The text size can't change",x3<x2&&x2<x1);
        }catch(Exception e){e.printStackTrace();}

    }
    public void screenTimeout(String time){ //设置屏幕待机时间
        findListSubmenu(SET_GENERAL_OPTION_TEXT);
        findListSubmenu(SET_SCREEN_TIMEOUT_TEXT);
        try{
            getObjectById(SET_TONE_SETUP_BTN_ID).clickAndWaitForNewWindow();
            getObjectByText(time).clickAndWaitForNewWindow();
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByText(SET_ICON_SET_TEXT).click();
        }catch(Exception e){e.printStackTrace();}
    }
    public void autoTime(boolean yesOrNo){ //是否采用 自动时间
        waitTime(10);
        try{
            if(yesOrNo){
                if(!getObjectById(SET_AUTOTIME_CHECKVIEW_ID).isChecked()){
                    getObjectById(SET_AUTOTIME_CHECKVIEW_ID).click();
                }
            }else{
                if(getObjectById(SET_AUTOTIME_CHECKVIEW_ID).isChecked()){
                    getObjectById(SET_AUTOTIME_CHECKVIEW_ID).click();
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    public void setTimeFormat(String timeFormat){ //设置时间格式
        //timeFormat_12小时制:SET_TIME_FORMAT_12_TEXT || timeFormat_24小时制:SET_TIME_FORMAT_24_TEXT
        try{
            getObjectById(SET_TIMEFORMAT_FIELD_ID).clickAndWaitForNewWindow();
            getObjectByText(timeFormat).clickAndWaitForNewWindow();
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void setDate(String date){ //设置日期，格式：YYYY-MM-DD
        autoTime(false);
        String[] dates=date.split("-");
        try{
            String phoneDate =getObjectById(SET_DATE_FILED_ID).getText();
            String[] phoneDates =phoneDate.split("/");
            int clickY =Integer.valueOf(dates[0]) - Integer.valueOf("20"+phoneDates[2]);
            int clickM =Integer.valueOf(dates[1]) - Integer.valueOf(phoneDates[1]);
            int clickD =Integer.valueOf(dates[2]) - Integer.valueOf(phoneDates[0]);
            getObjectById(SET_DATE_FILED_ID).clickAndWaitForNewWindow();
            if(clickY<0){
                for(int x=0;x<-clickY;x++){
                    getObjectById(SET_YEAR_DECREASE_ID).click();
                }
            }else{
                for(int y=0;y<clickY;y++){
                    getObjectById(SET_YEAR_INCREASE_ID).click();
                }
            }
            if(clickM<0){
                for(int x=0;x<-clickM;x++) {
                    getObjectById(SET_MONTH_DECREASE_ID).click();
                }
            }else{
                for(int y=0;y<clickM;y++) {
                    getObjectById(SET_MONTH_INCREASE_ID).click();
                }
            }
            if(clickD<0){
                for(int x=0;x<-clickD;x++) {
                    getObjectById(SET_DAY_DECREASE_ID).click();
                }
            }else{
                for(int y=0;y<clickD;y++) {
                    getObjectById(SET_DAY_INCREASE_ID).click();
                }
            }
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void setNextDay(){ //设置当前日期的下一天
        findTimeAndate();
        autoTime(false);
        try{
            getObjectById(SET_DATE_FILED_ID).clickAndWaitForNewWindow();
            getObjectById(SET_DAY_INCREASE_ID).click();
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    private void aMpM(String time){ //时间下选择AM 还是PM
        try{
            if(!getObjectById(SET_FORMAT_EDIT_ID).getText().equals(time)){
                getObjectById(SET_FORMAT_INCREASE_ID).click();
            }
        }catch(Exception e){e.printStackTrace();}
    }
    private void hourAndMin(int hours,int mins){
        try{
                if(hours>0){
                    for(int i=0;i<hours;i++){
                        getObjectById(SET_HOUR_INCREASE_ID).click();
                    }
                }else{
                    for(int j=0;j<-hours;j++){
                        getObjectById(SET_HOUR_DECREASE_ID).click();
                    }
                }
                if(mins>0){
                    for(int i=0;i<mins;i++){
                        getObjectById(SET_MINUTE_INCREASE_ID).click();
                    }
                }else{
                    for(int j=0;j<-mins;j++){
                        getObjectById(SET_MINUTE_DECREASE_ID).click();
                    }
                }
        }catch(Exception e){e.printStackTrace();}
    }
    public void setTime(String time){ //设置时间，时间格式：HH：MM
        autoTime(false);
        String[] times = time.split(":");
        int hour =Integer.valueOf(times[0]);
        int min =Integer.valueOf(times[1]);
        try{
           getObjectById(SET_TIME_FILED_ID).clickAndWaitForNewWindow();
            int phoneHour =Integer.valueOf(getObjectById(SET_HOUR_EDIT_ID).getText());
            int phoneMin =Integer.valueOf(getObjectById(SET_MINUTE_EDIT_ID).getText());
            if(getObjectById(SET_FORMAT_EDIT_ID).exists()){
                if(hour>12){
                    aMpM("PM");
                    hourAndMin(hour-phoneHour-12,min-phoneMin);
                }else{
                    aMpM("AM");
                    hourAndMin(hour-phoneHour,min-phoneMin);
                }
            }else{
                hourAndMin(hour-phoneHour,min-phoneMin);
            }
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    /*
    * Doro设置下的一些基本操作
    * */
    public void findWallpaper(){ //找到Wallpaper
        try{
            getObjectByText(SET_ICON_SET_TEXT).click();
            findListSubmenu(SET_MYHOMESCREEN_TEXT);
        }catch(Exception e){e.printStackTrace();}
    }
    public void CheckWallpaper(){ //检查Wallpaper
        Assert.assertTrue("Don't find the Wallpaper",getObjectByText(SET_MY_WALLPAPER_TEXT).exists());
    }
    public void findBrightness(){ //找到Brightnessh和text size
        try{
            getObjectByText(SET_ICON_SET_TEXT).click();
            findListSubmenu(SET_DISPLAY_OPTION_TEXT);
        }catch(Exception e){e.printStackTrace();}
    }
    public void CheckBrightness(){ //检查Brightness
        Assert.assertTrue("Don't find the Brightness",getObjectByText(SET_SCREEN_BRIGHTNESS_TEXT).exists());
    }
    public void CheckTextSize(){ //检查text size
        Assert.assertTrue("Don't find the text size",getObjectByText(SET_TEXT_SIZE_TEXT).exists());
    }
    public void findScreenTimeout(){ //找到ScreenTimeout
        try{
            getObjectByText(SET_ICON_SET_TEXT).click();
        }catch(Exception e){e.printStackTrace();}
        findListSubmenu(SET_GENERAL_OPTION_TEXT);
        findListSubmenu(SET_SCREEN_TIMEOUT_TEXT);
    }
    public void CheckScreenTimeout(){ //检查ScreenTimeout
        Assert.assertTrue("Don't find the Screen Timeout",getObjectByText(SET_SCREEN_TIMEOUT_TWO_TEXT).exists());
    }
    public void setWallpaper(int x){ //设置第x张图片为壁纸
        findListSubmenu(SET_MYHOMESCREEN_TEXT);
        findListSubmenu(SET_MY_WALLPAPER_TEXT);
        wallpaperChange(x);
    }
    public void resetWallpaper(){ //重置壁纸
        findListSubmenu(SET_MYHOMESCREEN_TEXT);
        findListSubmenu(SET_MY_WALLPAPER_TEXT);
        wallpaperReset();
    }
    public void checkAudioSetup(String audioSetupMode){ //检查音频设置是否为指定的模式
        findListSubmenu(SET_AN_AUDIO_OPTION_TEXT);
        findListSubmenu(SET_MY_AUDIO_SETUP_TEXT);
        try{
            Assert.assertTrue("Setting"+audioSetupMode+"mode can't success",getObjectByText(audioSetupMode).exists());
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByText(SET_ICON_SET_TEXT).click();
        }catch(Exception e){e.printStackTrace();}
    }
    public void setAlarmVolume(int x){ //设置闹钟的音量
        findListSubmenu(SET_AN_AUDIO_OPTION_TEXT);
        findListSubmenu(SET_THE_VOLUME_SETUP_TEXT);
        minusAndPlus(SET_ALARM_VALUE_ID,x,SET_MINUS_ALARM_ID,SET_PLUS_ALARM_ID);
        try{
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void setMediaVolume(int x){ //设置多媒体的音量
        findListSubmenu(SET_AN_AUDIO_OPTION_TEXT);
        findListSubmenu(SET_THE_VOLUME_SETUP_TEXT);
        minusAndPlus(SET_MEDIA_VALUE_ID,x,SET_MINUS_MEDIA_ID,SET_PLUS_MEDIA_ID);
        try{
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void setRingtoneVolume(int x){ //设置铃声的音量
        findListSubmenu(SET_AN_AUDIO_OPTION_TEXT);
        findListSubmenu(SET_THE_VOLUME_SETUP_TEXT);
        minusAndPlus(SET_RINGTONE_VALUE_ID,x,SET_MINUS_RINGTONE_ID,SET_PLUS_RINGTONE_ID);
        try{
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void checkVolume(int x,String valueString){ //检查音量
        try{
            getObjectByText(SET_THE_VOLUME_SETUP_TEXT).clickAndWaitForNewWindow();
            String y =getObjectById(valueString).getText();
            int z =Integer.valueOf(y);
            Assert.assertTrue("The volume can't change successfully",x==z);
        }catch(Exception e){e.printStackTrace();}
    }
    public void checkScreenTimeout(double min){ //检查屏幕是否灭屏
        try{
            mDevice.sleep();
            Thread.sleep(5);
            mDevice.wakeUp();
            unLock();
            phoneWaitTime(min);
            Assert.assertFalse("The screen is still wake ",mDevice.isScreenOn());
            mDevice.wakeUp();
            unLock();
        }catch(Exception e){e.printStackTrace();}
    }
    private void findTimeAndate(){
        findSet();//找到设置
        findListSubmenu(SET_GENERAL_OPTION_TEXT);
        findListSubmenu(SET_DATE_AND_TIME_TEXT);//找到The date and time
    }
    public void setATime(String time){ //去设置一个指定时间
        findTimeAndate();
        setTime(time);
        try{
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void setADate(String date){//去指定一个日期
        findTimeAndate();
        setDate(date);
        try{
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void setNextDayTime(String time){ //设置下一天的时间
        setNextDay();
        setTime(time);
        try{
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void setADateAndTime(String date,String time){//去指定一个日期与时间
        findTimeAndate();
        setDate(date);
        setTime(time);
        try{
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void checkTimeFormat(int timeformat){ //检查手机设置的时间格式
        try{
            getObjectByText(SET_DATE_AND_TIME_TEXT).clickAndWaitForNewWindow();
            autoTime(false);
            boolean format = getObjectById(SET_TIME_FILED_ID).getText().contains("m");
            if(timeformat ==12){
                Assert.assertTrue("The time format can't change to 12-hour format",format);
            }else{
                Assert.assertFalse("The time format can't change to 24-hour format",format);
            }
        }catch(Exception e){e.printStackTrace();}
    }
}
