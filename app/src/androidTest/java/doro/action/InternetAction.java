package doro.action;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.hamcrest.Asst;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.page.InternetPage;

/**
 * Created by bo.zhang on 2017/01/17   .
 */
public class InternetAction extends VP4 {
    private static UiObject SearchAddressBox =getObjectById(InternetPage.INTERNET_SEARCH_BUTTN);
    private static UiObject WelcomeText = getObjectByText(InternetPage.INTERNET_WECOME_TXT);
    private static UiObject IWantToButton = getObjectByText(InternetPage.INTERNET_I_WANT_TO_BUTTON);
    private static UiObject SearchButton = getObjectById(InternetPage.INTERNET_SEARCH_BUTTN);

    /*
    * 检查概览页面
    * */
    public static void checkOverView(){
        try {
            Asst.assertTrue("顶端的文本内容不是显示为‘Welcome to internet！’",
                    WelcomeText.exists());
            Asst.assertTrue("焦点默认没有在搜索框",WelcomeText.isFocused());
            Asst.assertEquals("搜索框内容不是显示为：'Search word/address'",
                    InternetPage.INTERNET_CONTENT_SEARCH_BOX,SearchAddressBox.getText());

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    * 打开浏览器
    * */
    public static void openInternetApp(){
        VP4.openAppliction(InternetPage.INTERNET_APP_NAME);
        Asst.assertEquals("打开浏览器失败",InternetPage.INTERNET_PACKAGE_NAME,
                gDevice.getCurrentPackageName());
    }

    /*
    * 退出浏览器
    * */
    public static void exitInternetApp(){

        try {
            openInternetApp();
            pressKey("Back");
            Asst.assertTrue("退出浏览器失败",!InternetPage.INTERNET_PACKAGE_NAME.equals(
                    gDevice.getCurrentPackageName()));
            openInternetApp();
            pressKey("Home");
            Asst.assertTrue("退出浏览器失败",!InternetPage.INTERNET_PACKAGE_NAME.equals(
                    gDevice.getCurrentPackageName()));
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}
