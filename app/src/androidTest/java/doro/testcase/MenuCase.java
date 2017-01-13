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
import doro.action.MenuAction;
import doro.page.MenuPage;

/**
 * Created by Caibing.yin  on 2017/1/12.
 */
@RunWith(AndroidJUnit4.class)
public class MenuCase extends VP4{
    @BeforeClass
    public static void initGalleryCase(){
        unLock();
    }

    @Test
    public void testClickAnyAPPInMenu() throws UiObjectNotFoundException {
        for(int i=0;i<20;i++){
            switchToMenuPage();//进入菜单界面
            openAppliction(MenuPage.AppNameList[i]);//依次打开菜单中的每一个应用程序
            Spoon.screenshot(MenuPage.AppNameList[i]);

            Asst.assertFalse("Can't Launch "+MenuPage.AppNameList[i],!text_exists(MenuAction.getPkgName(i)));
            waitTime(2);
            continue;
        }
    }
    @Test
    public void test2(){}
}
