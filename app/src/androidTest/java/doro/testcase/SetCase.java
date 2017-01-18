package doro.testcase;

import org.junit.Test;

import doro.action.SetAction;

import static doro.page.SetPage.SET_ALARM_VALUE_ID;
import static doro.page.SetPage.SET_AUDIOSETUP_HACMODE_TEXT;
import static doro.page.SetPage.SET_AUDIOSETUP_HIGH_TEXT;
import static doro.page.SetPage.SET_AUDIOSETUP_NORMAL_TEXT;
import static doro.page.SetPage.SET_DATE_AND_TIME_TEXT;
import static doro.page.SetPage.SET_GENERAL_OPTION_TEXT;
import static doro.page.SetPage.SET_MEDIA_VALUE_ID;
import static doro.page.SetPage.SET_RINGTONE_VALUE_ID;
import static doro.page.SetPage.SET_TEXTSIZE_EXTRALARGE_TEXT;
import static doro.page.SetPage.SET_TIME_FORMAT_12_TEXT;
import static doro.page.SetPage.SET_TIME_FORMAT_24_TEXT;

/**
 * Created by user on 2017/01/11   .
 */

public class SetCase {
    SetAction setAction = new SetAction();
    @Test
    public void setAudioSetup(){
        setAction.findSet();
        setAction.audioSetup(SET_AUDIOSETUP_NORMAL_TEXT); //设置AudioSetup模式为normal
        setAction.checkAudioSetup(SET_AUDIOSETUP_NORMAL_TEXT); //检查是否为normal模式
        setAction.audioSetup(SET_AUDIOSETUP_HIGH_TEXT ); //设置AudioSetup模式为High
        setAction.checkAudioSetup(SET_AUDIOSETUP_HIGH_TEXT );  //检查是否为High模式
        setAction.audioSetup(SET_AUDIOSETUP_HACMODE_TEXT);  //设置AudioSetup模式为HAC mode
        setAction.checkAudioSetup(SET_AUDIOSETUP_HACMODE_TEXT);  //检查是否为HAC mode模式
    }
    @Test
    public void setAlarmVolume(){ //调节闹钟的音量
        setAction.findSet();
        setAction.setAlarmVolume(8);
        setAction.checkVolume(8,SET_ALARM_VALUE_ID);
    }
    @Test
    public void setMediaVolume(){ //调节多媒体的音量
        setAction.findSet();
        setAction.setMediaVolume(8);
        setAction.checkVolume(8,SET_MEDIA_VALUE_ID);
    }
    @Test
    public void setRingtoneVolume(){ //调节铃声的音量
        setAction.findSet();
        setAction.setRingtoneVolume(8);
        setAction.checkVolume(8,SET_RINGTONE_VALUE_ID);
    }
    @Test
    public void setTextSize(){ //设置文字大小,暂时没有判断是否设置成功
        setAction.findSet();
        setAction.textSize(SET_TEXTSIZE_EXTRALARGE_TEXT);
    }
    @Test
    public void setScreenTimeout(){
        setAction.findSet();
        setAction.screenTimeout("15 seconds");
        setAction.checkScreenTimeout(0.25);
    }
    @Test
    public void setAutoTime(){ // 自动时间
        setAction.findSet();//找到设置
        setAction.findListSubmenu(SET_GENERAL_OPTION_TEXT);
        setAction.findListSubmenu(SET_DATE_AND_TIME_TEXT);//找到The date and time
        setAction.autoTime(true);
    }
    @Test
    public void setTimeFormat12(){
        setAutoTime();
        setAction.setTimeFormat(SET_TIME_FORMAT_12_TEXT);//采用12小时制
        setAction.checkTimeFormat(12);
    }
    @Test
    public void setTimeFormat24(){
        setAutoTime();
        setAction.setTimeFormat(SET_TIME_FORMAT_24_TEXT);//采用24小时制
        setAction.checkTimeFormat(24);
    }
}
