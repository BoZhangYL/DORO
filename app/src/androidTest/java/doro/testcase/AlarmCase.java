package doro.testcase;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.action.AlarmAction;
import doro.action.SettingAction;

/**
 * Created by user on 2016/12/06   .
 */
@RunWith(AndroidJUnit4.class)
public class AlarmCase extends VP4{
    AlarmAction alarmAction = new AlarmAction();
    SettingAction setAction = new SettingAction();
    @Test
    public void enterAndExitAlarm(){ //进入与退出闹钟界面
        openAppliction("Alarm"); //找到设置应用
        alarmAction.enterAndExitAlarm(5);
    }
    @Test
    public void creatZoneAlarm(){ //设置一个零点响起的闹钟
        openAppliction("Alarm"); //找到设置应用
        alarmAction.addTimeAlarm24("00:00");//建立一个时间为00:00的闹钟
        setAction.setSpecialTime("23:59");
        phoneWaitTime(5.5);//等待5分钟
        alarmAction.checkAlarmComing();//判断闹钟是否到来
        alarmAction.alarmComingStop();//闹钟到来后选择关闭闹钟
    }
    @Test
    public void creatYearAlarm(){ //建立一个跨日跨年的闹钟
        setAction.setDateTime("2013-12-31","23:59");//设置2013/12/31,设置手机时间为23:55
        openAppliction("Alarm"); //找到闹钟应用
        alarmAction.addTimeAlarm24("00:01");//建立一个时间为00:01的闹钟
        phoneWaitTime(5.5);//等待5分钟
        alarmAction.checkAlarmComing();//判断闹钟是否到来
        alarmAction.alarmComingStop();//闹钟到来后选择关闭闹钟
    }
    @Test
    public void caeatOneAlarm(){ //创建一个2分钟后的闹钟
        openAppliction("Alarm"); //找到闹钟应用
        alarmAction.addOneAlarm();//建立一个2分钟后的闹钟
        phoneWaitTime(2);//等待2分钟
        alarmAction.checkAlarmComing();//判断闹钟是否到来
        alarmAction.alarmComingStop();//闹钟到来后选择关闭闹钟
    }
    @Test
    public void repeatEveryAlarm(){ //创建一个重复三天的闹钟
        openAppliction("Alarm"); //找到闹钟设置应用
        alarmAction.addTimeWeekAlarm("18:22","Monday","Tuesday","Wednesday","Thursday",
                "Friday","Saturday","Sunday");
        setAction.setEverydayTime("18:21",2.5);
    }
    @Test
    public void deleteAllAlarm(){ //删除所有的闹钟
        openAppliction("Alarm"); //找到闹钟设置应用
        alarmAction.deleteAllAlarm();
        alarmAction.checkHasAlarm();
    }
    @Test
    public void deleteAOneAlarm(){ //删除一个闹钟
        openAppliction("Alarm"); //找到闹钟设置应用
        alarmAction.deleteOneAlarm();
    }
    @Test
    public void snoozeOneAlarm(){ //snooze 一个闹钟
        openAppliction("Alarm"); //找到闹钟设置应用
        alarmAction.addOneAlarm();//建立一个2分钟后的闹钟
        phoneWaitTime(2);//等待2分钟
        alarmAction.checkAlarmComing();//判断闹钟是否到来
        alarmAction.alarmComingSnooze();//闹钟到来后选择Snooze闹钟
        phoneWaitTime(5.2);//等待5.2分钟
        alarmAction.checkAlarmComing();//判断闹钟是否到来
        alarmAction.alarmComingStop();//闹钟到来后选择关闭闹钟
    }
    @Test
    public void stopOneAlarm(){ //stop 一个闹钟
        openAppliction("Alarm"); //找到闹钟设置应用
        alarmAction.addOneAlarm();//建立一个2分钟后的闹钟
        phoneWaitTime(2.5);//等待2分钟
        alarmAction.checkAlarmComing();//判断闹钟是否到来
        alarmAction.alarmComingStop();//闹钟到来后选择Snooze闹钟
        phoneWaitTime(5.5);//等待5.5分钟
        alarmAction.checkAlarmNotComing();//判断闹钟是否到来
    }
}
