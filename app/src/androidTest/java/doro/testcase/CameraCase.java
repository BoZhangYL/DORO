package doro.testcase;

import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.test.runner.AndroidJUnit4;

import android.view.KeyEvent;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;



import ckt.base.VP4;

import static doro.page.CalednarPage.CALENDAR_PACKAGE;
import static doro.page.CameraPage.*;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import doro.action.CameraAction;
/**
 * Created by bo.zhang on 2016/12/15   .
 */
@RunWith(AndroidJUnit4.class)
public class CameraCase extends VP4{
    @BeforeClass
    public static void initCalendar(){
        initDevice();
       // unLock();
    }
    @Test
    public void launchCameraByAppListCase1(){
        // D8040-78093:通过主菜单启动
        openAppliction("Camera");
        assertEquals("launchCameraByAppListCase1",
                CAMERA_PACKAGE_NAME,gDevice.getCurrentPackageName());
    }
    @Test
    public void launchCameraByshortcutCase2(){
        //D8040-78094:通过桌面快捷方式启动
        new CameraAction().launchCameraByShortcut();
        assertEquals("launchCameraByAppListCase1",
                CAMERA_PACKAGE_NAME,gDevice.getCurrentPackageName());
    }
    @Test
    public void launchCameraByKeyCase3(){
        //D8040-78095:通过相机键启动
        gDevice.pressKeyCode(KeyEvent.KEYCODE_CAMERA,500);

    }

}
