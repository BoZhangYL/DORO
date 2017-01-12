package doro.action;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;

import ckt.base.VP4;

import static android.R.attr.id;
import static android.R.id.button2;
import static ckt.base.VP2.getObjectById;
import static ckt.base.VP2.waitTime;
import static doro.action.CalendarAction.doroInput;
import static doro.page.CalednarPage.CALENDAR_EVENT_TITLE_ID;
import static doro.page.CalednarPage.DORO_HIDE_KEYBOARD_BUTTON;
import static doro.page.WifiPage.INPUT_PASSWORDBOX;

/**
 * Created by admin on 2017/1/10.
 */

public class WifiAction extends VP4{
    private static void doroInput(UiObject object, String text) {//输入键盘的操作
        try {
            object.click();
            waitTime(1);
            object.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getPasswprd(String Passwords) {//输入密码
        UiObject PasswordBox = getObjectById(INPUT_PASSWORDBOX);
        doroInput(PasswordBox,Passwords);}
}
