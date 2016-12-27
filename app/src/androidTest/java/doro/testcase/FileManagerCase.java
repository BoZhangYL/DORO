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
    public void checkSDCard(){
        openAppliction(APPS_ICON_FILEMANAGER_TEXT); //找到文件管理器应用
        fileManageraction.CheckSDCard();
    }
    @Test
    public void detailsOfFiles(){
        openAppliction(APPS_ICON_FILEMANAGER_TEXT); //找到文件管理器应用
        fileManageraction.checkDetails("Internal shared storage/ScreenShort/calendar20130901084814.jpg");
    }
    @Test
    public void copyFolder(){
        openAppliction(APPS_ICON_FILEMANAGER_TEXT); //找到文件管理器应用
        fileManageraction.CheckCopeFolder("Internal shared storage/ScreenShort/calendar20130901084814.jpg",
                "Internal shared storage/DCIM");
    }

}
