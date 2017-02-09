package doro.testcase;

import org.junit.Test;

import doro.action.FileManagerAction;

import static ckt.base.VP4.openAppliction;
import static doro.page.FileManagerPage.APPS_ICON_FILEMANAGER_TEXT;

/**
 * Created by user on 2016/12/27   .
 */

public class FileManagerCase {
    FileManagerAction fileManageraction = new FileManagerAction();
    @Test
    public void checkSDCard(){ //检查是否有SD卡
        openAppliction(APPS_ICON_FILEMANAGER_TEXT); //找到文件管理器应用
        fileManageraction.CheckSDCard();
    }
    @Test
    public void detailsOfFiles(){ //查看文件的详细情况
        openAppliction(APPS_ICON_FILEMANAGER_TEXT); //找到文件管理器应用
        fileManageraction.findDetails("Internal shared storage/FileManager/Details I'm Yours.mp3");
        fileManageraction.checkDetails();
    }
    @Test
    public void copyFolder(){ //复制文件夹
        openAppliction(APPS_ICON_FILEMANAGER_TEXT); //找到文件管理器应用
        fileManageraction.copyFolder("Internal shared storage/FileManager/copyfiles.bmp",
                "Internal shared storage/AcceptFiles");
        fileManageraction.checkCopyFolder("copyfiles.bmp");
    }
    @Test
    public void cutFolder(){ //移动文件夹
        openAppliction(APPS_ICON_FILEMANAGER_TEXT); //找到文件管理器应用
        fileManageraction.cutFolder("Internal shared storage/FileManager/cut.jpg",
                "Internal shared storage/AcceptFiles");
        fileManageraction.checkCutFolder("cut.jpg");
    }
    @Test
    public void deleteFolder(){ //删除文件夹，文件
        openAppliction(APPS_ICON_FILEMANAGER_TEXT); //找到文件管理器应用
        fileManageraction.deleteFolder("Internal shared storage/FileManager/deletekkkkk.png");
        fileManageraction.checkDeleteFolder("deletekkkkk.png");
    }

}
