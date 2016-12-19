package doro.action;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.junit.Assert;

import ckt.base.VP4;

import static ckt.base.VP3.takeScreen;
import static doro.page.CalednarPage.*;
import static org.hamcrest.Asst.assertEquals;


/**
 * Created by bo.zhang on 2016/12/02   .
 */

public class CalendarAction extends VP4 {
    private static String CalednarTitle = null;
    private static String CalednarLocation = null;
    private static String CalendarRecurrence = null;
    private static String CalendarReminder = null;
    private static String CalendarBefore = null;
    private static String CalendarStartDate = null;
    private static String CalendarEndDate = null;
    private static String CalendarStartTime = null;
    private static String CalendarEndTime = null;
    private static String CalendarAllDayEvent = null;

    private void initCalendarDevice() {
        initDevice();
    }

    public static void setCalendar(String CalednarTitle, String CalednarLocation,
                          String CalendarStartDate, String CalendarEndDate, String CalendarAllDayEvent,
                          String CalendarStartTime, String CalendarEndTime, String CalendarRecurrence,
                          String CalendarReminder, String CalendarBefore) {
        setCalednarTitle(CalednarTitle);
        setCalednarLocation(CalednarLocation);
        setCalendarRecurrence(CalendarRecurrence);
        setCalendarReminder(CalendarReminder);
        setCalendarBefore(CalendarBefore);
        setCalendarStartsDATE(CalendarStartDate);
        setCalendarEndsDATE(CalendarEndDate);
        setCalendarStartsTime(CalendarStartTime);
        setCalendarEndsTime(CalendarEndTime);
        setCalendarAllDayEvent(CalendarAllDayEvent);
    }

