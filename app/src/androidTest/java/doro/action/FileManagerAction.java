
package doro.action;

import android.support.test.uiautomator.UiObjectNotFoundException;

import junit.framework.Assert;

import ckt.base.VP4;

import static doro.page.FileManagerPage.APPS_ICON_FILEMANAGER_TEXT;
import static doro.page.FileManagerPage.FILEMANAGER_COPY_DESC;
import static doro.page.FileManagerPage.FILEMANAGER_CUT_DESC;
import static doro.page.FileManagerPage.FILEMANAGER_DELETE_DESC;
import static doro.page.FileManagerPage.FILEMANAGER_DETAILS_TEXT;
import static doro.page.FileManagerPage.FILEMANAGER_EDIT_ADAPTER_NAME_ID;
import static doro.page.FileManagerPage.FILEMANAGER_MORE_OPTIONS_DESC;
import static doro.page.FileManagerPage.FILEMANAGER_OK_TEXT;
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
    public void FileManagerAccess(){
        openAppliction(APPS_ICON_FILEMANAGER_TEXT); //找到文件管理器应用
        try {
            if(getUiObjectByText("ALLOW").exists()){
                getUiObjectByText("ALLOW").clickAndWaitForNewWindow();
            }
        } catch (UiObjectNotFoundException e) {e.printStackTrace();}

    }
    public void chooseFile(String Filepath){ //根据文件路径选中一个文件
        try{
            String[] pathName =Filepath.split("/");
            for(int i=0;i<pathName.length-1;i++){
                while(!getObjectByTextContains(pathName[i]).exists()){
                    scrollByVerticalForward(35);
                }
                getObjectByTextContains(pathName[i]).clickAndWaitForNewWindow();

            }
            getUiObjectByDes(FILEMANAGER_MORE_OPTIONS_DESC).clickAndWaitForNewWindow();
            getObjectByTextContains(FILEMANAGER_SELECT_FILE_TEXT).clickAndWaitForNewWindow();
            if(getObjectByText(pathName[pathName.length-1]).exists()){
                getObjectByText(pathName[pathName.length-1]).click();
            }else{
                Assert.assertTrue("The resource folder don't exist!",false);
            }
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
            if(sd){
                getObjectByIdText(FILEMANAGER_EDIT_ADAPTER_NAME_ID,FILEMANAGER_SD_CARD_TEXT).clickAndWaitForNewWindow();
                pressKey("back");
            }
            Assert.assertTrue("SanDisk SD card  don't exist",sd);
        }catch(Exception e){e.printStackTrace();}
    }
    public void findDetails(String Filepath){ //找到一个文件夹的详细信息
        try{
            chooseFile(Filepath);
            getUiObjectByDes(FILEMANAGER_MORE_OPTIONS_DESC).clickAndWaitForNewWindow();
            getObjectByText(FILEMANAGER_DETAILS_TEXT).clickAndWaitForNewWindow();
        }catch(Exception e){e.printStackTrace();}
    }
    public void checkDetails(){  //检查一个文件夹的详细信息
        try {
            Assert.assertTrue("Details of folder is exist",getObjectByText("OK").exists());
            Assert.assertTrue("Don't have the details's Name ",getObjectByTextContains("Name").exists());
            Assert.assertTrue("Don't have the details's Size",getObjectByTextContains("Size").exists());
            Assert.assertTrue("Don't have the details's Modified",getObjectByTextContains("Modified").exists());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void copyFolder(String formFilepath,String toFilepath){
        //从一个路径下复制文件到另外一个路径下
        try{
            chooseFile(formFilepath);
            getUiObjectByDes(FILEMANAGER_COPY_DESC).click();
            findFile(toFilepath);
            getUiObjectByDes(FILEMANAGER_PASTE_DESC).click();
            phoneWaitTime(0.1);
        }catch(Exception e){e.printStackTrace();}
    }
    public void checkCopyFolder(String fileNmae){//检查复制是否成功
        try{
            Assert.assertTrue("Don't copy the folder successfully",getObjectByTextContains(fileNmae).exists());
        }catch(Exception e){e.printStackTrace();}
    }
    public void cutFolder(String formFilepath,String toFilepath){
        //从一个路径下移动文件到另外一个路径下
        try{
            chooseFile(formFilepath);
            getUiObjectByDes(FILEMANAGER_CUT_DESC).click();
            findFile(toFilepath);
            getUiObjectByDes(FILEMANAGER_PASTE_DESC).click();
            phoneWaitTime(0.1);
        }catch(Exception e){e.printStackTrace();}
    }
    public void checkCutFolder(String fileName){
        //检查移动是否成功
        try{
             Assert.assertTrue("Don't cut folder successfully",getObjectByTextContains(fileName).exists());
        }catch(Exception e){e.printStackTrace();}
    }
    public void deleteFolder(String filepath){ //删除文件
        try{
            chooseFile(filepath);
            getUiObjectByDes(FILEMANAGER_DELETE_DESC).clickAndWaitForNewWindow();
            getObjectByText(FILEMANAGER_OK_TEXT).clickAndWaitForNewWindow();
            phoneWaitTime(0.1);
        }catch(Exception e){e.printStackTrace();}
    }
    public void checkDeleteFolder(String fileName){
        //检查 删除文件 是否成功
        try{
            Assert.assertFalse("Don't delete folder successfully",getObjectByTextContains(fileName).exists());
        }catch(Exception e){e.printStackTrace();}
    }
}
