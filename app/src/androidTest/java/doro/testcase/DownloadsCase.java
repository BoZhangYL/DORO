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

import doro.action.DownloadsAction;

import static doro.page.DownloadsPage.*;


/**
 * Created by admin on 2017/1/16.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DownloadsCase extends DownloadsAction {
    @BeforeClass
    public static void initDownloads(){
        unLock();
    }

    @Test
    public void testEnterDownloads()throws UiObjectNotFoundException{
        openAppliction(DOWNLOADS);
        Assert.assertEquals("没有进入Downloads",DOWNLOADS,getObjectByClass(DOWNLOADS_CLASS).getText());
        Assert.assertTrue("请下载一些应用程序！",!getObjectByText("Doro 8042").exists());//检查是否已下载有程序
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
        checkSoryBy(DOWNLOADS_SORTBY_NAME);//检查下载文件按姓名排序
    }

    @Test
    public void testSortByDateModified(){
        openAppliction(DOWNLOADS);
        sortBy(DOWNLOADS_SORTBY_DATEMODIFIED);//点击修改时间排序
        checkSoryBy(DOWNLOADS_SORTBY_DATEMODIFIED);//检查下载文件修改时间排序
    }

   /* @Test
    public void testSortBySize(){

        System.out.println(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        File downfiles = new File(Environment.getExternalStorageDirectory()+"/Download/");
        System.out.println(Environment.getExternalStorageDirectory()+"/Download/");
        String [] file=downfiles.list();
        for(int i=0 ;i<file.length;i++) {
            System.out.println(file[i]);
        }


    }

    @Test
    public void testGridView(){

    }

    @Test
    public void testListView(){

    }*/







































}
