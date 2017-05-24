package doro.testcase;

import android.support.test.runner.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import ckt.base.VP4;
import doro.action.AlarmAction;
import doro.action.SetAction;
import doro.action.SettingAction;

import static doro.page.AlarmPage.APPS_ICON_ALARM_TEXT;
/*
*
 * Created by user on 2016/12/06   .
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlarmCase extends VP4 {
    AlarmAction AlarmAction = new AlarmAction();
    SettingAction SettingAction = new SettingAction();
    SetAction SetAction = new SetAction();

    @Before
    public void unlock() {
        unLock();
    }

    @AfterClass
    public static void disableAlarm() {
        doro.action.AlarmAction.closeAlarm();
    }

    @Test
    public void enterAndExitAlarm() { //进入与退出闹钟界面
        openAppliction("Alarm"); //找到设置应用
        AlarmAction.enterAndExitAlarm(5);
    }

    @Test
    public void exitAlarm() { //退出闹钟界面
        openAppliction(APPS_ICON_ALARM_TEXT); //找到设置应用
        AlarmAction.exitAlarm();
    }

    @Test
    public void   creatZoneAlarm() { //设置一个零点响起的闹钟
        openAppliction(APPS_ICON_ALARM_TEXT); //找到设置应用
        AlarmAction.addTimeAlarm24("00:00");//建立一个时间为00:00的闹钟
        int SetAlarmTime = 60 * 60 * 24;//设置闹钟时间
        SetAction.setATime("23:59");
        AlarmAction.waitAlarmComing(SetAlarmTime);//等待闹钟到来
        AlarmAction.checkAlarmComing();//判断闹钟是否到来
        AlarmAction.alarmComingStop();//闹钟到来后选择关闭闹钟
    }

    @Test
    public void creatYearAlarm() { //建立一个跨日跨年的闹钟
        openAppliction(APPS_ICON_ALARM_TEXT); //找到闹钟应用
        AlarmAction.addTimeAlarm24("00:01");//建立一个时间为00:01的闹钟
        int SetAlarmTime = 60 * 60 * 24 + 60;
        SetAction.setADateAndTime("2013-12-31", "23:59");
        AlarmAction.waitAlarmComing(SetAlarmTime);
        AlarmAction.checkAlarmComing();//判断闹钟是否到来
        AlarmAction.alarmComingStop();//闹钟到来后选择关闭闹钟
    }

    @Test
    public void caeatOneAlarm() { //创建一个2分钟后的闹钟
        VP4.clearNoTifcation();
        AlarmAction.closeAutoTime();
        openAppliction(APPS_ICON_ALARM_TEXT); //找到闹钟应用
        int SetAlarmTime = AlarmAction.addOneAlarm();//建立一个2分钟后的闹钟
        AlarmAction.waitAlarmComing(SetAlarmTime);
        AlarmAction.checkAlarmComing();//判断闹钟是否到来
        AlarmAction.alarmComingStop();//闹钟到来后选择关闭闹钟
    }

    @Test
    public void repeatThreeAlarm() { //创建一个重复三天的闹钟
        openAppliction(APPS_ICON_ALARM_TEXT); //找到闹钟设置应用
        AlarmAction.addTimeWeekAlarm("18:21", null, "Tuesday", null, "Thursday",
                null, "Saturday", null);
        int SetAlarmTime = 18 * 60 * 60 + 21 * 60;
        SettingAction.setSpecialWeeks("18:20", "Tuesday", SetAlarmTime);
        SettingAction.setSpecialWeeks("18:20", "Thursday", SetAlarmTime);
        SettingAction.setSpecialWeeks("18:20", "Saturday", SetAlarmTime);
    }

    @Test
    public void repeatEveryAlarm() { //创建一个每天重复的闹钟
        openAppliction(APPS_ICON_ALARM_TEXT); //找到闹钟设置应用
        AlarmAction.addTimeWeekAlarm("18:21", "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday");
        int SetAlarmTime = 18 * 60 * 60 + 21 * 60;
        SettingAction.setEverydayTime("18:20",SetAlarmTime );
    }

    @Test
    public void deleteAllAlarm() { //删除所有的闹钟
        openAppliction(APPS_ICON_ALARM_TEXT); //找到闹钟设置应用
        AlarmAction.deleteAllAlarm();
        AlarmAction.checkHasAlarm();
    }

    @Test
    public void deleteAOneAlarm() { //删除一个闹钟
        openAppliction(APPS_ICON_ALARM_TEXT); //找到闹钟设置应用
        AlarmAction.deleteOneAlarm();
    }

    @Test
    public void snoozeOneAlarm() { //snooze 一个闹钟
        openAppliction(APPS_ICON_ALARM_TEXT); //找到闹钟设置应用
        int SetAlarmTime = AlarmAction.addOneAlarm();//建立一个2分钟后的闹钟
        AlarmAction.waitAlarmComing(SetAlarmTime);
        AlarmAction.checkAlarmComing();//判断闹钟是否到来
        AlarmAction.alarmComingSnooze();//闹钟到来后选择Snooze闹钟
        waitTime(300);
        AlarmAction.checkAlarmComing();//判断闹钟是否到来
        AlarmAction.alarmComingStop();//闹钟到来后选择关闭闹钟
    }

    @Test
    public void stopOneAlarm() { //stop 一个闹钟
        openAppliction(APPS_ICON_ALARM_TEXT); //找到闹钟设置应用
        int SetAlarmTime = AlarmAction.addOneAlarm();//建立一个2分钟后的闹钟
        AlarmAction.waitAlarmComing(SetAlarmTime);
        AlarmAction.checkAlarmComing();//判断闹钟是否到来
        AlarmAction.alarmComingStop();//闹钟到来后选择Snooze闹钟
        waitTime(300);
        AlarmAction.checkAlarmNotComing();//判断闹钟是否到来
    }
}
