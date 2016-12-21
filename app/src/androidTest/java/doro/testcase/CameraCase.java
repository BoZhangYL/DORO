package doro.testcase;

import android.app.Activity;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.KeyEvent;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

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
//        initDevice();
//        unLock();
//        setCameraRestoredefaults();
    }

    @Test
    public void launchCameraFromAppList(){
        // D8040-78093:通过主菜单启动

        openAppliction("Camera");//通过菜单启动相机
        checkLaunchCameraResult();//检查启动相机是否成功
    }

    @Test
    public void launchCameraFormShortcut(){
       // D8040-78094:通过桌面快捷方式启动

        launchCameraByShortcut();//通过桌面快捷方式启动相机
        checkLaunchCameraResult();//检查启动相机是否成功

    }

    @Test
    public void launchCameraFromCameraKey(){
        //D8040-78095:通过相机键启动

        longPressKey(KeyEvent.KEYCODE_CAMERA);//通过长按相机键启动相机
        checkLaunchCameraResult();//检查相机是否启动成功
    }



    @Test
    public void captureByFrontCamera(){
        //D8040-9820:前摄默认设置下拍照

        openAppliction("Camera");//打开相机
        switchToFrontCamera();//切换到前摄像头
        manualFocus();//对焦
        takePictures();//拍照
        checkTakePicturesResult();//检查拍照结果
    }
    @Test
    public void captureByBackCamera(){
        //D8040-9869:后摄默认设置下拍照
        openAppliction("Camera");//打开相机
        switchToBackCamera();//切换到后摄像头
        manualFocus();//对焦
        takePictures();//拍照
        checkTakePicturesResult();//检查拍照结果
    }

    @Test
    public void switchToGalleryFromCamer(){
        //  D8040-9250:相机预览界面，进入图库
        openAppliction("Camera");//打开相机
        switchtoGallery();//切换到图片
        checkSwitchToGalleryResult();//判断结果
    }
    @Test
    public void deletePicures(){
        //D8040-7592:删除照片
        openAppliction("Camera");//打开相机
        takePictures();//拍照
        deletePicture();//删除照片
        checkdeleteResult();//检查删除是否成功
    }
    @Test
    public void recordByFrontCamera(){
        //D8040-7598:前摄默认设置下录像
        openAppliction("Camera");//打开相机
        switchToFrontCamera();//切换到前摄像头
        recordVideo(DEFAULT_RECORD_TIMES);//录像
        checkRecordVideoResult();//检查录像结果
    }
    @Test
    public void recordByBackCamera(){
        // D8040-7597:后摄默认设置下录像
        openAppliction("Camera");//打开相机
        switchToBackCamera();//切换到后摄像头
        recordVideo(DEFAULT_RECORD_TIMES);//录像
        checkRecordVideoResult();
    }
    @Test
    public void stopRecordVideo(){
        // D8040-7599:录像时，停止
        openAppliction("Camera");//打开相机
        switchToBackCamera();//切换到后摄像头

    }
    @Test
    public void suspendRecordVideo(){
        // D8040-7600:录像时，暂停
        openAppliction("Camera");//打开相机
        switchToBackCamera();//切换到后摄像头

    }
    @Test
    public void deleteVideos(){
        // D8040-7639:删除视频
        openAppliction("Camera");//打开相机
        switchToBackCamera();//切换到后摄像头
        recordVideo(DEFAULT_RECORD_TIMES);//录像
        deleteVideo();
        checkdeleteResult();//检查删除是否成功
    }
    @Test
    public void restoreDefaults(){
        // D8040-7693:恢复默认设置
        openAppliction("Camera");//打开相机
        changeCameraSettings();
        setCameraRestoredefaults();
        checkRestoredefaults();
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
     */



}
