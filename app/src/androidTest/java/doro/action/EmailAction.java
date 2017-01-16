package doro.action;

import android.support.test.uiautomator.UiObjectNotFoundException;

import java.io.IOException;
import java.util.Random;

import ckt.base.VP4;
import doro.page.APPMenuPage;
import doro.page.EmailPage;

/**
 * Created by Caibing.Yin on 2017/1/14.
 */

public class EmailAction extends VP4 {
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
    //退出邮箱账号
    public static void LogInEmail() throws UiObjectNotFoundException, IOException {
        MainAction.killAppByPackage(APPMenuPage.PkgNameList[15]);
        MainAction.startApp(APPMenuPage.AppNameList[15]);
        waitTime(3);
        if(text_exists("You can set up your account in just a few steps.")){
            getObject2ById(EmailPage.EMAIL_ACCOUNT_INPUT).setText("cktfalcontest@gmail.com");
            waitUntilFind(EmailPage.EMAIL_INPUT_OKTEXT,2000);
            clickById(EmailPage.EMAIL_INPUT_OKTEXT);
            waitUntilFind(EmailPage.EMAIL_PASSWORD_INPUT,8000);
            getObject2ById(EmailPage.EMAIL_PASSWORD_INPUT).setText("falcon@ckt2014");
            clickById(EmailPage.EMAIL_INPUT_OKBUTTON);
            //EMAIL程序崩溃，暂时不忙写
        }
    }

}
