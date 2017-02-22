package doro.action;

import android.graphics.Rect;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.hamcrest.Asst;

import ckt.base.VP4;
import doro.page.EmailPage;
import doro.page.WifiPage;

/**
 * Created by Caibing.Yin on 2017/1/14.
 */

public class EmailAction extends VP4 {
    private static UiCollection EmailList =
            new UiCollection(new UiSelector().resourceId(EmailPage.EMAIL_LIST));
    private static UiSelector Emails = new UiSelector().className(EmailPage.EMAIL_VIEW);

    /*
    * openSentEmail
    * */
    public static void openSentEmail() {

        try {

            if (getObjectById("com.doro.apps.email:id/dismiss_icon").exists()) {
                getObjectById("com.doro.apps.email:id/dismiss_icon").clickAndWaitForNewWindow();
            }
            gDevice.findObject(new UiSelector().className("android.view.View").instance(1)).
                    clickAndWaitForNewWindow();
            Asst.assertTrue("Not in Email Reply / Forward view ",
                    getObjectByText("Reply / Forward").exists());
            //getObjectById(EmailPage.EMAIL_LIST, 2).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * open OutBox email
    * */
    public static void openOutEmail() {
        if (getWifiState()) {
            clickWifiButton();
        }
//        if (!getMobileDataState()) {
//            clickMobileDataButton();
//        }
        if (!getObjectById(EmailPage.EMAIL_LIST, 1).exists()) {
            NewEmailByAddress();
            sendEmail();
        }
        try {
            if (getObjectById("com.doro.apps.email:id/dismiss_icon").exists()) {
                getObjectById("com.doro.apps.email:id/dismiss_icon").clickAndWaitForNewWindow();
            }
            gDevice.findObject(new UiSelector().className("android.view.View").instance(1)).
                    clickAndWaitForNewWindow();
            Asst.assertTrue("Not in Email edit view ", getObjectByText("Edit").exists());
            /*UiObject Emails =  gDevice.findObject(new UiSelector().resourceId("com.doro.apps." +
                    "email:id/conversation_list_parent_frame").index(0).index(1).index(0));
            Emails.clickAndWaitForNewWindow();
            Asst.assertTrue("Not in Email edit view ", getObjectByText("Edit").exists());*/

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 退出OutBox
    * */
    public static void exitOutBox() {
        if (!getWifiState()) {
            clickWifiButton();
        }
        if (!getMobileDataState()) {
            clickMobileDataButton();
        }
    }

    /*
    * 得到Wifi状态
    * */
    private static boolean getWifiState() {
        boolean WifiState = true;
        gDevice.openQuickSettings();
        UiCollection QuickSettingList = new UiCollection(new
                UiSelector().resourceId("com.android.systemui:id/tile_page"));
        UiSelector QucickSets = new UiSelector().className("android.widget.Button");
        try {
            //UiObject WifiTiles = gDevice.findObject(new UiSelector().resourceId(EmailPage.NOTIFCATION_LIST).index(0));
            UiObject WifiTiles = QuickSettingList.getChildByInstance(QucickSets, 0);
            String WifiDes = WifiTiles.getContentDescription();
            String[] wifiCurrentD = WifiDes.split(",");
            if (wifiCurrentD[0].equals("Wifi On")) {
                WifiState = true;
            } else
                WifiState = false;
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        gDevice.swipe(gDevice.getDisplayWidth() / 2, gDevice.getDisplayHeight(),
                gDevice.getDisplayWidth() / 2, 0, 20);
        return WifiState;
    }

    /*
    * 点击wifi快捷按钮
    * */
    public static void clickWifiButton() {
        gDevice.openQuickSettings();
        waitTime(2);
        UiCollection QuickSettingList = new UiCollection(new
                UiSelector().resourceId("com.android.systemui:id/tile_page"));
        UiSelector QucickSets = new UiSelector().className("android.widget.Button");
        //UiObject WifiTiles = gDevice.findObject(new UiSelector().textContains(EmailPage.QUICK_SET).index(0));
        try {
            UiObject WifiTiles = QuickSettingList.getChildByInstance(QucickSets, 0);
            WifiTiles.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        gDevice.swipe(gDevice.getDisplayWidth() / 2, gDevice.getDisplayHeight(),
                gDevice.getDisplayWidth() / 2, 0, 5);
    }

    /*
    * 得到数据连接状态
    * */
    private static boolean getMobileDataState() {
        boolean MobileDataState = true;
        gDevice.openQuickSettings();
        waitTime(2);
        UiCollection QuickSettingList = new UiCollection(new
                UiSelector().resourceId("com.android.systemui:id/tile_page"));
        UiSelector QucickSets = new UiSelector().className("android.widget.Button");
        try {
            //UiObject MobileDataTiles = gDevice.findObject(new UiSelector().textContains(EmailPage.QUICK_SET).index(1));
            UiObject MobileDataTiles = QuickSettingList.getChildByInstance(QucickSets, 1);
            String[] WifiDes = MobileDataTiles.getContentDescription().split("\\.");
            if (WifiDes[0].equals("Mobile Mobile Data On")) {
                MobileDataState = true;
            } else
                MobileDataState = false;
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        gDevice.swipe(gDevice.getDisplayWidth() / 2, gDevice.getDisplayHeight(),
                gDevice.getDisplayWidth() / 2, 0, 5);
        return MobileDataState;
    }

    /*
    * 点击数据连接快捷按钮
    * */
    public static void clickMobileDataButton() {
        gDevice.openQuickSettings();
        waitTime(2);
        UiCollection QuickSettingList = new UiCollection(new
                UiSelector().resourceId("com.android.systemui:id/tile_page"));
        UiSelector QucickSets = new UiSelector().className("android.widget.Button");
        //UiObject MobileDataTiles  = gDevice.findObject(new UiSelector().textContains(EmailPage.QUICK_SET).index(1));
        try {
            UiObject MobileDataTiles = QuickSettingList.getChildByInstance(QucickSets, 1);
            MobileDataTiles.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        gDevice.swipe(gDevice.getDisplayWidth() / 2, gDevice.getDisplayHeight(),
                gDevice.getDisplayWidth() / 2, 0, 10);
    }

    /*
    * Zoom in Email
    * */
    public static void zoomInEmail() {
        try {
            waitTime(5);
            Rect BeforeZoom = getUiObjectByDes("test Email success").getBounds();
            getObjectByText(EmailPage.I_WANT_TO_BUTTON).clickAndWaitForNewWindow();
            getObjectByText(EmailPage.ZOOM_IN).clickAndWaitForNewWindow();
            Rect CurrentZoom = getUiObjectByDes("test Email success").getBounds();
            Asst.assertTrue("Zoom in fail !", (CurrentZoom.bottom - CurrentZoom.top) >
                    (BeforeZoom.bottom - BeforeZoom.top));
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * delete Email
    * */
    public static void deleteEmail() {
        try {
            getObjectByText(EmailPage.I_WANT_TO_BUTTON).clickAndWaitForNewWindow();
            getObjectByText("Delete this email").clickAndWaitForNewWindow();
            Asst.assertTrue("there is no delete prompt",
                    getObjectByText(EmailPage.DELETE_CONVERSATION_PROMPT).exists());
            getObjectByText("Yes").click();
            waitTime(10);
            if (!getObjectById(EmailPage.EMPTY_EMAIL_ICON).exists()
                    && !getObjectById(EmailPage.EMPTY_EMAIL_TXT).exists()) {
                Asst.assertTrue("delete fail ", getObjectByText(EmailPage.WRITE_EMAIL_BUTTON).exists());
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }


    /*
    * outbox
    * */
    public static void switchToOutBox() {
        try {
            getObjectByText(EmailPage.I_WANT_TO_BUTTON).clickAndWaitForNewWindow();
            getObjectByText(EmailPage.DISPLAY).clickAndWaitForNewWindow();
            if (!getObjectByText(EmailPage.OUTBOX).exists())
                scrollToEnd(10);
            getObjectByText(EmailPage.OUTBOX).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * sentBox
    * */
    public static void switchToSentBox() {
        try {
            getObjectByText(EmailPage.I_WANT_TO_BUTTON).clickAndWaitForNewWindow();
            getObjectByText(EmailPage.DISPLAY).clickAndWaitForNewWindow();
            if (!getObjectByText(EmailPage.SENTBOX).exists())
                scrollToEnd(10);
            getObjectByText(EmailPage.SENTBOX).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }


    /*
    * 进入Email 菜单
    * */
    public static void switchToEmailMenu() {
        try {
            getObjectByText(EmailPage.I_WANT_TO_BUTTON).clickAndWaitForNewWindow();
            Asst.assertTrue("Display option not exists",
                    getObjectByText(EmailPage.DISPLAY).exists());
            Asst.assertTrue("Manage accounts not exists",
                    getObjectByText(EmailPage.MANAGE_ACCOUNTS).exists());
            Asst.assertTrue("Open settings not exists",
                    getObjectByText(EmailPage.OPEN_SETTINGS_OPTION).exists());
            Asst.assertTrue("Delete option not exxists",
                    getObjectByText(EmailPage.DELETE).exists());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 等待接受新邮件
    * */
    public static void waitReceiveEmail() {
        waitTime(360);
        VP4.unLock();
        gDevice.click(gDevice.getDisplayWidth() / 2, 0);
        gDevice.click(gDevice.getDisplayWidth() / 2, 0);
        Asst.assertTrue("whitout new Email!", getObjectByText("Email").exists());
        try {
            getObjectByText("Email").clickAndWaitForNewWindow();
            Asst.assertEquals("Can not open Email by notfication", EmailPage.EMAIL_PACKAGE,
                    gDevice.getCurrentPackageName());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }


    /*
    * 允许Email的访问权限
    * */
    public static void accessEmail() {
        if (getObjectById(EmailPage.ACCESS_RIGHT).exists()) {
            try {
                while (getObjectByText("ALLOW").exists())
                    getObjectByText("ALLOW").clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * 改变自动同步时间5分钟
    * */
    public static void changeSyncTimeToFiveMinutes() {
        try {
            getObjectByText(EmailPage.I_WANT_TO_BUTTON).clickAndWaitForNewWindow();
            getObjectByText(EmailPage.MANAGE_ACCOUNTS).clickAndWaitForNewWindow();
            getObjectByText(EmailPage.EMAIL_ACCOUNT_1).clickAndWaitForNewWindow();
            getObjectByText(EmailPage.DATA_USAGE).clickAndWaitForNewWindow();
            if (getObjectByText(EmailPage.EVERY_5_MINUTES).exists()) {
                getObjectByText(EmailPage.EVERY_15_MINUTES).clickAndWaitForNewWindow();
                getObjectByText(EmailPage.EVERY_5_MINUTES).clickAndWaitForNewWindow();
                getObjectByText(EmailPage.EMAIL_INPUT_OK_TEXT).clickAndWaitForNewWindow();
            }
            waitTime(5);
            pressKey("Back/Back");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }


    /*
    * 检查接受到的邮件
    * */
    public static void checkReceive() {
        UiObject newReceiveEmail = getObjectById(EmailPage.EMAIL_LIST, 1);
        try {
            String EmailTitle = newReceiveEmail.getContentDescription();
            String[] Title = EmailTitle.split(",");
            Asst.assertEquals("Without new Email", "conversation unread", Title[2]);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 刷新出一封新的email邮件
    * */
    public static void refreshNewEmail() {
        try {
            int i = EmailList.getChildCount();
            int j = 0;
            while (i <= EmailList.getChildCount() && j < 5) {
                refreshPage();
                j++;
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }


    /*
    * 关闭确认发送功能
    * */
    public static void disabledConfirmSending() {
        try {
            getObjectByText(EmailPage.I_WANT_TO_BUTTON).clickAndWaitForNewWindow();
            getObjectByText(EmailPage.OPEN_SETTINGS_OPTION).clickAndWaitForNewWindow();
            getObjectByText(EmailPage.CONFIRM_SENDING_OPTION).clickAndWaitForNewWindow();
            if (getObjectById(EmailPage.ENABLED_SWITCH).isChecked()) {
                getObjectById(EmailPage.ENABLED_SWITCH).click();
            }
            getObjectByText(EmailPage.CONFIRM_BUTTON).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 检查确认发送功能
    * */
    public static void checkConfirmSending() {
        Asst.assertTrue("Send this message? not exist", getObjectByText(EmailPage.
                CONFIRM_SENDING_TXT).exists());
        try {
            getObjectByText(EmailPage.CANCEL_BUTTON).clickAndWaitForNewWindow();
            Asst.assertTrue("Cancel send email fail",
                    getObjectById(EmailPage.EDIT_EMAIL_VIEW).exists());
            getObjectByText(EmailPage.SEND_BUTTON).clickAndWaitForNewWindow();
            Asst.assertTrue("tap Send button again,Send this message? not exist",
                    getObjectByText(EmailPage.CONFIRM_SENDING_TXT).exists());
            getObjectByText(EmailPage.EMAIL_INPUT_OK_TEXT).clickAndWaitForNewWindow();
            waitTime(10);
            Asst.assertTrue("tap ok button ,send email fail", EmailList.exists());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 打开发送确认
    * */
    public static void enabkedConfirmSending() {
        try {
            getObjectByText(EmailPage.I_WANT_TO_BUTTON).clickAndWaitForNewWindow();
            getObjectByText(EmailPage.OPEN_SETTINGS_OPTION).clickAndWaitForNewWindow();
            getObjectByText(EmailPage.CONFIRM_SENDING_OPTION).clickAndWaitForNewWindow();
            if (!getObjectById(EmailPage.ENABLED_SWITCH).isChecked()) {
                getObjectById(EmailPage.ENABLED_SWITCH).click();
            }
            getObjectByText(EmailPage.CONFIRM_BUTTON).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 打开邮箱应用
    * */
    public static void openEmailApp() {
        openAppliction(EmailPage.EMAIL);
        Asst.assertEquals("Open Email fail", EmailPage.EMAIL_PACKAGE,
                gDevice.getCurrentPackageName());
    }

    /*
    * 退出邮箱应用
    * */
    public static void exitEmailApp() {
        try {
            pressKey("Home");
            Asst.assertTrue("exit Email Fail", !EmailPage.EMAIL_PACKAGE.equals(
                    gDevice.getCurrentPackageName()));
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 登陆163邮箱
    * */
    public static void loginEmail() {
        WifiAction WifiAction = new WifiAction();
        openAppliction(WifiPage.SETTINGS);
        clickByText(WifiPage.WIFI);
        WifiAction.turnOnWifi(true);
        WifiAction.connectWifi("CKT", "ck88888!");
        openAppliction(EmailPage.EMAIL);
        if (!getObjectByText(EmailPage.WRITE_EMAIL_BUTTON).exists()) {
            UiObject Address = getObjectById(EmailPage.EMAIL_ACCOUNT_INPUT);
            UiObject Password = getObjectById(EmailPage.EMAIL_PASSWORD_INPUT);
            UiObject OKButton = getObjectByText(EmailPage.EMAIL_INPUT_OK_TEXT);
            try {
                Address.click();
                Address.setText(EmailPage.EMAIL_ACCOUNT_1);
                OKButton.clickAndWaitForNewWindow();
                Password.click();
                Password.setText(EmailPage.EMAIL_PASSWORD_1);
                OKButton.clickAndWaitForNewWindow();
                OKButton.clickAndWaitForNewWindow();
                OKButton.clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * 刷新页面
    * */
    public static void refreshPage() {
        gDevice.swipe(gDevice.getDisplayWidth() / 2, gDevice.getDisplayHeight() / 3,
                gDevice.getDisplayWidth() / 2, gDevice.getDisplayHeight() * 2 / 3, 10);
        int i = 0;
        try {
            int j = EmailList.getChild(Emails).getChildCount();
            while (j < EmailList.getChild(Emails).getChildCount() && i < 30) {
                waitTime(10);
                i++;
            }
            Asst.assertTrue("刷新时间超过5分钟！", i < 30);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    * 等待邮箱加载
    * */
    public static void waitLoad() {
        try {
            Asst.assertTrue("Load Email Fail", EmailList.getChildCount() > 1);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    * 创建一份通过邮箱地址发送的邮件
    * */
    public static void NewEmailByAddress() {
        try {
            getObjectByText(EmailPage.WRITE_EMAIL_BUTTON).clickAndWaitForNewWindow();
            getObjectByText(EmailPage.SEND_BY_ADDRESS).clickAndWaitForNewWindow();
            getObjectById(EmailPage.INPUT_SEND_ADDRESS).setText(EmailPage.EMAIL_ACCOUNT_1);
            getObjectByText(EmailPage.CONFIRM_BUTTON).clickAndWaitForNewWindow();
            setSubject("test");
            setEmailBody("test Email success");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 设置标题
    * */
    private static void setSubject(String subject) {
        try {
            getObjectById(EmailPage.INPUT_EMAIL_SUBJECT).click();
            getObjectById(EmailPage.INPUT_EMAIL_SUBJECT).setText(subject);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 设置邮件正文
    * */
    private static void setEmailBody(String text) {
        try {
            getObjectById(EmailPage.INPUT_EMAIL_BODY).click();
            getObjectById(EmailPage.INPUT_EMAIL_BODY).setText(text);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 发送邮件
    * */
    public static void sendEmail() {
        try {
            getObjectByText(EmailPage.SEND_BUTTON).clickAndWaitForNewWindow();
            waitTime(10);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    //尹才兵
  /*
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
                if(getObjectByText(APPMenuPage.REGISTER_GAIL_PROMPT).exists())
                clickByText("Cancel");//gmail需要

                //gmail邮箱比其他邮箱多个确认步骤，判断是否是gmail邮箱
                if(account.endsWith("@gmail.com")) {
                    waitTime(3);
                    if(getObjectByText("Cancel").exists())
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
                if(getObjectByText(APPMenuPage.REGISTER_GAIL_PROMPT).exists())
                    clickByText("Cancel");//gmail需要
                //gmail邮箱比其他邮箱多个确认步骤，判断是否是gmail邮箱
                if(account.endsWith("@gmail.com")) {
                    waitTime(3);
                    if(getObjectByText(APPMenuPage.REGISTER_GAIL_PROMPT).exists())
                    clickByText("Cancel");//gmail需要
                    waitUntilFind(EmailPage.EMAIL_INPUT_OK_TEXT, 2000);
                    clickById(EmailPage.EMAIL_INPUT_OK_TEXT);
                    waitUntilFind(EmailPage.EMAIL_PASSWORD_INPUT, 20000);
                    if(getObjectByText(APPMenuPage.REGISTER_GAIL_PROMPT).exists())
                        clickByText("Cancel");//gmail需要
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
    *//**
     * 发送一封主题为Email_Subject，内容为Email_Body的新邮件给收件人TargetAddress
     * *//*
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
    *//**
     * 根据发件人账号SendEmailAccount，密码SendEmailPassword，
     * 发送一封主题为Email_Subject，
     * 内容Email_Body的新邮件给收件人TargetAddress
     * *//*
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
    *//**
     *备注：登陆后需要在手机上注册Google账号才能使用Play Store
     * 手机注册Chrome账号
     * *//*
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
    *//**
     *备注：登陆后需要在手机上注册Google账号才能使用Play Store
     * 手机注册Chrome账号
     * *//*
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
    }*/
}
