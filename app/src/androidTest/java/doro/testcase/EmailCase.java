package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.hamcrest.Asst;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import ckt.base.VP4;
import doro.action.EmailAction;
import doro.action.MainAction;
import doro.page.APPMenuPage;

/**
 * Created by Caibing.Yin on 2017/1/14.
 */
@RunWith(AndroidJUnit4.class)
public class EmailCase extends VP4 {
    @BeforeClass
    public static void initGalleryCase(){
        unLock();
    }

    @Test
    public void testEnterEmail() throws UiObjectNotFoundException {
        MainAction.startApp(APPMenuPage.AppNameList[14]);
        Asst.assertTrue(text_exists("Email address"));
    }
    @Test
    public void testQuitEmail() throws UiObjectNotFoundException {
        MainAction.startApp(APPMenuPage.AppNameList[14]);
        gDevice.pressBack();
        Asst.assertFalse("QuitEmailFail",text_exists("Email address"));
    }
    @Test
    public void testCreateEmailAccount() throws UiObjectNotFoundException, IOException {
        MainAction.startApp(APPMenuPage.AppNameList[14]);
        boolean Actcal = text_exists("You can set up your account in just a few steps.");
        shellInputText(EmailAction.reduceRandomEmail(10));
        clickByText("OK");
    }
    @Test
    public void test() throws IOException, UiObjectNotFoundException {
        EmailAction.LogInEmail();
    }
}
