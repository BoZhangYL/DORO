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
    public void searchWord(){//
        InternetAction.openInternetApp();
        InternetAction.searchWord();
    }
    @Test
    public void searchAddress(){//
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
        InternetAction.addOneBookmarks(InternetPage.BOOKMARKS_NEW_1);
        InternetAction.checkAddBookmarks(InternetPage.BOOKMARKS_NEW_1);
    }
    @Test
    public void deleteBookmakes(){//
        InternetAction.openInternetApp();
        InternetAction.searchAddress(InternetPage.HAO123);
        InternetAction.addOneBookmarks(InternetPage.BOOKMARKS_NEW_2);
        InternetAction.checkAddBookmarks(InternetPage.BOOKMARKS_NEW_2);
        InternetAction.openInternetApp();
        InternetAction.deleteBookmarks(InternetPage.BOOKMARKS_NEW_2);
        InternetAction.ckeckDleeteBookmarks(InternetPage.BOOKMARKS_NEW_2);
    }
    @Test
    public void openSwitchPage(){
        InternetAction.openInternetApp();
        InternetAction.goToSwitchPage();
        InternetAction.checkSwitchPage();
    }
    @Test
    public void checkMaxPage(){
        InternetAction.checkMaxPages();
    }
    @Test
    public void checkEmptyPage(){//
        InternetAction.openInternetApp();
        InternetAction.clickClearDataAndHistoryButton();
       // InternetAction.goToSwitchPage();
        InternetAction.ckeckEmptyPages();
    }

    @Test
    public void deletePage(){
        InternetAction.AddPage();
        InternetAction.goToSwitchPage();
        InternetAction.deleteOnePage();
        InternetAction.checkDeleteOnePage();
        InternetAction.DelteAllPage();
    }
    @Test
    public void checkNavigationPage(){//
        InternetAction.openInternetApp();
        InternetAction.searchAddress();
        InternetAction.checkNavigationPages();
    }
    @Test
    public void shareNavigationPage(){//
        InternetAction.openInternetApp();
        InternetAction.searchAddress();
        InternetAction.checkNavigationPages();
        InternetAction.sendThisPage();
    }
    @Test
    public void checkInternetSet(){
        InternetAction.openInternetApp();
        InternetAction.checkSetOption();
    }

    @Test
    public void setMaxOpendPage(){
        InternetAction.openInternetApp();
        InternetAction.checkSetOption();
        InternetAction.setMaxOpendPages(InternetPage.MAXPAGE_NUMBER_2);
        InternetAction.checkMaxPages(InternetPage.MAXPAGE_NUMBER_2);
    }
    @Test
    public void checkDefaultPage(){
        InternetAction.openInternetApp();
        InternetAction.checkSetOption();
        InternetAction.checkDefaultPages();
    }

}
