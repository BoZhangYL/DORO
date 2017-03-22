package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.hamcrest.Asst;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import ckt.base.VP4;
import doro.action.EmailAction;
import doro.action.MainAction;
import doro.bean.Email;
import doro.page.APPMenuPage;
import doro.page.EmailPage;

/**
 * Created by Caibing.Yin on 2017/1/14.
 */
@RunWith(AndroidJUnit4.class)
public class EmailCase extends VP4 {
    @BeforeClass
    public static void initGalleryCase() {
        unLock();
        EmailAction.loginEmail();
    }

    @After
    public void checkConnect() {
        EmailAction.openWiFiDataUsage();
    }

    @Test
    public void launchEmail() {
        EmailAction.openEmailApp();
    }

    @Test
    public void exitEmail() {
        EmailAction.openEmailApp();
        EmailAction.exitEmailApp();
    }

    @Test
    public void loadEmail() {
        EmailAction.openEmailApp();
        EmailAction.refreshPage();
        EmailAction.waitLoad();
    }

    @Test
    public void createSendEmail() {
        EmailAction.openEmailApp();
        EmailAction.NewEmailByAddress();
        EmailAction.sendEmail();
    }

    @Test
    public void checkSendEmailPrompt() {
        EmailAction.openEmailApp();
        EmailAction.enabkedConfirmSending();
        EmailAction.NewEmailByAddress();
        EmailAction.sendEmail();
        EmailAction.checkConfirmSending();
        EmailAction.disabledConfirmSending();
    }

    @Test
    public void checkReceiveEmail() {
        Email email =new Email();
        EmailAction.openEmailApp();
        email =EmailAction.NewEmailByAddress();
        EmailAction.sendEmail();
        EmailAction.exitEmailApp();
        EmailAction.waitReceiveEmail();
        EmailAction.checkEmailFromNotifcation(email);
    }

    @Test
    public void receiveEmail() {
        EmailAction.waitReceiveEmail();
        Email email = new Email();
        EmailAction.openEmailApp();
        EmailAction.changeSyncTimeToFiveMinutes();
        email = EmailAction.NewEmailByAddress();
        EmailAction.sendEmail();
        EmailAction.exitEmailApp();
        EmailAction.waitReceiveEmail();
        EmailAction.checkSendEmail(email);
    }

    @Test
    public void checkEmailMenu() {
        EmailAction.openEmailApp();
        EmailAction.switchToEmailMenu();
    }

    @Test
    public void operationOutBox() {
        Email email = new Email();
        EmailAction.closeDataUsage();
        EmailAction.openEmailApp();
        EmailAction.switchToOutBox();
        email = EmailAction.openOutEmail();
        EmailAction.zoomInEmail(email);
        EmailAction.deleteEmail(email);
        EmailAction.exitOutBox();
    }

    @Test
    public void operationSentBox() {
        EmailAction.openEmailApp();
        EmailAction.switchToSentBox();
        Email email = EmailAction.openSentEmail();
        EmailAction.zoomInEmail(email);
        EmailAction.deleteEmail(email);
    }

    @Test
    public void cehckSentEmail() {
        EmailAction.openEmailApp();
        EmailAction.switchToSentBox();
    }

    @Test
    public void firestLoginEmail() {
        EmailAction.loginEmail();
    }


 /*   @Test
    //
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
    //新建邮件并发送成功
    public void testCreateNewEmailAndRecieveSuccessfully() throws IOException, UiObjectNotFoundException {
        //给woshihouzi2016@gmail.com发送一封新邮件
        EmailAction.CreateNewEmail("TestCreateNewEmail","CreateSuccessfully","woshihouzi2016@gmail.com");
        //登录woshihouzi2016@gmail.com账号查看是否收到新邮件
        EmailAction.LogInEmail("woshihouzi2016@gmail.com","woshidoubi","");
        gDevice.pressBack();

    }
    @Test
    public void testA() throws UiObjectNotFoundException, IOException {
        EmailAction.LogInEmail("woshihouzi2016@gmail.com", "woshidoubi", "");
    }*/

}
