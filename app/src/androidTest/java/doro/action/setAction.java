package doro.action;

import junit.framework.Assert;

import ckt.base.VP4;

import static doro.page.SetPage.SET_ALARM_VALUE_ID;
import static doro.page.SetPage.SET_AN_AUDIO_OPTION_TEXT;
import static doro.page.SetPage.SET_CONFIRM_TEXT;
import static doro.page.SetPage.SET_DISPLAY_OPTION_TEXT;
import static doro.page.SetPage.SET_GENERAL_OPTION_TEXT;
import static doro.page.SetPage.SET_ICON_SET_TEXT;
import static doro.page.SetPage.SET_MEDIA_VALUE_ID;
import static doro.page.SetPage.SET_MINUS_ALARM_ID;
import static doro.page.SetPage.SET_MINUS_MEDIA_ID;
import static doro.page.SetPage.SET_MINUS_RINGTONE_ID;
import static doro.page.SetPage.SET_MY_AUDIO_SETUP_TEXT;
import static doro.page.SetPage.SET_PLUS_ALARM_ID;
import static doro.page.SetPage.SET_PLUS_MEDIA_ID;
import static doro.page.SetPage.SET_PLUS_RINGTONE_ID;
import static doro.page.SetPage.SET_RINGTONE_VALUE_ID;
import static doro.page.SetPage.SET_SCREEN_TIMEOUT_TEXT;
import static doro.page.SetPage.SET_SUPPORT_RECYCLERVIEW_CLASS;
import static doro.page.SetPage.SET_TEXT_SIZE_TEXT;
import static doro.page.SetPage.SET_THE_VOLUME_SETUP_TEXT;
import static doro.page.SetPage.SET_TONE_SETUP_BTN_ID;
import static doro.page.SetPage.SET_WIDGET_FRAMELAYOUT_CLASS;

/**
 * Created by user on 2017/01/12   .
 */

public class SetAction extends VP4{
    public void findSet(){
        try{
            switchToMenuPage();//找到快捷菜单界面
            getLinearLayout(8,SET_SUPPORT_RECYCLERVIEW_CLASS,SET_WIDGET_FRAMELAYOUT_CLASS).click();
            //点击"Set"快捷键
        }catch(Exception e){e.printStackTrace();}
    }
    public void findListSubmenu(String Submenu1){
        try{
            while(!getObjectByText(Submenu1).exists()){
                scrollByVerticalForward(40);
            }
            getObjectByText(Submenu1).clickAndWaitForNewWindow();
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
        try{
            findListSubmenu(SET_TEXT_SIZE_TEXT);
            getObjectById(SET_TONE_SETUP_BTN_ID).clickAndWaitForNewWindow();
            getObjectByText(textSize).clickAndWaitForNewWindow();
            getObjectByText(SET_CONFIRM_TEXT).clickAndWaitForNewWindow();
            getObjectByText(SET_ICON_SET_TEXT).click();
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
    /*
    * Doro设置下的一些基本操作
    * */
    public void checkAudioSetup(String audioSetupMode){ //检查音频设置是否为指定的模式
        findListSubmenu(SET_AN_AUDIO_OPTION_TEXT);
        findListSubmenu(SET_MY_AUDIO_SETUP_TEXT);
        try{
            Assert.assertTrue("设置"+audioSetupMode+"模式没有成功",getObjectByText(audioSetupMode).exists());
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
            Assert.assertTrue("音量没有调节成功",x==z);
        }catch(Exception e){e.printStackTrace();}
    }
    public void checkScreenTimeout(double min){
        try{
            mDevice.sleep();
            Thread.sleep(5);
            mDevice.wakeUp();
            unLock();
            phoneWaitTime(min);
            Assert.assertFalse("屏幕没有关闭",mDevice.isScreenOn());
        }catch(Exception e){e.printStackTrace();}

    }
}