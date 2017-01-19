package doro.testcase;

import android.os.Environment;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.Assert;
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
public class DownloadsCase extends DownloadsAction {
    @BeforeClass
    public static void initDownloads(){
        unLock();
    }

    @Test
    public void testEnterDownloads()throws UiObjectNotFoundException{
        openAppliction(DOWNLOADS);
        Assert.assertEquals("没有进入Downloads",DOWNLOADS,getObjectByClass(DOWNLOADS_CLASS).getText());
        Assert.assertTrue("请下载一些文件！",!getObjectByText("Doro 8042").exists());//检查是否已下载有程序
    }

    @Test
    public void testQuitDownloads(){
        openAppliction(DOWNLOADS);
        mDevice.pressBack();
        mDevice.pressBack();//按返回键退出下载程序
        Assert.assertTrue("没有退出Downloads",getObjectByText(MYAPPLICATIONS).exists());
        openAppliction(DOWNLOADS);
        mDevice.pressHome();//按Home键退出下载程序
        Assert.assertTrue("没有退出Downloads",getObjectByText(MYAPPLICATIONS).exists());
    }

    @Test
    public void testSortByName(){
        openAppliction(DOWNLOADS);
        sortBy(DOWNLOADS_SORTBY_NAME);//点击姓名排序
        checkSortBy(DOWNLOADS_SORTBY_NAME);//检查下载文件按姓名排序
    }

    @Test
    public void testSortByDateModified(){
        openAppliction(DOWNLOADS);
        sortBy(DOWNLOADS_SORTBY_DATEMODIFIED);//点击修改时间排序
        checkSortBy(DOWNLOADS_SORTBY_DATEMODIFIED);//检查下载文件修改时间排序
    }

    @Test
    public void testSortBySize(){
        openAppliction(DOWNLOADS);
        showFileSize();//打开显示下载文件大小
        sortBy(DOWNLOADS_SORTBY_SIZE);//点击按大小排序
        checkSortBy(DOWNLOADS_SORTBY_SIZE);//检查下载文件大小排序
    }

   @Test
    public void testGridView(){
       openAppliction(DOWNLOADS);
       viewBy(DOWNLOADS_GRIDVIEW);//点击网格查看
       checkViewBy(DOWNLOADS_GRIDVIEW);//检查网格显示
   }

    @Test
    public void testListView(){
        openAppliction(DOWNLOADS);
        viewBy(DOWNLOADS_LISTVIEW);//点击列表查看
        checkViewBy(DOWNLOADS_LISTVIEW);//检查列表显示
    }

}
