package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import com.squareup.spoon.Spoon;
import org.junit.Test;
import org.junit.runner.RunWith;
import ckt.base.VP4;
import doro.action.CalendarAction;

/**
 * Created by bo.zhang on 2016/12/02   .
 */
@RunWith(AndroidJUnit4.class)
public class CalendarCase extends VP4 {

    @Test
    public void launchCalendar() {//打开Calendar应用
            openAppliction("Calen\u200Bdar");
    }

    @Test
    public void createEventBefore5(){//创建提前5分钟提醒事件
        openAppliction("Calen\u200Bdar");
        CalendarAction newCalendar =new CalendarAction();
        newCalendar.setCalednarTitle("my new Calendar");
        newCalendar.setCalednarLocation("Home");
        newCalendar.addEvent();
    }
}
