package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import ckt.base.VP4;
import doro.action.CalendarAction;
import static ckt.base.VP3.takeScreen;
import static doro.page.CalednarPage.*;
import static org.junit.Assert.assertEquals;


/**
 * Created by bo.zhang on 2016/12/02   .
 */
@RunWith(AndroidJUnit4.class)
public class CalendarCase extends VP4{
    @BeforeClass
    public static void initCalendar(){
        initDevice();
        unLock();
    }

    @Test
    public void launchCalendarCase1() {
        //D8040-1228:进入日历查看日历 [View calender enter calender applictaion]

        openAppliction("Calen\u200Bdar");
        assertEquals("launchCalendarCase1",
                CALENDAR_PACKAGE,gDevice.getCurrentPackageName());
    }

    @Test
    public void createCalendarEventBeforeCase2() throws UiObjectNotFoundException {
        //D8040-1244:提前5分钟提醒 [Reminder at 5 minutes in advance]

        openAppliction("Calen\u200Bdar");
        CalendarAction newCalendar =new CalendarAction("my new Calendar","Home",null,null,
                null,null,null,null,null,CALENDAR_BEFORE_VALUE_5_MINUTES);
        newCalendar.addEvent();
        assertEquals("Calendar title","my new Calendar",
                getObjectById(CALENDAR_EVENT_TITLE_ID).getText());
        assertEquals("createEventBeforeCase2","Home",
                getObjectById(CALENDAR_LOCATION_ID).getText());
    }
    @Test
    public void checkCalendarGreenPointsCase3(){
        // D8040-1252:添加事件后日期上方有绿点 [The date icon top displays the green points after add event]
        openAppliction("Calen\u200Bdar");
        String newpath = takeScreen("calendar");
        CalendarAction newCalendar =new CalendarAction();
        boolean existGreenPoint = newCalendar.
                isCalendarGreenPoint(getObjectByIdText(CALENDAR_DAY_LABEL,
                        String.valueOf(newCalendar.getCurrentDay())), newpath);
        Assert.assertTrue("checkCalendarGreenPointsCase3",existGreenPoint);
    }


}
