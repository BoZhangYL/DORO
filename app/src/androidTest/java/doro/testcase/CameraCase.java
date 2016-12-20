package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import ckt.base.VP4;
import static doro.action.CameraAction.*;
import static doro.page.CameraPage.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by bo.zhang on 2016/12/15   .
 */
@RunWith(AndroidJUnit4.class)
public class CameraCase extends VP4{
    @BeforeClass
    public static void initCalendar(){
        initDevice();
        unLock();
    }
    @Test
    public void launchCameraFromAppList(){
        // D8040-78093:通过主菜单启动

        openAppliction("Camera");
        assertEquals("launchCameraFromAppList",
                CAMERA_PACKAGE_NAME,gDevice.getCurrentPackageName());
    }
    @Test
    public void launchCameraFormShortcut(){
       // D8040-78094:通过桌面快捷方式启动

        launchCameraByShortcut();
        assertEquals("launchCameraFormShortcut",
                CAMERA_PACKAGE_NAME,gDevice.getCurrentPackageName());

    }
    @Test
    public void launchCameraFromCameraKey(){
        //D8040-78095:通过相机键启动

        initDevice();
        gDevice.pressKeyCode(KeyEvent.KEYCODE_CAMERA, 500);
    }

    @Test
    public void captureByFrontCamera(){
        //D8040-9820:前摄默认设置下拍照

        openAppliction("Camera");//打开相机
        switchToFrontCamera();
        manualFocus();
        takePictures();
        checkTakePicturesResult();
    }
    @Test
    public void captureByBackCamera(){
        //D8040-9869:后摄默认设置下拍照
        openAppliction("Camera");//打开相机
        switchToBackCamera();
        manualFocus();
        takePictures();
        checkTakePicturesResult();
    }
    //  D8040-9822:后摄手动对焦
    // D8040-9215:前摄自动对焦

    @Test
    public void switchToGalleryFromCamer(){
        //  D8040-9250:相机预览界面，进入图库
        openAppliction("Camera");//打开相机
        switchtoGallery();
        checkSwitchToGalleryResult();
    }
    @Test
    public void deletePicures(){
        //D8040-7592:删除照片
        openAppliction("Camera");//打开相机
        takePictures();
        deletePicture();
        checkdeletePicuresResult();
    }
    @Test
    public void recordByFrontCamera(){
        //D8040-7598:前摄默认设置下录像
        openAppliction("Camera");//打开相机
//        recordVideo();
//        checkRecordByFrontCameraResult();
    }
    @Test
    public void recordByBackCamera(){
        // D8040-7597:后摄默认设置下录像
    }

    // D8040-7438:全景模式拍照向左移动
    // D8040-7439:全景模式拍照向右移动
    // D8040-7567:后摄美颜拍照后查看照片
    // D8040-7568:前摄美颜拍照后查看照片

    // D8040-7594:后摄从相机切换到录像

    // D8040-7599:录像时，停止
    // D8040-7600:录像时，暂停
    // D8040-7639:删除视频
    // D8040-7693:恢复默认设置
}
