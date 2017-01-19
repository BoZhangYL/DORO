package doro.action;

import android.os.RemoteException;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.hamcrest.Asst;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.page.InternetPage;
import doro.page.WifiPage;

/**
 * Created by bo.zhang on 2017/01/17   .
 */
public class InternetAction extends VP4 {
    private static UiObject SearchAddressBox =getObjectById(InternetPage.INTERNET_ADDRESS_BOX);
    private static UiObject WelcomeText = getObjectByText(InternetPage.INTERNET_WECOME_TXT);
    private static UiObject IWantToButton = getObjectByText(InternetPage.INTERNET_I_WANT_TO_BUTTON);
    private static UiObject SearchButton = getObjectById(InternetPage.INTERNET_SEARCH_BUTTN);
    private static UiObject Web_url = getObjectById(InternetPage.WEB_URL);
    private static UiObject ViewMyBookmarks = getObjectByText(InternetPage.
            SETTINGS_VIEW_MY_BOOKMARKS);
    private static UiObject SwitchPage = getObjectByText(InternetPage.SETTINGS_SWITCH_PAGE);
    private static UiObject ClearDataAndHistory = getObjectByText(InternetPage.
            SETTINGS_CLEAR_DATA_AND_HISTORY);
    private static UiObject Set = getObjectByText(InternetPage.SETTINGS_SET);
    private static UiObject ConfirmClearDataAndHistory = getObjectByText(InternetPage.
            REQUSET_CLEAR_DATA);
    private static UiObject Empty_Page = getObjectByText(InternetPage.EMPTPY_PAGE);
    private static UiObject HeadTiltle = getObjectById(InternetPage.HEAD_TITLE);
    private static UiObject EmptyBookmarks = getObjectByText(InternetPage.EMPTY_BOOKMARKS_PAGE);
    private static UiCollection BookMarksList = new UiCollection(new UiSelector().
            resourceId(InternetPage.BOOKMARKS_LIST));
    private static UiSelector BookmarksIconLabel = new UiSelector().resourceId(InternetPage.
            BOOKMARKS_ICON_AND_LABEL);
    private static UiSelector BookmarksIcon = new UiSelector().
            resourceId(InternetPage.BOOKMARKS_ICON);
    private static UiSelector BookmarksLabel = new UiSelector().
            resourceId(InternetPage.BOOKMARKS_LABEL);
    private static UiObject NOButton =getUiObjectByText(InternetPage.NO_BUTTON);
    private static UiObject AddBookmarksButton = getObjectByText(InternetPage.
            ADD_TO_BOOKMARKS_BUTTON);
    private static UiObject AddButton = getObjectByText(InternetPage.ADD_BUTTON);
    private static UiObject EditBookmarksName = getObjectByClass(InternetPage.EDIT_BOOKMARKS_NAME);
    /*
    * 检查添加书签是否成功
    * */
    public static void checkAddBookmarks(String BooksName){
        openInternetApp();
        clickViewMyBookmarksButton();
        boolean isAdd =false;
        try {
            for(int i=0;i<BookMarksList.getChild(BookmarksLabel).getChildCount();i++){
                if(BooksName.equals(BookMarksList.getChildByInstance(BookmarksLabel,i).getText())){
                    isAdd =true;
                }
            }
            Asst.assertTrue("添加的名称为个"+BooksName+"的书签的图标不存在，添加书签失败！",
                    isAdd);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    * 添加一个书签
    * */
    public static  void addOneBookmarks(String BookmarksName){
        dismissAccessReq();
        try {
            IWantToButton.clickAndWaitForNewWindow();
            AddBookmarksButton.clickAndWaitForNewWindow();
            EditBookmarksName.setText(BookmarksName);
            AddButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 取消页面的权限请求
    * */
    private static void dismissAccessReq(){
        if(NOButton.exists()){
            try {
                NOButton.clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    /*
    * 检查所有书签
    * */
    public static void ckeckAllMyBookmarksPage(){
        if(!BookMarksList.exists()){
            Asst.assertTrue("空浏览器的书签的提示不对", EmptyBookmarks.exists());
        }else{
            try {
                for(int i=0;i<BookMarksList.getChild(BookmarksIcon).getChildCount();i++){
                    Asst.assertTrue("第"+i+"个书签的图标不存在",
                            BookMarksList.getChildByInstance(BookmarksIcon,i).exists());
                    Asst.assertTrue("第"+i+"个书签的label不存在",
                            BookMarksList.getChildByInstance(BookmarksLabel,i).exists());
                }
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * 点击书签按钮
    * */
    public static void clickViewMyBookmarksButton(){
        try {
            IWantToButton.clickAndWaitForNewWindow();
            ViewMyBookmarks.clickAndWaitForNewWindow();
            Asst.assertEquals("进入书签页面成功!",HeadTiltle.getText(),
                    InternetPage.HRADPAGE_MY_BOOKMARKS);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 点击清除按钮，清除数据和历史
    * */
    public static void clickClearDataAndHistoryButton(){
        try {
            IWantToButton.clickAndWaitForNewWindow();
            ClearDataAndHistory.clickAndWaitForNewWindow();
            Asst.assertTrue("确认清除数据提示框不存在", ConfirmClearDataAndHistory.exists());
            getObjectByText(InternetPage.OK_BUTTON).clickAndWaitForNewWindow();
            IWantToButton.clickAndWaitForNewWindow();
            SwitchPage.clickAndWaitForNewWindow();
            Asst.assertTrue("清除数据和历史不成功！", Empty_Page.exists());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 搜索文字
    * */
    public static void searchWord(){
        try {
            SearchAddressBox.setText(InternetPage.SEARCH_WORD);
            SearchButton.clickAndWaitForNewWindow();
            if(HeadTiltle.exists()){
                SearchButton.clickAndWaitForNewWindow();
            }
            Asst.assertTrue("点击搜索按钮后没有进入搜索页面", !HeadTiltle.exists());
            Asst.assertEquals("搜索文字出错！",InternetPage.SEARCH_WORD_URL,Web_url.getText());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    /*
    * 搜索网页
    * */
    public static void searchAddress(){
        try {
            SearchAddressBox.setText(InternetPage.SEARCH_ADDRESS);
            if(HeadTiltle.exists()){
                SearchButton.clickAndWaitForNewWindow();
            }
            SearchButton.clickAndWaitForNewWindow();
            Asst.assertTrue("点击搜索按钮后没有进入搜索页面", !HeadTiltle.exists());
            if(getObjectByText(InternetPage.BAIDU_LOCATION).exists()){
                getObjectByText("No").click();
            }
            Asst.assertEquals("搜索网址出错！",InternetPage.SEARCH_ADDRESS_URL,Web_url.getText());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 切换到横屏模式
    * */
    public static void changeToLandscapeMode(){
        try {
            if(gDevice.isNaturalOrientation()) {
                int width =gDevice.getDisplayWidth();
                gDevice.setOrientationLeft();
                waitTime(5);
                Asst.assertEquals("切换到横屏显示失败",width ,gDevice.getDisplayHeight());
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /*
    * 切换到竖屏
    * */
    public static void changeToPortraitMode(){
        try {
            if(!gDevice.isNaturalOrientation()) {
                int width =gDevice.getDisplayWidth();
                gDevice.setOrientationNatural();
                waitTime(5);
                Asst.assertEquals("切换到竖屏显示失败",width ,gDevice.getDisplayHeight());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    /*
    *取消开始向导
    * */
    public static void dismissStartup(){
        WifiAction WifiAction=new WifiAction();
        openAppliction(WifiPage.SETTINGS);
        clickByText(WifiPage.WIFI);
        WifiAction.turnOnWifi(true);
        WifiAction.connectWifi("CKT","ck88888!");
    }

    /*
    * 检查概览页面
    * */
    public static void checkOverView(){
        try {
            Asst.assertTrue("顶端的文本内容不是显示为‘Welcome to internet！’",
                    WelcomeText.exists());
            Asst.assertTrue("焦点默认没有在搜索框",SearchAddressBox.isFocused());
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
