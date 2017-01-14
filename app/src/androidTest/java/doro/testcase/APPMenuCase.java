package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.squareup.spoon.Spoon;

import org.hamcrest.Asst;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import ckt.base.VP4;
import doro.action.APPMenuAction;
import doro.action.MainAction;
import doro.action.APPMenuAction;
import doro.page.APPMenuPage;
import doro.page.APPMenuPage;

/**
 * Created by Caibing.yin  on 2017/1/12.
 */
@RunWith(AndroidJUnit4.class)
public class APPMenuCase extends VP4{
    @BeforeClass
    public static void initGalleryCase(){
        unLock();
    }

    @Test
    public void testClickAnyAPPInMenu() throws UiObjectNotFoundException {
        for(int i=0;i<42;i++){
            // 依次打开菜单中的每一个应用程序
            MainAction.clearAllApp();
            MainAction.startApp(APPMenuPage.AppNameList[i]);
            boolean Actal = pkgName_exists(APPMenuAction.getPkgName(i));
            //通过程序包名是否存在来判断应用程序打开是否成功
            Asst.assertFalse("Can't_Launch_"+APPMenuPage.AppNameList[i],!Actal);
        }
    }
    @Test
    public void testReEnterApp(){

    }
}
