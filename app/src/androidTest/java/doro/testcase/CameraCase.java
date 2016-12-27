package doro.testcase;


import android.support.test.runner.AndroidJUnit4;

import android.view.KeyEvent;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import ckt.base.VP4;
import doro.action.CameraAction;
import static doro.page.CameraPage.*;

/**
 * Created by bo.zhang on 2016/12/15   .
 */
@RunWith(AndroidJUnit4.class)
public class CameraCase extends VP4{
    @BeforeClass
    public static void initCalendar(){
        VP4.initDevice();
        VP4.unLock();
        CameraAction.setCameraRestoredefaults();
    }

    @Test
    public void launchCameraFromAppList(){
       //  D8040-78093:通过主菜单启动

        VP4.openAppliction(CAMERA);//通过菜单启动相机
        CameraAction.checkLaunchCameraResult();//检查启动相机是否成功
    }

    @Test
    public void launchCameraFormShortcut(){
       // D8040-78094:通过桌面快捷方式启动

        CameraAction.launchCameraByShortcut();//通过桌面快捷方式启动相机
        CameraAction.checkLaunchCameraResult();//检查启动相机是否成功
    }

    @Test
    public void launchCameraFromCameraKey(){
        //D8040-78095:通过相机键启动

        CameraAction.longPressKey(KeyEvent.KEYCODE_CAMERA);//通过长按相机键启动相机
        CameraAction.checkLaunchCameraResult();//检查相机是否启动成功
    }



    @Test
    public void captureByFrontCamera(){
        //D8040-9820:前摄默认设置下拍照

        VP4.openAppliction(CAMERA);//打开相机
        CameraAction.switchToFrontCamera();//切换到前摄像头
        CameraAction.manualFocus();//对焦
        CameraAction.takePictures();//拍照
        CameraAction.checkTakePicturesResult();//检查拍照结果
    }
    @Test
    public void captureByBackCamera(){
        //D8040-9869:后摄默认设置下拍照
        VP4.openAppliction(CAMERA);//打开相机
        CameraAction.switchToBackCamera();//切换到后摄像头
        CameraAction.manualFocus();//对焦
        CameraAction.takePictures();//拍照
        CameraAction.checkTakePicturesResult();//检查拍照结果
    }

    @Test
    public void switchToGalleryFromCamera(){
        //  D8040-9250:相机预览界面，进入图库
        VP4.openAppliction(CAMERA);//打开相机
        CameraAction.switchtoGallery();//切换到图片
        CameraAction.checkSwitchToGalleryResult();//判断结果
    }
    @Test
    public void deletePicures(){
        //D8040-7592:删除照片
        VP4.openAppliction(CAMERA);//打开相机
        CameraAction.takePictures();//拍照
        CameraAction.deletePicture();//删除照片
        CameraAction.checkdeleteResult();//检查删除是否成功
    }
    @Test
    public void recordByFrontCamera(){
        //D8040-7598:前摄默认设置下录像
        VP4.openAppliction(CAMERA);//打开相机
        CameraAction.switchToFrontCamera();//切换到前摄像头
        CameraAction.recordVideo(DEFAULT_RECORD_TIMES);//录像
        CameraAction.checkRecordVideoResult();//检查录像结果
    }
    @Test
    public void recordByBackCamera(){
        // D8040-7597:后摄默认设置下录像
        VP4.openAppliction(CAMERA);//打开相机
        CameraAction.switchToBackCamera();//切换到后摄像头
        CameraAction.recordVideo(DEFAULT_RECORD_TIMES);//录像
        CameraAction.checkRecordVideoResult();
    }
    @Test
    public void stopRecordVideo(){
        // D8040-7599:录像时，停止
        VP4.openAppliction(CAMERA);//打开相机
        CameraAction.switchToBackCamera();//切换到后摄像头
        CameraAction.checkStopRecoed();
        CameraAction. recordVideo(DEFAULT_RECORD_TIMES);//录像
    }
    @Test
    public void deleteVideos(){
        // D8040-7639:删除视频
        VP4.openAppliction(CAMERA);//打开相机
        CameraAction.switchToBackCamera();//切换到后摄像头
        CameraAction.recordVideo(DEFAULT_RECORD_TIMES);//录像
        CameraAction.deleteVideo();
        CameraAction.checkdeleteResult();//检查删除是否成功
    }
    @Test
    public void restoreDefaults(){
        // D8040-7693:恢复默认设置
        VP4.openAppliction(CAMERA);//打开相机
        CameraAction.changeCameraSettings();
        CameraAction.setCameraRestoredefaults();
        CameraAction.checkRestoredefaults();
    }
    /**
     * 如下case doro不适用
     D8040-9822:后摄手动对焦
     D8040-9215:前摄自动对焦
     D8040-7438:全景模式拍照向左移动
     D8040-7439:全景模式拍照向右移动
     D8040-7567:后摄美颜拍照后查看照片
     D8040-7568:前摄美颜拍照后查看照片
     D8040-7594:后摄从相机切换到录像
     D8040-7600:录像时，暂停
     */



}
