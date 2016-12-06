package doro.action;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;

import ckt.base.VP4;
import static doro.page.CalednarPage.*;


/**
 * Created by bo.zhang on 2016/12/02   .
 */

public class CalendarAction extends VP4 {
    private String CalednarTitle = null;
    private String CalednarLocation = null;
    private String CalendarRecurrence = null;
    private String CalendarReminder = null;
    private String CalendarBefore = null;
    private String STartsDATE = null;
    private byte CalendarAllDayEvent;
    private void initCalendarDevice(){
        initDevice();
    }
    private void ClickAddEventButton(){//点击Add event按钮
        scrollToEnd(STEP_NORMAL);
        UiObject Calendar = getObjectByIdText(CALENDAR_ADD_BUTTIN_ID,
                CALENDAR_ADD_BUTTON_TEXT);
        try {
            Calendar.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void setCalednarTitle(String CalednarTitle){//设置日历标题
        this.CalednarTitle = CalednarTitle;
    }
    private void getCalednarTitle(){//输入日历标题
        UiObject TitleBox = getObjectById(CALENDAR_EVENT_TITLE_ID);
        doroInput(TitleBox,CalednarTitle);
    }
    public void setCalednarLocation(String CalednarLocation){//设置日历地址
        this.CalednarLocation = CalednarLocation;
    }
    private void getCalednarLocation(){//输入日历地址
        UiObject LocationBox = getObjectById(CALENDAR_LOCATION_ID);
        doroInput(LocationBox,CalednarLocation);
    }
    public void setCalendarStartsDATE(String STartsDATE){//设置开始日期
        this.STartsDATE = STartsDATE;
    }
    private void getCalendarStartsDATE(){//输入日期

    }
    public void setCalendarEndsDATE(){//设置结束日期

    }
    public void setCalendarStartsTime(){//设置开始时间

    }
    public void setCalendarEndsTime(){//设置结束时间

    }
    public void setCalendarAllDayEvent(byte AllDayEventState){//设置全天事件

    }
    public void setCalendarRecurrence(String CalendarRecurrence){//设置重复事件
        this.CalendarRecurrence = CalendarRecurrence;
    }
    public void setCalendarReminder(String CalendarReminder){//设置提醒
        this.CalendarReminder = CalendarReminder;
    }
    public void setCalendarBefore(String CalendarBefore){//设置日历开始好多分钟前提醒
        this.CalendarBefore = CalendarBefore;
    }
    public void addEvent(){//
        ClickAddEventButton();
        UiObject NextButton =getObjectById(CALENDAR_ADD_EVENT_NEXT_BUTTON);
        setEventPage();
        try {
            NextButton.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        setMomentPage();
        try {
            NextButton.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        try {
            NextButton.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        setReminderPage();
    }
    private void setEventPage(){
        if(CalednarTitle != null){
            getCalednarTitle();
        }
        if(CalednarLocation != null) {
            getCalednarLocation();
        }
    }
    private void setMomentPage(){
        ;
    }
    private void setReminderPage(){
        ;
    }
    public void doroInput(UiObject object,String text){
        try {
            object.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        while(!getObjectById(DORO_HIDE_KEYBOARD_BUTTON).exists()){
                waitTime(1);
        }
        try {
            object.setText(text);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        try {
            getObjectById(DORO_HIDE_KEYBOARD_BUTTON).click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

}
