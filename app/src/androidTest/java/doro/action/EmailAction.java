package doro.action;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.hamcrest.Asst;
import org.junit.Assert;

import java.io.IOException;
import java.util.Random;

import ckt.base.VP4;
import doro.page.APPMenuPage;
import doro.page.EmailPage;

import static doro.page.EmailPage.EMAIL_INPUT_OK_BUTTON;
import static doro.page.EmailPage.EMAIL_WRITE_EMAIL;

/**
 * Created by Caibing.Yin on 2017/1/14.
 */

public class EmailAction extends VP4 {
    //产生一个随机邮箱账号
    public static String reduceRandomEmail(int length) {
        int count=(int)(3+Math.random()*(length-3));
        int count1=(int)(1+Math.random()*(count-3));
        String randomEmail="";
        char[]alphaEmail={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
                'p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G',
                'H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y',
                'Z','0','1','2','3','4','5','6','7','8','9'};
        String[]alphaTLD={".com",".net",".org",".biz",".mobi",".travel",".name",".pro",
                ".cn",".cc", ".co",".in",".me",".tv",".us",".tk",".net",".gov",".edu",
                ".mil",".biz",".info",".museum",".travel",".int",".areo",".post",".rec",
                ".asia",".vip",".top",".au",".ae",".ar",".br",".ca",".ch",".de",".dk",
                ".es",".eg",".fg",".gr",".hk",".in",".iq",".it",".jp",".mo",".us",".uk"};
        for(int i=0;i<count1;i++){
            Character ranChar1=Character.valueOf(alphaEmail[new Random().nextInt(alphaEmail.length)]);
            randomEmail=randomEmail+ranChar1.toString();}
        randomEmail=randomEmail+"@";
        for(int a=0;a<count-count1-1;a++){
            Character ranChar2=Character.valueOf(alphaEmail[new Random().nextInt(alphaEmail.length)]);
            randomEmail=randomEmail+ranChar2.toString();
        }
        String emailEnding= alphaTLD[new Random().nextInt(alphaTLD.length)];
        randomEmail=randomEmail+emailEnding.toString();
        System.out.println(randomEmail);
        return randomEmail;
    }
    //判断邮箱账号是否已经登录
    public static Boolean IsExist_Email_account(String EmailAccount) throws UiObjectNotFoundException {
        MainAction.startApp(APPMenuPage.AppNameList[15]);
        waitTime(2);
        if(text_exists("Write email")) {
            clickById(EmailPage.EMAIL_I_WANT_TO);
            clickByText("Manage accounts");
            return text_exists(EmailAccount);
        }
        else if (
            text_exists("You can set up your account in just a few steps.") ||
                    !text_exists("Select an existing one in the list below:"));
            return false;
    }
    //登录邮箱账号
    public static void LogInEmail(String account,String password,String nickname) throws UiObjectNotFoundException, IOException {
        MainAction.killAppByPackage(APPMenuPage.PkgNameList[15]);
        MainAction.startApp(APPMenuPage.AppNameList[15]);
        waitTime(3);
        //登录有四种情况登录：1、没有其他账号；2、有其他账号；3、gmail账号；4、非gmail账号；其他136账号，189账号，QQ邮箱，sina邮箱，yahoo邮箱注册功能好像未实现就未考虑
        if(!EmailAction.IsExist_Email_account(account)){
            //已有其他账号，再添加账号
            if(text_exists("Add account")) {
                clickByText("Add account");
                //输入账号
                getObject2ById(EmailPage.EMAIL_ACCOUNT_INPUT).setText(account);
                waitTime(2);

                //gmail邮箱比其他邮箱多个确认步骤，判断是否是gmail邮箱
                if(account.endsWith("@gmail.com")) {
                    waitTime(3);
                    clickByText("Cancel");//gmail需要
                    waitUntilFind(EmailPage.EMAIL_INPUT_OK_TEXT, 2000);
                    clickById(EmailPage.EMAIL_INPUT_OK_TEXT);
                    waitUntilFind(EmailPage.EMAIL_PASSWORD_INPUT, 20000);//可能超时
                    //输入密码
                    getObject2ById(EmailPage.EMAIL_PASSWORD_INPUT).setText(password);
                    clickById(EmailPage.EMAIL_INPUT_OK_BUTTON);
                    waitTime(10);
                    clickById(EmailPage.EMAIL_INPUT_OK_BUTTON);
                    waitTime(5);
                    getObject2ById(EmailPage.EMAIL_ACCOUNT_NAME).setText("");
                    clickById(EmailPage.EMAIL_INPUT_OK_BUTTON);
                    //等待10s将收件箱刷新出来
                    waitTime(10);
                }
                //非gmail邮箱
                else {
                    waitUntilFind(EmailPage.EMAIL_INPUT_OK_TEXT, 2000);
                    clickById(EmailPage.EMAIL_INPUT_OK_TEXT);
                    waitUntilFind(EmailPage.EMAIL_PASSWORD_INPUT, 20000);
                    //输入密码
                    getObject2ById(EmailPage.EMAIL_PASSWORD_INPUT).setText(password);
                    clickById(EmailPage.EMAIL_INPUT_OK_BUTTON);
                    waitTime(10);
                    clickById(EmailPage.EMAIL_INPUT_OK_BUTTON);
                    waitTime(5);
                    getObject2ById(EmailPage.EMAIL_ACCOUNT_NAME).setText("");
                    clickById(EmailPage.EMAIL_INPUT_OK_BUTTON);
                    //等待10s将收件箱刷新出来
                    waitTime(10);
                }
            }
            //一个账号都没有
            else if(text_exists("You can set up your account in just a few steps.")){
                //输入账号
                getObject2ById(EmailPage.EMAIL_ACCOUNT_INPUT).setText(account);
                waitTime(2);

                //gmail邮箱比其他邮箱多个确认步骤，判断是否是gmail邮箱
                if(account.endsWith("@gmail.com")) {
                    waitTime(3);
                    clickByText("Cancel");//gmail需要
                    waitUntilFind(EmailPage.EMAIL_INPUT_OK_TEXT, 2000);
                    clickById(EmailPage.EMAIL_INPUT_OK_TEXT);
                    waitUntilFind(EmailPage.EMAIL_PASSWORD_INPUT, 20000);
                    //输入密码
                    getObject2ById(EmailPage.EMAIL_PASSWORD_INPUT).setText(password);
                    clickById(EmailPage.EMAIL_INPUT_OK_BUTTON);
                    waitTime(10);
                    clickById(EmailPage.EMAIL_INPUT_OK_BUTTON);
                    waitTime(5);
                    getObject2ById(EmailPage.EMAIL_ACCOUNT_NAME).setText("");
                    clickById(EmailPage.EMAIL_INPUT_OK_BUTTON);
                    //等待10s将收件箱刷新出来
                    waitTime(10);

                }
                //非gmail邮箱
                else {
                    waitUntilFind(EmailPage.EMAIL_INPUT_OK_TEXT, 2000);
                    clickById(EmailPage.EMAIL_INPUT_OK_TEXT);
                    waitUntilFind(EmailPage.EMAIL_PASSWORD_INPUT, 20000);
                    //输入密码
                    getObject2ById(EmailPage.EMAIL_PASSWORD_INPUT).setText(password);
                    clickById(EmailPage.EMAIL_INPUT_OK_BUTTON);
                    waitTime(10);//容易超时
                    clickById(EmailPage.EMAIL_INPUT_OK_BUTTON);
                    waitTime(5);
                    getObject2ById(EmailPage.EMAIL_ACCOUNT_NAME).setText("");
                    clickById(EmailPage.EMAIL_INPUT_OK_BUTTON);
                    //等待10s将收件箱刷新出来
                    waitTime(10);
                }
            }
        }
    }
    //退出所有邮箱账号
    public static void LogOutEmail() throws UiObjectNotFoundException {
        MainAction.killAppByPackage(APPMenuPage.PkgNameList[15]);
        MainAction.startApp(APPMenuPage.AppNameList[15]);
        waitTime(2);
        if(id_exists(EmailPage.EMAIL_WRITE_EMAIL)) {
            gDevice.pressHome();
            openAppliction("Settings");
            UiObject account = scrollAndGetUIObject("Accounts");
            account.click();
            UiObject mail = getLinearLayout(0,"android.widget.LinearLayout","android.widget.ImageView");
            mail.click();
            clickById(EmailPage.EMAIL_ICON_FRAME);
            clickByDescription("More options");
            clickByText("Remove account");
            clickById(EmailPage.EMAIL_REMOVEACCOUNT);
            if(!text_exists("Add account")){
                clickById(EmailPage.EMAIL_ICON_FRAME);
                clickByDescription("More options");
                clickByText("Remove account");
                clickById(EmailPage.EMAIL_REMOVEACCOUNT);
            }
        }
        gDevice.pressHome();
    }
    /**
     * 发送一封主题为Email_Subject，内容为Email_Body的新邮件给收件人TargetAddress
     * */
    public static void CreateNewEmail(String Email_Subject,String Email_Body,String TargetAddress) throws IOException, UiObjectNotFoundException {
        EmailAction.LogInEmail("chengduxike@hotmail.com","falcon@ckt2015","");
        gDevice.pressBack();
        clickById(EmailPage.EMAIL_WRITE_EMAIL);
        waitUntilFind(EmailPage.EMAIL_ADDRESS,6000);
        clickById(EmailPage.EMAIL_ADDRESS);
        //设置发送的地址
        waitTime(2);
        findObject(EmailPage.EMAIL_ADDRESS_INPUT).setText(TargetAddress);
        clickById(EmailPage.EMAIL_ADDRESS_CONFIRM);
        //设置邮件主题
        waitTime(2);
        findObject(EmailPage.EMAIL_SUBJECT).setText(Email_Subject);
        //设置邮件内容
        waitTime(2);
        findObject(EmailPage.EMAIL_BODY).setText(Email_Body);
        clickById(EmailPage.EMAIL_SEND);
        waitTime(3);
    }
    /**
     * 根据发件人账号SendEmailAccount，密码SendEmailPassword，
     * 发送一封主题为Email_Subject，
     * 内容Email_Body的新邮件给收件人TargetAddress
     * */
    public static void CreateNewEmailToAddress(String SendEmailAccount, String SendEmailPassword, String Email_Subject,String Email_Body, String TargetAddress) throws IOException, UiObjectNotFoundException {
        //发件人账号登录
        EmailAction.LogInEmail(SendEmailAccount,SendEmailPassword,"");
        gDevice.pressBack();
        clickById(EmailPage.EMAIL_WRITE_EMAIL);
        waitUntilFind(EmailPage.EMAIL_ADDRESS,4000);
        clickById(EmailPage.EMAIL_ADDRESS);
        //设置发送的地址
        waitTime(2);
        findObject(EmailPage.EMAIL_ADDRESS_INPUT).setText(TargetAddress);
        clickById(EmailPage.EMAIL_ADDRESS_CONFIRM);
        //设置邮件主题
        waitTime(2);
        findObject(EmailPage.EMAIL_SUBJECT).setText(Email_Subject);
        //设置邮件内容
        waitTime(2);
        findObject(EmailPage.EMAIL_BODY).setText(Email_Body);
        clickById(EmailPage.EMAIL_SEND);
        waitTime(3);
    }
    /**
     *备注：登陆后需要在手机上注册Google账号才能使用Play Store
     * 手机注册Chrome账号
     * */
    public static void RegisterChromeAccount(String EmailAccount,String PassWord) throws UiObjectNotFoundException, IOException {
        MainAction.startApp(APPMenuPage.AppNameList[4]);
        if(text_exists("Sign in to Chrome")){
                clickByText("SIGN IN");
                //等待与服务器连接
                waitTime(40);
                //1、如果与服务器连接失败
                if(text_exists("Couldn't sign in")&&
                        text_exists("There was a problem communicating with Google servers. \n" +
                        "\n" +
                        "Try again later.")){
                    clickByText("NEXT");
                    RegisterChromeAccount(EmailAccount,PassWord);
                }
                //2、如果没有停留在连接状态
                else if (!text_exists("Checking info…")){
                    findObject("identifierId").setText(EmailAccount);
                    clickById("identifierNext");
                    findObject("password").setText(PassWord);
                    clickByText("NEXT");
                    clickById("next");
                    waitTime(5);
                    clickByText("NEXT");
                    clickByText("CONTINUE");
                    clickByText("OK, GOT IT");
                }
                //3、如果依然停留在连接状态
                MainAction.killAppByPackage(APPMenuPage.AppNameList[4]);
                RegisterGoogleAccount(EmailAccount,PassWord);
            }
        }
    /**
     *备注：登陆后需要在手机上注册Google账号才能使用Play Store
     * 手机注册Chrome账号
     * */
    public static void RegisterGoogleAccount(String EmailAccount,String PassWord) throws UiObjectNotFoundException, IOException {
        MainAction.startApp(APPMenuPage.AppNameList[20]);
        if(text_exists("Let Google help you throughout the day")&&text_exists("SIGN IN")){
            clickByText("SIGN IN");
            //等待与服务器连接
            waitTime(40);
            //1、如果与服务器连接失败
            if(text_exists("Couldn't sign in")&&text_exists("There was a problem communicating with Google servers. \n" +
                    "\n" +
                    "Try again later.")){
                clickByText("NEXT");
                RegisterChromeAccount(EmailAccount,PassWord);
            }
            //2、如果没有停留在连接状态
            else if (!text_exists("Checking info…")){
                findObject("identifierId").setText(EmailAccount);
                clickById("identifierNext");
                waitTime(3);
                findObject("password").setText(PassWord);
                waitTime(3);
                clickByText("NEXT");
                clickById("next");
                waitTime(5);
                clickByText("NEXT");
                clickByText("CONTINUE");
                clickByText("OK, GOT IT");
            }
            //3、如果依然停留在连接状态
            MainAction.killAppByPackage(APPMenuPage.AppNameList[20]);
            RegisterGoogleAccount(EmailAccount,PassWord);
        }
    }
}
