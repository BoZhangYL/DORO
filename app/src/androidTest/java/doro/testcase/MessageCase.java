package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.squareup.spoon.Spoon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.action.MessageAction;
import doro.page.MessagePage;

/**
 * Created by admin on 2016/12/7.
 */
@RunWith(AndroidJUnit4.class)
public class MessageCase extends VP4{
    @Before
    public void before() throws Exception {
        MessageAction.launchMessage();
    }
    @Test
    public void testIWT() throws UiObjectNotFoundException {
        MessageAction.clickIWantToBtn();
        Spoon.screenshot("i_want_to");
        waitUntilFind(MessagePage.MENU_CLOSE_ID,10000);
        Assert.assertEquals("enter i want to page",true,id_exists(MessagePage.MENU_CLOSE_ID));
    }
    @Test
    public void testMsgAN() throws UiObjectNotFoundException {
        MessageAction.clickWriteMessageBtn();
        MessageAction.clickANumber();
        MessageAction.toWhomEnterNumber("10086");
        MessageAction.clickPickNumber();
        //input msg text
        String expectMsg=getRandomString(10);
        MessageAction.insertText(expectMsg);
        MessageAction.sendMsg();
        MessageAction.checkMsg(expectMsg);
    }
    @Test
    public void testMsgAC(){
        MessageAction.clickWriteMessageBtn();
        MessageAction.clickAContact();
    }
    @Test
    public void testMsgAR(){
        MessageAction.clickWriteMessageBtn();
        MessageAction.clickARecentContact();
    }

}
