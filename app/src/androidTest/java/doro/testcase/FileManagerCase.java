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
        fileManageraction.checkDetails("Internal shared storage/ScreenShort/Critty - 杏花弦外雨");
    }
    @Test
    public void copyFolder(){ //复制文件夹
        openAppliction(APPS_ICON_FILEMANAGER_TEXT); //找到文件管理器应用
        fileManageraction.checkCopeFolder("Internal shared storage/ScreenShort/1455413756654.gif",
                "Internal shared storage/DCIM");
    }
    @Test
    public void cutFolder(){ //移动文件夹
        openAppliction(APPS_ICON_FILEMANAGER_TEXT); //找到文件管理器应用
        fileManageraction.checkCutFolder("Internal shared storage/ScreenShort/1455413796082.gif",
                "Internal shared storage/DCIM");
    }
    @Test
    public void deleteFolder(){ //删除文件夹，文件
        openAppliction(APPS_ICON_FILEMANAGER_TEXT); //找到文件管理器应用
        fileManageraction.checkDeleteFolder("Internal shared storage/ScreenShort/1455413852653.gif");
    }

}
