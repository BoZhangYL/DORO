package doro.action;

import android.support.test.uiautomator.UiObjectNotFoundException;

import com.squareup.spoon.Spoon;

import org.hamcrest.Asst;

import java.util.logging.Logger;

import ckt.base.VP4;
import doro.page.MessagePage;

/**
 * Created by qiang.zhang on 2017/1/12.
 */
public class MessageAction extends VP4{
    private static Logger logger=Logger.getLogger(MessagePage.class.getName());
    /*Launch message*/
    public static void launchMessage() throws Exception {
        MainAction.stopMessage();
        MainAction.startMessage();
    }
    public static void launchMessages(){
        try {
            //清除所有启动的app
            MainAction.clearAllApp();
            //启动联系人App
            MainAction.startApp(MessagePage.MSG);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*click-write message button*/
    public static void clickWriteMessageBtn(){
        clickById(MessagePage.WRITE_MESSAGE_BTN_ID);
        //等待页面加载完成
        waitUntilFind(MessagePage.TO_WHOM_A_NUMBER_TEXT,20000);
    }
    /*click-i want to button*/
    public static void clickIWantToBtn(){
        clickById(MessagePage.I_WANT_TO_ID);
    }
    //To whom - a number
    public static void clickANumber(){
        clickByText(MessagePage.TO_WHOM_A_NUMBER_TEXT);
    }
    public static void clickAContact(){
        clickByText(MessagePage.TO_WHOM_A_CONTACT_TEXT);
    }
    public static void clickARecentContact(){
        clickByText(MessagePage.TO_WHOM_A_RECENT_CONTACT_TEXT);
    }
    public static void clickDialerGridView(char c){
        clickByIdText(MessagePage.TO_WHOM_DIALER_BTN_NUMBER_ID,String.valueOf(c));
    }
    //拨号
    public static void toWhomEnterNumber(String number) throws UiObjectNotFoundException {
        waitUntilFind(MessagePage.TO_WHOM_DIALER_BTN_ID,20000);
        clearText(MessagePage.TO_WHOM_DIGITS_EDIT_TEXT_ID);
        char[] chars = number.toCharArray();
        for (int i = 0;i<chars.length;i++){
            clickDialerGridView(chars[i]);
        }
        Spoon.screenshot("dial");
    }
    //删除号码
    public static void clickDeleteDigits(int n){
        for (int i = 0; i <n ; i++) {
            clickById(MessagePage.TO_WHOM_DELETE_BTN_ID);
        }
    }
    //click pick number
    public static void clickPickNumber(){
        clickById(MessagePage.TO_WHOM_PICK_KEYPAD_ID);
    }
    //input msg-text
    public static void insertText(String msg) throws UiObjectNotFoundException {
        setText(MessagePage.COMPOSE_MSG_EDIT_TEXT_ID,msg);
    }
    //send msg button
    public static void sendMsg() throws UiObjectNotFoundException {
        clickById(MessagePage.SEND_MSG_BUTTON_ID);
    }
    //check  msg-text
    public static void checkMsg(String text) throws UiObjectNotFoundException {
        waitUntilFind(MessagePage.MSG_TEXT_ID,10000);
        String expectMsg=text;
        String activeMsg=getTex(MessagePage.MSG_TEXT_ID);
        String msgStatus=getTex(MessagePage.MSG_STATUS_ID);
        Asst.assertEquals("send success",expectMsg,activeMsg);
    }
}
