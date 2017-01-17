package doro.testcase;

import org.junit.Test;

import doro.action.SetAction;

import static doro.page.SetPage.SET_ALARM_VALUE_ID;
import static doro.page.SetPage.SET_AUDIOSETUP_HACMODE_TEXT;
import static doro.page.SetPage.SET_AUDIOSETUP_HIGH_TEXT;
import static doro.page.SetPage.SET_AUDIOSETUP_NORMAL_TEXT;
import static doro.page.SetPage.SET_MEDIA_VALUE_ID;
import static doro.page.SetPage.SET_RINGTONE_VALUE_ID;
import static doro.page.SetPage.SET_TEXTSIZE_EXTRALARGE_TEXT;

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
}
