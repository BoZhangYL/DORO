package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.action.CalendarAction;

import static doro.action.CalendarAction.checkCalendarGreenPoint;
import static doro.action.CalendarAction.checkCreateBeforCalendar;
import static doro.action.CalendarAction.checkOpenCalendarResult;
import static doro.action.CalendarAction.createNewCalendar;
import static doro.action.CalendarAction.setCalendar;
import static doro.action.CalendarAction.setCalendarBefore;
import static doro.page.CalednarPage.*;


/**
 * Created by bo.zhang on 2016/12/02   .
 */
@RunWith(AndroidJUnit4.class)
public class CalendarCase extends VP4 {
    @BeforeClass
    public static void initCalendar() {
        unLock();//解锁
    }

    @Test
    public void openCalendarFromAppList() {
        //D8040-1228:进入日历查看日历 [View calender enter calender applictaion]

        openAppliction("Calen\u200Bdar");//打开日历应用
        checkOpenCalendarResult();//检查打开是否正常
    }

    @Test
    public void CreateBeforeFiveMinEvent() throws UiObjectNotFoundException {
        //D8040-1244:提前5分钟提醒 [Reminder at 5 minutes in advance]

        openAppliction("Calen\u200Bdar");//打开日历应用
        setCalendarBefore(CALENDAR_BEFORE_VALUE_5_MINUTES);//设置提醒提前5分钟开始
        CalendarAction.setCalednarTitle("Event_before_5");
        createNewCalendar();//添加新的calendar
        checkCreateBeforCalendar();//检查添加是否成功
    }

    @Test
    public void checkCalendarGreenPoints() {
        // D8040-1252:添加事件后日期上方有绿点 [The date icon top displays the green points after add event]

        openAppliction("Calen\u200Bdar");//打开日历应用
        setCalendar("CheckGreenPoint", "office", null, null, CALENDAR_ALL_DAY_DISABLE,
                null, null, CALENDAR_RECURRENCE_NONE, CALENDAR_REMINDER_TYPE_NOTIFICATION,
                CALENDAR_BEFORE_VALUE_5_MINUTES);//设置日历数据
        createNewCalendar();//添加一个新日历
        checkCalendarGreenPoint();//检查绿点是否存在
    }

}
