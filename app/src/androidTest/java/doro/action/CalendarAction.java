package doro.action;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import ckt.base.VP4;

import static ckt.base.VP3.takeCutCompareScreen;
import static ckt.base.VP3.takeCutScreen;
import static doro.page.CalednarPage.*;
import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;


/**
 * Created by bo.zhang on 2016/12/02   .
 */

public class CalendarAction extends VP4 {
    private String CalednarTitle = null;
    private String CalednarLocation = null;
    private String CalendarRecurrence = null;
    private String CalendarReminder = null;
    private String CalendarBefore = null;
    private String CalendarStartDate = null;
    private String CalendarEndDate = null;
    private String CalendarStartTime = null;
    private String CalendarEndTime = null;
    private String CalendarAllDayEvent = null;

    private void initCalendarDevice() {
        initDevice();
    }

    public CalendarAction() {

    }

    public CalendarAction(String CalednarTitle, String CalednarLocation,
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

    private void ClickAddEventButton() {//点击Add event按钮
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

    public void setCalednarTitle(String CalednarTitle) {//设置日历标题
        this.CalednarTitle = CalednarTitle;
    }

    private void getCalednarTitle() {//输入日历标题
        UiObject TitleBox = getObjectById(CALENDAR_EVENT_TITLE_ID);
        doroInput(TitleBox, CalednarTitle);
    }

    public void setCalednarLocation(String CalednarLocation) {//设置日历地址
        this.CalednarLocation = CalednarLocation;
    }

    private void getCalednarLocation() {//输入日历地址
        UiObject LocationBox = getObjectById(CALENDAR_LOCATION_ID);
        doroInput(LocationBox, CalednarLocation);
    }

    public void setCalendarStartsDATE(String STartDATE) {//设置开始日期,格式：年/月/日
        this.CalendarStartDate = STartDATE;
    }

    private void getCalendarStartsDATE() {//输入日期
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

    public void setCalendarEndsDATE(String EndsDate) {//设置结束日期
        this.CalendarEndDate = EndsDate;
    }

    private void getCalendarEndsDATE() {
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

    public void setCalendarStartsTime(String CalendarStartTime) {//输入开始时间
        this.CalendarStartTime = CalendarStartTime;
    }

    private void getCalendarStartsTime() {//设置开始时间
        try {
            getObjectById(CALENDAR_EVENT_START_TIME_BUTTON).clickAndWaitForNewWindow();
            setTime(this.CalendarStartTime);
            getObjectById(CALENDAR_EDIT_TIME_VIEW_CONFIRM_BUTTON).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setCalendarEndsTime(String CalendarEndTime) {//输入结束时间
        this.CalendarEndTime = CalendarEndTime;
    }

    private void getCalendarEndsTime() {//设置结束时间
        try {
            getObjectById(CALENDAR_EVENT_END_TIME_BUTTON).clickAndWaitForNewWindow();
            setTime(this.CalendarEndTime);
            getObjectById(CALENDAR_EDIT_TIME_VIEW_CONFIRM_BUTTON).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setCalendarAllDayEvent(String AllDayEventState) {//输入全天事件状态
        this.CalendarAllDayEvent = AllDayEventState;
    }

    private void getCalendarAllDayEvent() {//设置全天事件
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

    public void setCalendarRecurrence(String CalendarRecurrence) {//输入重复事件
        this.CalendarRecurrence = CalendarRecurrence;
    }

    private void getCalendarRecurrence() {//设置重复事件
        try {
            getObjectById(CALENDAR_RECURRENCE_BUTTON).clickAndWaitForNewWindow();
            getObjectByIdText(CALENDAR_SET_RECURRENCE_BUTTON, this.CalendarRecurrence)
                    .clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setCalendarReminder(String CalendarReminder) {//输入提醒
        this.CalendarReminder = CalendarReminder;
    }

    private void getCalendarReminder() {//设置提醒
        try {
            getObjectById(CALENDAR_REMINDER_BUTTON).clickAndWaitForNewWindow();
            getObjectByIdText(CALENDAR_CHOSE_REMINDER_TYPE, this.CalendarReminder)
                    .clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setCalendarBefore(String CalendarBefore) {//输入日历开始好多分钟前提醒
        this.CalendarBefore = CalendarBefore;
    }

    private void getCalendarBefore() {//设置日历开始好多分钟前提醒
        try {
            getObjectById(CALENDAR_BEFORE_BUTTON).clickAndWaitForNewWindow();
            getObjectByIdText(CALENDAR_SET_BEFOR, this.CalendarBefore).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addEvent() {//
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

    private void setEventPage() {
        if (CalednarTitle != null) {
            getCalednarTitle();
        }
        if (CalednarLocation != null) {
            getCalednarLocation();
        }
    }

    private void setMomentPage() {
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

    private void setReminderPage() {
        if (CalendarReminder != null) {
            getCalendarReminder();
        }
        if (CalendarBefore != null) {
            getCalendarBefore();
        }
    }

    public void doroInput(UiObject object, String text) {
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

    public void setDate(String date_yyyy_mm_dd) {//设置日期，格式YYYY/MM/DD
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

    public void setTime(String TIME_HH_MM) {//设置时间，格式：HH：MM
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

    public boolean isCalendarGreenPoint(UiObject CurrentObject, String newpath) {
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
                        int k = mBitmap.getPixel(x, y);
                        if (-16434874 != k) {
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


}