    private static void ClickAddEventButton() {//点击Add event按钮
        UiObject Calendar = getObjectByIdText(CALENDAR_ADD_BUTTIN_ID,
                CALENDAR_ADD_BUTTON_TEXT);
        try {
            while(!Calendar.exists()){
                scrollToEnd(STEP_NORMAL);
            }
            Calendar.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void setCalednarTitle(String CalednarTitles) {//设置日历标题
        CalednarTitle = CalednarTitles;
    }

    private static void getCalednarTitle() {//输入日历标题
        UiObject TitleBox = getObjectById(CALENDAR_EVENT_TITLE_ID);
        doroInput(TitleBox, CalednarTitle);
    }

    public static void setCalednarLocation(String CalednarLocations) {//设置日历地址
        CalednarLocation = CalednarLocations;
    }

    private static void getCalednarLocation() {//输入日历地址
        UiObject LocationBox = getObjectById(CALENDAR_LOCATION_ID);
        doroInput(LocationBox, CalednarLocation);
    }

    public static void setCalendarStartsDATE(String STartDATE) {//设置开始日期,格式：年/月/日
        CalendarStartDate = STartDATE;
    }

    private static void getCalendarStartsDATE() {//输入日期
        String[] datedetail = CalendarStartDate.split("/");
        UiObject StartsDateButton = getObjectById(CALENDAR_EVENT_START_DATE_BUTTON);
        if (datedetail.length == 3) {
            try {
                StartsDateButton.clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
            setDate(CalendarStartDate);
        } else {
            Log.e("CAlendarDAte", "Date format error");
        }

    }

    public static void setCalendarEndsDATE(String EndsDate) {//设置结束日期
        CalendarEndDate = EndsDate;
    }

    private static void getCalendarEndsDATE() {
        String[] datedetail = CalendarEndDate.split("/");
        UiObject EndDateButton = getObjectById(CALENDAR_EVENT_END_DATE_BUTTON);
        if (datedetail.length == 3) {
            try {
                EndDateButton.clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
            setDate(CalendarEndDate);
        } else {
            Log.e("CAlendarDAte", "Date format error");
        }
    }

    public static void setCalendarStartsTime(String CalendarStartTimes) {//输入开始时间
        CalendarStartTime = CalendarStartTimes;
    }

    private static void getCalendarStartsTime() {//设置开始时间
        try {
            getObjectById(CALENDAR_EVENT_START_TIME_BUTTON).clickAndWaitForNewWindow();
            setTime(CalendarStartTime);
            getObjectById(CALENDAR_EDIT_TIME_VIEW_CONFIRM_BUTTON).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setCalendarEndsTime(String CalendarEndTimes) {//输入结束时间
        CalendarEndTime = CalendarEndTimes;
    }

    private static void getCalendarEndsTime() {//设置结束时间
        try {
            getObjectById(CALENDAR_EVENT_END_TIME_BUTTON).clickAndWaitForNewWindow();
            setTime(CalendarEndTime);
            getObjectById(CALENDAR_EDIT_TIME_VIEW_CONFIRM_BUTTON).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void setCalendarAllDayEvent(String AllDayEventStates) {//输入全天事件状态
        CalendarAllDayEvent = AllDayEventStates;
    }

    private static void getCalendarAllDayEvent() {//设置全天事件
        String currentAllDayState;
        try {
            boolean stateALLDAY = getObjectById(CALENDAR_ALL_DAY_EVENT).isChecked();
            if (stateALLDAY) {
                currentAllDayState = CALENDAR_ALL_DAY_ENALE;
            } else {
                currentAllDayState = CALENDAR_ALL_DAY_DISABLE;
            }
            if (!CalendarAllDayEvent.equals(currentAllDayState)) {
                getObjectById(CALENDAR_ALL_DAY_EVENT).click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setCalendarRecurrence(String CalendarRecurrences) {//输入重复事件
        CalendarRecurrence = CalendarRecurrences;
    }

    private static void getCalendarRecurrence() {//设置重复事件
        try {
            getObjectById(CALENDAR_RECURRENCE_BUTTON).clickAndWaitForNewWindow();
            getObjectByIdText(CALENDAR_SET_RECURRENCE_BUTTON, CalendarRecurrence)
                    .clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setCalendarReminder(String CalendarReminders) {//输入提醒
        CalendarReminder = CalendarReminders;
    }

    private static void getCalendarReminder() {//设置提醒
        try {
            getObjectById(CALENDAR_REMINDER_BUTTON).clickAndWaitForNewWindow();
            getObjectByIdText(CALENDAR_CHOSE_REMINDER_TYPE, CalendarReminder)
                    .clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setCalendarBefore(String CalendarBefores) {//输入日历开始好多分钟前提醒
        CalendarBefore = CalendarBefores;
    }

    private static void getCalendarBefore() {//设置日历开始好多分钟前提醒
        try {
            getObjectById(CALENDAR_BEFORE_BUTTON).clickAndWaitForNewWindow();
            getObjectByIdText(CALENDAR_SET_BEFOR, CalendarBefore).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createNewCalendar() {//创建一个新的calendar
        UiObject NextButton = getObjectById(CALENDAR_ADD_EVENT_NEXT_BUTTON);
        try {
            ClickAddEventButton();
            setEventPage();
            NextButton.click();

            setMomentPage();
            NextButton.click();

            setReminderPage();
            NextButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void setEventPage() {//设置新calendar的event界面
        if (CalednarTitle != null) {
            getCalednarTitle();
        }
        if (CalednarLocation != null) {
            getCalednarLocation();
        }
    }

    private static void setMomentPage() {//设置新calendar的moment界面
        if (CalendarStartDate != null) {
            getCalendarStartsDATE();
        }
        if (CalendarEndDate != null) {
            getCalendarEndsDATE();
        }
        if (CalendarAllDayEvent != null) {
            getCalendarAllDayEvent();
        }
        if (CalendarStartTime != null) {
            getCalendarStartsTime();
        }
        if (CalendarEndDate != null) {
            getCalendarEndsTime();
        }
        if (CalendarRecurrence != null) {
            getCalendarRecurrence();
        }

    }

    private static void setReminderPage() {//设置新calendar的reminder可免
        if (CalendarReminder != null) {
            getCalendarReminder();
        }
        if (CalendarBefore != null) {
            getCalendarBefore();
        }
    }

    public static void doroInput(UiObject object, String text) {//输入键盘的操作
        try {
            object.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        while (!getObjectById(DORO_HIDE_KEYBOARD_BUTTON).exists()) {
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

    public static void setDate(String date_yyyy_mm_dd) {//设置日期，格式YYYY/MM/DD
        String[] datedetail = date_yyyy_mm_dd.split("/");
        UiObject SetYearMonthView = gDevice.findObject(new UiSelector().index(0).index(1));
        UiObject Dayview = gDevice.findObject(new UiSelector()
                .resourceId(CALENDAT_SET_DATE_VIEW));
        String[] YearMonth = new String[0];
        try {
            YearMonth = SetYearMonthView.getText().split(" ");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String CalendarMonth = YearMonth[0];
            String CalendarYear = YearMonth[1];
            String inputYear = datedetail[0];
            String inputMonth = datedetail[1];
            String inputDay = datedetail[2];
            while (!CalendarYear.equals(inputYear)) {//设置年
                if (Integer.valueOf(inputYear) > Integer.valueOf(CalendarYear)) {
                    scrollLeft(Dayview, STEP_NORMAL);
                    SetYearMonthView = gDevice.findObject(new UiSelector().index(0).index(1));
                    YearMonth = SetYearMonthView.getText().split(" ");
                    CalendarYear = YearMonth[1];
                }
                if (Integer.valueOf(inputYear) < Integer.valueOf(CalendarYear)) {
                    scrollRight(Dayview, STEP_NORMAL);
                    SetYearMonthView = gDevice.findObject(new UiSelector().index(0).index(1));
                    YearMonth = SetYearMonthView.getText().split(" ");
                    CalendarYear = YearMonth[1];
                }
            }
            while (CalendarMonth.equals("January")) {//设置月
                scrollRight(Dayview, STEP_NORMAL);
            }
            for (int i = 1; i < Integer.valueOf(inputMonth); i++) {
                scrollLeft(Dayview, STEP_NORMAL);
            }
            UiObject days = gDevice.findObject(new UiSelector().
                    className(CALENDAR_SET_DAY_VIEW).
                    text(String.valueOf(Integer.valueOf(inputDay))));//设置日
            days.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void setTime(String TIME_HH_MM) {//设置时间，格式：HH：MM
        String[] times = TIME_HH_MM.split(":");
        int hours = Integer.valueOf(times[0]);
        int minutes = Integer.valueOf(times[1]);
        try {
            int currentHours = Integer.valueOf(getObjectById(CALENDAR_SET_HOUR).getText());
            int currentMinutes = Integer.valueOf(getObjectById(CALENDAR_SET_MIBUTE).getText());
            int j = hours - currentHours;
            int k = minutes - currentMinutes;
            if (j > 0) {//设置小时
                for (int i = 0; i < j; i++) {
                    getObjectById(CALENDAR_ADD_HOUR).click();
                }
            } else {
                for (int i = 0; i > j; i--) {
                    getObjectById(CALENDAR_MINUS_HOUR).click();
                }
            }
            if (k > 0) {//设置分钟
                for (int i = 0; i < k; i++) {
                    getObjectById(CALENDAR_ADD_MINUTE).click();
                }
            } else {
                for (int i = 0; i > k; i--) {
                    getObjectById(CALENDAR_MINUS_MINUTE).click();
                }
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static boolean isCalendarGreenPoint(UiObject CurrentObject, String newpath) {
        //绿点的位置是对应的日标签框中的坐标：（横坐标：1/2日标签，纵坐标：1/5纵坐标），半径：8像素点
        Bitmap mBitmap = BitmapFactory.decodeFile(newpath);
        UiObject daybox = CurrentObject;
        try {
            int PixelX0 = daybox.getBounds().left;
            int PixelY0 = daybox.getBounds().top;
            int pixelX1 = daybox.getBounds().right;
            int PixelY1 = daybox.getBounds().bottom;
            int PixelGreenPointX = daybox.getBounds().centerX();
            int PixelGreenPointY = PixelY0 + ((PixelY1-PixelY0) / 5);
            for (int i = PixelGreenPointX -10; i < PixelGreenPointX +10; i++) {
                for (int j = PixelGreenPointY-10; j < PixelGreenPointY+10; j++) {
                    int x = i, y = j;
                    if ((Math.abs(x - PixelGreenPointX) < 8) && (Math.abs(y - PixelGreenPointY) < 8)) {
                        //Green Constant Value: -16711936 (0xff00ff00)
                        int k = mBitmap.getPixel(x, y);
                        if (k< (Color.GREEN*0.98) && k >(Color.GREEN*1.12)) {
                            return false;
                        }
                    }
                }
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static int  getCurrentDay(){//得到当前日期的日
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        return day;
    }
    @TargetApi(Build.VERSION_CODES.N)
    public static int getCurrentYear(){//得到当前日期的年
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        return year;
    }
    @TargetApi(Build.VERSION_CODES.N)
    public static int getCurrentMonth(){//得到当前日期的月
        Calendar c = Calendar.getInstance();
        int  month = c.get(Calendar.MONTH);
        return month;
    }
    public static void checkOpenCalendarResult(){//检查打开calendar结果
        assertEquals("openCalendarFromAppList",
                CALENDAR_PACKAGE,gDevice.getCurrentPackageName());
    }
    public static void checkCreateBeforCalendar() throws UiObjectNotFoundException {
        scrollToEnd(STEP_NORMAL);
        UiObject beforbox = getObjectById("com.doro.apps.calendar:id/event_alarm");
        Assert.assertEquals("CreateBeforeFiveMinEvent",CALENDAR_BEFORE_VALUE_5_MINUTES,
                beforbox.getText());
    }
    public static void checkCalendarGreenPoint()  {//检查绿点
        try {
            UiObject DayBox =getObjectByIdText(CALENDAR_DAY_LABEL, String.valueOf(getCurrentDay()));
            while(!DayBox.exists()) {
                pressKey("Back");
            }
            DayBox =getObjectByIdText(CALENDAR_DAY_LABEL, String.valueOf(getCurrentDay()));
            String newpath = takeScreen("calendar");
            Assert.assertTrue("checkCalendarGreenPointsCase3",isCalendarGreenPoint(DayBox,
                    newpath));
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}
