package doro.testcase;

import android.os.Environment;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import doro.action.DownloadsAction;

import static doro.page.DownloadsPage.*;


/**
 * Created by admin on 2017/1/16.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DownloadsCase extends DownloadsAction {
    @Test
    public void Case1_testEnterDownloads()throws UiObjectNotFoundException{
        unLock();
        connectWifi("CKT","ck88888!");//连接wifi
        downloadFiles();//下载文件
        openAppliction(DOWNLOADS);
        Assert.assertEquals("没有进入Downloads",DOWNLOADS,getObjectByClass(DOWNLOADS_CLASS).getText());
    }

    @Test
    public void Case2_testQuitDownloads(){
        openAppliction(DOWNLOADS);
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressBack();//按返回键退出下载程序
        Assert.assertTrue("没有退出Downloads",getObjectByText(MYAPPLICATIONS).exists());
        openAppliction(DOWNLOADS);
        mDevice.pressHome();//按Home键退出下载程序
        Assert.assertTrue("没有退出Downloads",getObjectById(MENU).exists());
    }

    @Test
    public void Case3_testSortByName(){
        openAppliction(DOWNLOADS);
        sortBy(DOWNLOADS_SORTBY_NAME);//点击姓名排序
        checkSortBy(DOWNLOADS_SORTBY_NAME);//检查下载文件按姓名排序
    }

    @Test
    public void Case4_testSortByDateModified(){
        openAppliction(DOWNLOADS);
        sortBy(DOWNLOADS_SORTBY_DATEMODIFIED);//点击修改时间排序
        checkSortBy(DOWNLOADS_SORTBY_DATEMODIFIED);//检查下载文件修改时间排序
    }

    @Test
    public void Case5_testSortBySize(){
        openAppliction(DOWNLOADS);
        showFileSize();//打开显示下载文件大小
        sortBy(DOWNLOADS_SORTBY_SIZE);//点击按大小排序
        checkSortBy(DOWNLOADS_SORTBY_SIZE);//检查下载文件大小排序
    }

   @Test
    public void Case6_testGridView(){
       openAppliction(DOWNLOADS);
       viewBy(DOWNLOADS_GRIDVIEW);//点击网格查看
       checkViewBy(DOWNLOADS_GRIDVIEW);//检查网格显示
   }

    @Test
    public void Case7_testListView(){
        openAppliction(DOWNLOADS);
        viewBy(DOWNLOADS_LISTVIEW);//点击列表查看
        checkViewBy(DOWNLOADS_LISTVIEW);//检查列表显示
    }

}
