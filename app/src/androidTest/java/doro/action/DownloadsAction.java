package doro.action;


import android.graphics.Rect;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import org.junit.Assert;
import ckt.base.VP4;
import static doro.page.DownloadsPage.*;
import static doro.page.WifiPage.*;

/**
 * Created by admin on 2017/1/16.
 */

public class DownloadsAction extends VP4 {
    public static void connectWifi(String name,String password){
        //连接wifi
        WifiAction WifiAction=new WifiAction();
        openAppliction(SETTINGS);
        clickByText(WIFI);
        WifiAction.turnOnWifi(true);
        WifiAction.connectWifi(name,password);
    }

    public static void downloadFiles(){
        //从wiki上下载文件
        InternetAction.watchexception();
        try {
            openAppliction(INTERNET);
            UiObject address=getObjectById(INTERNET_SEARCH);
            address.clickAndWaitForNewWindow();
            address.setText(INTERNET_ADDRESS);
            getObjectById(INTERNET_SEARCHBUTTON).clickAndWaitForNewWindow();
            for (int i=0;i<=2;i++) {
                Rect Z = getObjectByDesc(INTERNET_FILE).getBounds();
                int centerX = Z.centerX();
                int centerY = Z.centerY();
                gDevice.swipe(centerX, centerY, centerX + 2, centerY + 2, 300);
                clickByText(INTERNET_SAVE);
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void sortBy(String Method) {//选择SortBy
        try {
            clickById(DOWNLOADS_SORTMENU_ID);
            getObjectByText(Method).clickAndWaitForNewWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkSortBy(String Method) {//检查SortBy
        try {
            UiCollection Collection = new UiCollection(new UiSelector().resourceId(DOWNLOADS_APPS_ID).className(DOWNLOADS_APPS_CLASS));
            int Appcounts = Collection.getChildCount();
            Assert.assertTrue("请下载至少两个文件！",Appcounts>1);
            switch (Method) {
                case DOWNLOADS_SORTBY_NAME://检查按名字排序
                    UiSelector childPattern = new UiSelector().resourceId(DOWNLOADS_APPS_NAME);
                    String APPNAME1 = Collection.getChildByInstance(childPattern, 0).getText();
                    String APPNAME2 = Collection.getChildByInstance(childPattern, 0).getText();
                    Assert.assertTrue("下载文件没有按名字排序！", APPNAME1.compareToIgnoreCase(APPNAME2)<= 0);
                    break;
                case DOWNLOADS_SORTBY_DATEMODIFIED://检查按修改时间排序
                    UiSelector childPattern1 = new UiSelector().resourceId(DOWNLOADS_APPS_DATEMODIFIED);
                    String APPDATE1 = Collection.getChildByInstance(childPattern1, 0).getText();
                    String APPDATE2 = Collection.getChildByInstance(childPattern1, 1).getText();
                    Assert.assertTrue("下载文件没有按时间排序！", APPDATE1.compareToIgnoreCase(APPDATE2)>= 0);
                    break;
                case DOWNLOADS_SORTBY_SIZE://检查按大小排序
                    UiSelector childPattern2 = new UiSelector().resourceId(DOWNLOADS_APPS_SIZE);
                    String APPSIZE1 = Collection.getChildByInstance(childPattern2, 0).getText();
                    String APPSIZE2 = Collection.getChildByInstance(childPattern2, 1).getText();
                    Assert.assertTrue("下载文件没有按大小排序！",sizeUnitConversion(APPSIZE1)>=sizeUnitConversion(APPSIZE2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float sizeUnitConversion(String APPSIZE) {//把文件大小GB，MB，KB统一为KB
        String[] NumberUnit = APPSIZE.split(" ");
        float Size=0;
        switch (NumberUnit[1]) {
            case "GB":
                Size = Float.valueOf(NumberUnit[0])* 1024 * 1024;
                System.out.println(Size);
                break;
            case "MB":
                Size = Float.valueOf(NumberUnit[0])*1024;
                System.out.println(Size);
                break;
            case "KB":
                Size = Float.valueOf(NumberUnit[0]);
                System.out.println(Size);
                break;
        }
        return Size;
    }

    public void showFileSize(){//打开显示大小
        try {
            clickByDescription(DOWNLOADS_MOREOPTIONS_DESC);
            UiObject ShowFileSize = getObjectByIdText(DOWNLOADS_SHOWFILESIZE_ID,DOWNLOADS_SHOWFILESIZE);
            if (ShowFileSize.exists()) {
                ShowFileSize.clickAndWaitForNewWindow();
            }else {
                mDevice.pressBack();
            }
        }catch (Exception e){
           e.printStackTrace();
        }
    }

    public void viewBy(String Method){//选择显示
       try{
        switch (Method){
            case DOWNLOADS_GRIDVIEW://选择按网格显示
                UiObject GridButton=getObjectById(DOWNLOADS_GRIDVIEW_ID);
               if (GridButton.exists()) {
                   GridButton.clickAndWaitForNewWindow();
               }
                break;
            case DOWNLOADS_LISTVIEW://选择按列表显示
                UiObject ListButton=getObjectById(DOWNLOADS_LISTVIEW_ID);
                if (ListButton.exists()){
                    ListButton.clickAndWaitForNewWindow();
                }
                break;
        }
       }catch (Exception e){
       e.printStackTrace();
       }
    }

    public void checkViewBy(String Method){//检查显示
        switch (Method){
            case DOWNLOADS_GRIDVIEW://检查网格显示
                UiObject Object=getLinearLayout(0,DOWNLOADS_APPS_CLASS,DOWNLOADS_GRIDVIEW_CLASS);
                Assert.assertTrue("下载文件没有网格显示",Object.exists());
                break;
            case DOWNLOADS_LISTVIEW://检查列表显示
                UiObject Object1=getLinearLayout(0,DOWNLOADS_APPS_CLASS,DOWNLOADS_LISTVIEW_CLASS);
                System.out.println(Object1);
                Assert.assertTrue("下载文件没有列表显示",Object1.exists());
                break;
        }
    }







































}
