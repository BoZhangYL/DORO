package doro.testcase;

import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.action.InternetAction;
import doro.page.InternetPage;


/**
 * Created by bo.zhang on 2017/01/17   .
 */
@RunWith(AndroidJUnit4.class)
public class InternetCase extends VP4{
    @BeforeClass
    public static void initInternet(){
        VP4.unLock();
        InternetAction.dismissStartup();
    }
    @Test
    public void LaunchIntenetFromApplictionList(){
        InternetAction.openInternetApp();
    }
    @Test
    public void exitInternet(){
        InternetAction.exitInternetApp();
    }
    @Test
    public void checkOverviewPage(){
        InternetAction.openInternetApp();
        InternetAction.checkOverView();
    }
    @Test
    public void changeToInternetLandscapeMode() {
        InternetAction.openInternetApp();
        InternetAction.changeToLandscapeMode();
        InternetAction.changeToPortraitMode();
    }

    @Test
    public void searchWordAndAddress(){
        InternetAction.openInternetApp();
        InternetAction.searchWord();
        InternetAction.openInternetApp();
        InternetAction.searchAddress();
    }

    @Test
    public void clearDataAndHistory(){
        InternetAction.openInternetApp();
        InternetAction.clickClearDataAndHistoryButton();
    }
    @Test
    public void viewMyBookmarks(){
        InternetAction.openInternetApp();
        InternetAction.clickViewMyBookmarksButton();
    }
    @Test
    public void checkBookmarks(){
        InternetAction.openInternetApp();
        InternetAction.clickViewMyBookmarksButton();
        InternetAction.ckeckAllMyBookmarksPage();
    }
    @Test
    public void addBookmarks(){
        InternetAction.openInternetApp();
        InternetAction.searchAddress();
        InternetAction.addOneBookmarks(InternetPage.BOOKMARKS_NAME_BAIDU);
        InternetAction.checkAddBookmarks(InternetPage.BOOKMARKS_NAME_BAIDU);
    }
    @Test
    public void deleteBookmakes(){

    }
}
