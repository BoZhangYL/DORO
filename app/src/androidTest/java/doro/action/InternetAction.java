package doro.action;

import android.os.RemoteException;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.hamcrest.Asst;

import ckt.base.VP4;
import doro.page.InternetPage;
import doro.page.ViewPage;
import doro.page.WifiPage;

/**
 * Created by bo.zhang on 2017/01/17   .
 */
public class InternetAction extends VP4 {
    private static UiObject SearchAddressBox =getObjectById(InternetPage.INTERNET_ADDRESS_BOX);
    //搜索栏
    private static UiObject WelcomeText = getObjectByText(InternetPage.INTERNET_WECOME_TXT);
    //欢迎文字
    private static UiObject IWantToButton = getObjectByText(InternetPage.INTERNET_I_WANT_TO_BUTTON);
    //I want to 按钮
    private static UiObject SearchButton = getObjectById(InternetPage.INTERNET_SEARCH_BUTTN);
    //搜索按钮
    private static UiObject Web_url = getObjectById(InternetPage.WEB_URL);
    //网址栏
    private static UiObject ViewMyBookmarks = getObjectByText(InternetPage.
            SETTINGS_VIEW_MY_BOOKMARKS);
    //查看书签选项
    private static UiObject SwitchPage = getObjectByText(InternetPage.SETTINGS_SWITCH_PAGE);
    //进入我的页面选项
    private static UiObject ClearDataAndHistory = getObjectByText(InternetPage.
            SETTINGS_CLEAR_DATA_AND_HISTORY);
    //清除数据和历史选项
    private static UiObject Set = getObjectByText(InternetPage.SETTINGS_SET);
    //设置选项
    private static UiObject ConfirmClearDataAndHistory = getObjectByText(InternetPage.
            REQUSET_CLEAR_DATA);
    //确认清除数据和历史对话框
    private static UiObject Empty_Page = getObjectByText(InternetPage.EMPTPY_PAGE);
    //空页面
    private static UiObject HeadTiltle = getObjectById(InternetPage.HEAD_TITLE);
    //标题栏
    private static UiObject EmptyBookmarks = getObjectByText(InternetPage.EMPTY_BOOKMARKS_PAGE);
    //空书签
    private static UiCollection BookMarksList = new UiCollection(new UiSelector().
            resourceId(InternetPage.BOOKMARKS_LIST));
    //书签列表
    private static UiSelector BookmarksIconLabel = new UiSelector().resourceId(InternetPage.
            BOOKMARKS_ICON_AND_LABEL);
    //书签图标和书签名
    private static UiSelector BookmarksIcon = new UiSelector().
            resourceId(InternetPage.BOOKMARKS_ICON);
    //书签图标
    private static UiSelector BookmarksLabel = new UiSelector().
            resourceId(InternetPage.BOOKMARKS_LABEL);
    //书签名字
    private static UiObject NOButton =getUiObjectByText(InternetPage.NO_BUTTON);
    //no按钮
    private static UiObject AddBookmarksButton = getObjectByText(InternetPage.
            ADD_TO_BOOKMARKS_BUTTON);
    //添加书签按钮
    private static UiObject AddButton = getObjectByText(InternetPage.ADD_BUTTON);
    //添加按钮
    private static UiObject EditBookmarksName = getObjectByClass(InternetPage.EDIT_BOOKMARKS_NAME);
    //编辑书签名字编辑框
    private static UiObject DeleteBookmarks = getObjectByText(InternetPage.DeleteBookmarks);
    //删除书签选项
    private static UiObject ConfirmButton = getObjectByText(InternetPage.CONFIRM);
    //确认按钮
    private static UiCollection PageList = new UiCollection(new UiSelector().resourceId(InternetPage.
            PAGE_ALL_INDICATOR));
    //页面列表
    private static UiSelector Page = new UiSelector().className(InternetPage.PAGE_INDICATOR);
    //页面
    private static UiObject SearchPAgeButton =getObjectByText(InternetPage.SEARCH_PAGE_BUTTON);
    private static UiObject GoToHomePageButton = getObjectByText(InternetPage.GO_TO_HOME_PAGE);
    private static UiObject ClosePageButton = getObjectByText(InternetPage.CLOSE_PAGE_BUTTON);
    private static UiCollection ViewPages = new UiCollection(new UiSelector().
            resourceId(InternetPage.VIEW_PAGE));
    private static UiSelector PageDeleteCheckbox = new UiSelector().resourceId(InternetPage.
            DELETE_CHECKBOX_PAGE);
    private static UiObject DleteAllPageButton = getObjectById(InternetPage.DELETE_ALL_CHECKBOX);
    /*
    * 删除页面
    * */
    public static void deleteOnePage(){
        try {
            IWantToButton.clickAndWaitForNewWindow();
            ClosePageButton.clickAndWaitForNewWindow();
            ViewPages.getChildByInstance(PageDeleteCheckbox,0).clickAndWaitForNewWindow();
            ConfirmButton.clickAndWaitForNewWindow();
            getObjectByText(InternetPage.OK_BUTTON).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
   * 删除所有页面
   * */
    public static void DelteAllPage(){
        try {
            IWantToButton.clickAndWaitForNewWindow();
            ClosePageButton.clickAndWaitForNewWindow();
            DleteAllPageButton.clickAndWaitForNewWindow();
            ConfirmButton.clickAndWaitForNewWindow();
            getObjectByText(InternetPage.OK_BUTTON).clickAndWaitForNewWindow();
            Asst.assertTrue("删除所有page失败",(!PageList.exists()) &&
                    (getObjectByText(InternetPage.HEADPAGE_MY_PAGES).exists()));
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    *检查删除一个页面
    * */
    public static void checkDeleteOnePage(){
        try {
            while(PageList.getChildCount()>4){
                PageList.getChildByInstance(Page,PageList.getChildCount()-1).clickAndWaitForNewWindow();
            }
            int number= Integer.valueOf(PageList.getChildByInstance(Page, PageList.getChildCount()-1).
                    getText());
            Asst.assertEquals("最大页面数不是" + InternetPage.MAX_PAGES + "个",
                    InternetPage.MAX_PAGES -1, number);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 检查空的页面
    * */
    public static void ckeckEmptyPages(){
        Asst.assertTrue("空页面的提示语不正确！",getObjectByText(InternetPage.EMPTY_PAGE_PROMPT)
                .exists());
        Asst.assertTrue("搜索页面按钮不存在",SearchPAgeButton.exists());
    }

    /*
    *访问百度网页15次
     *  */
    public static void AddPage(){
        openInternetApp();
        for(int i = 0; i<15;i++) {
            try {
                SearchAddressBox.setText(InternetPage.SEARCH_ADDRESS);
                SearchButton.clickAndWaitForNewWindow();
                if (HeadTiltle.exists()) {
                    SearchButton.clickAndWaitForNewWindow();
                }
                Asst.assertTrue("点击搜索按钮后没有进入搜索页面", !HeadTiltle.exists());
                IWantToButton.clickAndWaitForNewWindow();
                GoToHomePageButton.clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
        }


    /*
    * 检查最大的页面数
    * */
    public static void checkMaxPages(){
        try {
            AddPage();
            openInternetApp();
            goToSwitchPage();
            while(PageList.getChildCount()>4){
                PageList.getChildByInstance(Page,PageList.getChildCount()-1).clickAndWaitForNewWindow();
            }
        int number= Integer.valueOf(PageList.getChildByInstance(Page, PageList.getChildCount()-1).
                    getText());
         Asst.assertEquals("最大页面数不是" + InternetPage.MAX_PAGES + "个",
                 InternetPage.MAX_PAGES, number);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    * 检查最大页面数
    * */


    /*
    * 进入switchPAge 页面
    * */
    public static void goToSwitchPage(){
        try {
            IWantToButton.clickAndWaitForNewWindow();
            SwitchPage.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 检查Switch Page
    * */
    public static void checkSwitchPage(){
        try {
            Asst.assertEquals("进入My Page页面失败",InternetPage.HEADPAGE_MY_PAGES,
                    HeadTiltle.getText());

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 删除一个书签
    * */
    public static void deleteBookmarks(String BooksName){
        try {
            IWantToButton.clickAndWaitForNewWindow();
            ViewMyBookmarks.clickAndWaitForNewWindow();
            Asst.assertTrue("书签不存在",getObjectByText(BooksName).exists());
            IWantToButton.clickAndWaitForNewWindow();
            DeleteBookmarks.clickAndWaitForNewWindow();
            getObjectByText(BooksName).click();
            ConfirmButton.clickAndWaitForNewWindow();
            getObjectByText(InternetPage.OK_BUTTON).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 检查书签删除是否成功
    * */
    public static void ckeckDleeteBookmarks(String BooksName){
        Asst.assertTrue("删除书签不成功",!getObjectByText(BooksName).exists());
    }

    /*
    * 检查添加书签是否成功
    * */
    public static void checkAddBookmarks(String BooksName){
        openInternetApp();
        clickViewMyBookmarksButton();
        Asst.assertTrue("添加的名称为个"+BooksName+"的书签的图标不存在，添加书签失败！",
                getObjectByText(BooksName).exists());
    }

    /*
    * 添加一个书签
    * */
    public static  void addOneBookmarks(String BookmarksName){
        dismissAccessReq();
        try {
            IWantToButton.clickAndWaitForNewWindow();
           // Asst.assertTrue("书签已经添加",!AddBookmarksButton.exists());
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
    * 通过传入参数搜索网页
    * */
    public static void searchAddress(String Address){
        try {
            SearchAddressBox.setText(Address);
            if(HeadTiltle.exists()){
                SearchButton.clickAndWaitForNewWindow();
            }
            SearchButton.clickAndWaitForNewWindow();
            Asst.assertTrue("点击搜索按钮后没有进入搜索页面", !HeadTiltle.exists());
            if(getObjectByText(InternetPage.BAIDU_LOCATION).exists()){
                getObjectByText("No").click();
            }
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
            Asst.assertTrue("搜索网址出错！",getObjectByText(InternetPage.SEARCH_ADDRESS_URL).
                    exists());
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
