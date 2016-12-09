package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import com.squareup.spoon.Spoon;
import org.junit.Test;
import org.junit.runner.RunWith;
import ckt.base.VP4;
import doro.action.CalendarAction;

import static doro.page.CalednarPage.*;

/**
 * Created by bo.zhang on 2016/12/02   .
 */
@RunWith(AndroidJUnit4.class)
public class CalendarCase extends VP4 {

    @Test
    public void launchCalendarCase1() {//打开Calendar应用
        openAppliction("Calen\u200Bdar");
    }

    @Test
    public void createEventBeforeCase2(){//创建提前5分钟提醒事件
        openAppliction("Calen\u200Bdar");
//       CalendarAction newCalendar =new CalendarAction("my new Calendar","Home","2017/01/01",
//                "2017/01/05",null,"9:00","12:00", CALENDAR_RECURRENCE_EVERY_DAY,
//                CALENDAR_REMINDER_TYPE_NOTIFICATION, CALENDAR_BEFORE_VALUE_1_HOUR);
        CalendarAction newCalendar =new CalendarAction("my new Calendar","Home",null,null,
                null,null,null,null,null,null);
        newCalendar.addEvent();
    }

}
