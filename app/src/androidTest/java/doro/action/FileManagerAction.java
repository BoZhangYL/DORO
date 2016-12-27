package doro.action;

import junit.framework.Assert;

import ckt.base.VP4;

import static doro.page.FileManagerPage.FILEMANAGER_COPY_DESC;
import static doro.page.FileManagerPage.FILEMANAGER_DETAILS_TEXT;
import static doro.page.FileManagerPage.FILEMANAGER_EDIT_ADAPTER_NAME_ID;
import static doro.page.FileManagerPage.FILEMANAGER_MORE_OPTIONS_DESC;
import static doro.page.FileManagerPage.FILEMANAGER_PASTE_DESC;
import static doro.page.FileManagerPage.FILEMANAGER_SD_CARD_TEXT;
import static doro.page.FileManagerPage.FILEMANAGER_SELECT_FILE_TEXT;
import static doro.page.FileManagerPage.FILEMANAGER_WIDGET_IMAGEBUTTON_TEXT;
import static doro.page.FileManagerPage.FILEMANAGER_WIDGET_LINEARLAYOUT_CLASS;
import static doro.page.FileManagerPage.FILEMANAGER_WIDGET_LISTVIEW_CLASS;

/**
 * Created by user on 2016/12/27   .
 */

public class FileManagerAction extends VP4 {
    public void chooseFile(String Filepath){ //根据文件路径选中一个文件
        getObjectByClass(FILEMANAGER_WIDGET_IMAGEBUTTON_TEXT);
        try{
            String[] pathName =Filepath.split("/");
            for(int i=0;i<pathName.length-1;i++){
                while(!getObjectByTextContains(pathName[i]).exists()){
                    scrollByVerticalForward(25);
                }
                getObjectByTextContains(pathName[i]).clickAndWaitForNewWindow();
            }
            getUiObjectByDes(FILEMANAGER_MORE_OPTIONS_DESC).clickAndWaitForNewWindow();
            getObjectByTextContains(FILEMANAGER_SELECT_FILE_TEXT).clickAndWaitForNewWindow();
            getObjectByText(pathName[pathName.length-1]).click();
        }catch(Exception e){e.printStackTrace();}
    }
    public void findFile(String Filepath){ //随机选择一个文件夹
        getObjectByClass(FILEMANAGER_WIDGET_IMAGEBUTTON_TEXT);
        try{
            String[] pathName =Filepath.split("/");
            for(int i=0;i<pathName.length;i++){
                while(!getObjectByTextContains(pathName[i]).exists()){
                    scrollByVerticalForward(25);
                }
                getObjectByTextContains(pathName[i]).clickAndWaitForNewWindow();
            }
        }catch(Exception e){e.printStackTrace();}
    }
    public void findFiles(int num){ //随机选择一个文件夹、文件
        try{
            getUiObjectByDes(FILEMANAGER_MORE_OPTIONS_DESC).clickAndWaitForNewWindow();
            getObjectByTextContains(FILEMANAGER_SELECT_FILE_TEXT).click();
            getLinearLayout(num-1,FILEMANAGER_WIDGET_LISTVIEW_CLASS,FILEMANAGER_WIDGET_LINEARLAYOUT_CLASS).click();
        }catch(Exception e){e.printStackTrace();}
    }
    /*
    * 文件管理器的一些操作
    * */
    public void CheckSDCard(){ //检查SD卡是否存在
        try{
            boolean sd =getObjectByIdText(FILEMANAGER_EDIT_ADAPTER_NAME_ID,FILEMANAGER_SD_CARD_TEXT).exists();
            getObjectByIdText(FILEMANAGER_EDIT_ADAPTER_NAME_ID,FILEMANAGER_SD_CARD_TEXT).clickAndWaitForNewWindow();
            pressKey("back");
            Assert.assertTrue("SD card不存在",sd);
        }catch(Exception e){e.printStackTrace();}
    }
    public void checkDetails(String Filepath){ //查看一个文件夹的详细信息
        try{
            chooseFile(Filepath);
            getUiObjectByDes(FILEMANAGER_MORE_OPTIONS_DESC).clickAndWaitForNewWindow();
            getObjectByText(FILEMANAGER_DETAILS_TEXT).clickAndWaitForNewWindow();
            Assert.assertTrue("详细信息存在",getObjectByText("OK").exists());
            Assert.assertTrue("详细信息的Name不存在",getObjectByTextContains("Name").exists());
            Assert.assertTrue("详细信息得Size不存在",getObjectByTextContains("Size").exists());
            Assert.assertTrue("详细信息得Modified不存在",getObjectByTextContains("Modified").exists());
        }catch(Exception e){e.printStackTrace();}
    }
    public void CheckCopeFolder(String formFilepath,String toFilepath){
        try{
            chooseFile(formFilepath);
            getUiObjectByDes(FILEMANAGER_COPY_DESC).click();
            findFile(toFilepath);
            getUiObjectByDes(FILEMANAGER_PASTE_DESC).click();
            phoneWaitTime(0.1);
            //String[] pathName =formFilepath.split("/");
           // Assert.assertTrue("没有复制成功",getObjectByTextContains(pathName[pathName.length-1]).exists());
        }catch(Exception e){e.printStackTrace();}
    }
}
