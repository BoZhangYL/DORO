package doro.page;

/**
 * Created by elon on 2016/12/1.
 */
public class SettingPage {


    //设置中的“Storage”的Text
    public static final String SETTINGS_STORAGE_TEXT ="Storage";
    //""中“Storage settings”的设置
    public static final String SETTINGS_STORAGE_SETTINGS_TEXT ="Storage settings";
    //设置中的“Date&Time”的Text
    public static final String SETTINGS_DATE_TIME_TEXT ="Date & time";
    //“Date&Time”中“Automatic date & time”的Text
    public static final String SETTINGS_AUTOMATIC_DATETIME_TEXT ="Automatic date & time";
    //设置中的日期与时间的时间设置的Text
    public static final String SETTINGS_NETWORK_PROVIDED_TIME_TEXT ="Use network-provided time";
    public static final String SETTINGS_GPS_PROVIDED_TEXT ="Use GPS-provided time";
    public static final String SETTINGS_OFF_TEXT ="off";
    //Date&Time中"Automatic time zone"的Text
    public static final String SETTINGS_AUTO_TIME_ZONE_TEXT ="Automatic time zone";
    //Date&Time中日期设置的Text
    public static final String SETTINGS_SET_DATE_TEXT ="Set date";
    //“Set date”与“Set time”中的"OK"的Text
    public static final String SETTINGS_SET_DATE_OK_TEXT ="OK";
    //Date&Time中时间设置的Text
    public static final String SETTINGS_SET_TIME_TEXT ="Set time";
    //Date&Time中"Select time zone"的Text
    public static final String SETTINGS_SELECT_TIME_ZONE_TEXT ="Select time zone";
    //Date&Time中"Use 24-hour format"的Text
    public static final String SETTINGS_USE_24HOUR_FORMAT_TEXT ="Use 24-hour format";
    //使用12小时制度
    public static final String SETTINGS_CHOOSE_12HOUR_FORMAT_TEXT ="1:00 pm";
    //使用24小时制度
    public static final String SETTINGS_CHOOSE_24HOUR_FORMAT_TEXT ="13:00";


    //日期设置中“year”的ID
    public static final String SETTINGS_DATE_HEADER_YEAR_ID ="android:id/date_picker_header_year";
    //日期设置中“date”的ID
    public static final String SETTINGS_DATE_HEADER_DATE_ID ="android:id/date_picker_header_date";
    //日期设置中“year”列表中年份的ID
    public static final String SETTINGS_DATE_YEAR_TEXT1_ID = "android:id/text1";
    //日期设置中月份后退与前进的ID
    public static final String SETTINGS_DATE_MONTH_PREV_ID ="android:id/prev";
    public static final String SETTINGS_DATE_MONTH_NEXT_ID ="android:id/next";
    //时间设置中的时间“hour”的ID
    public static final String SETTINGS_TIME_HOURS_ID ="android:id/hours";
    //时间设置中的时间“min”的ID
    public static final String SETTINGS_TIME_MINUTES_ID ="android:id/minutes";

    //存储设置中的Class
    public static final String SETTINGS_STORAGE_RECYCLERVIEWER_CLASS ="android.support.v7.widget.RecyclerView";
    public static final String SETTINGS_STORAGE_LINEARLAYOUT_CLASS ="android.widget.LinearLayout";
    //存储设置中默认设置的按钮的Class
    public static final String SETTINGS_STORAGE_RADIOBUTTON_CLASS="android.widget.RadioButton";
    //日期设置中的“天”的Class
    public static final String SETTING_DATE_DAY_VIEW_CLASS ="android.view.View";
    //时间设置中的“hour”与“mins”的Class
    public static final String SETTING_HOUR_TOUCHHELPER_CLASS ="android.widget.RadialTimePickerView$RadialPickerTouchHelper";

}
