package doro.action;

import android.annotation.TargetApi;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Environment;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.view.KeyEvent;

import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import ckt.base.VP4;

import static doro.action.CalendarAction.getCurrentDay;
import static doro.action.CalendarAction.getCurrentMonth;
import static doro.action.CalendarAction.getCurrentYear;
import static doro.page.CameraPage.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by bo.zhang on 2016/12/15   .
 */

public class CameraAction extends VP4{
    private static UiObject FrontSwitchButton =getObjectById(CAMERA_FRONT_SWITCH_BUTTON);
    private static UiObject FlashButton = getObjectById(CAMERA_FLASH_BUTTON);
    private static UiObject CaptureButton = getObjectById(CAMERA_CAPTURE_BUTTON);
    private static UiObject RecordButton = getObjectById(CAMERA_RECORD_BUTTON);
    private static UiObject MiniBox = getObjectById(CAMERA_MINI_BOX);
    private static UiObject CenterFocusArea = getObjectById(CAMERA_FOCUS_AREA);
    private static UiObject SettingsButton = getObjectById(CAMERA_SETTINGS_BUTTON);
    private static UiObject IWantToButton = getObjectByText(I_WANT_TO_BUTTON);
    private static UiObject PictureDetailOption = getObjectByText(PICTURE_DETAIL);
    private static UiObject DeletePictureOption = getObjectByText(DELETE_PICTURES);
    private static UiObject PictureNameOption =getObjectById(PICTURE_NAME);
    private static UiObject OKButton = getUiObjectByText(OK_BUTTON);
    private static String CapturedPictureName;
    private static UiObject PlayVideoView =getObjectByText(GALLERY_VIDEO_PLAY_VIEW);
    private static UiObject RestreDefaultsButton = getObjectByText(RESTORE_DEFAULTS);
    private static UiObject GeneralSetting = getObjectById(CAMERA_SETTINGS_TABLE,0);
    private static UiObject CameraSetting = getObjectById(CAMERA_SETTINGS_TABLE,1);
    private static UiObject VideoSetting = getObjectById(CAMERA_SETTINGS_TABLE,2);
    private static UiObject WhiteBlance = getObjectByText(CAMERASETTINGS_WHITE_BALANCE);
    private static UiObject SelfTimer =getObjectByText(CAMERASETTINGS_SELT_TIMER);
    private static UiObject VideoQuality = getObjectByText(CAMERASETTINGS_VIDEO_QUALITY);
    private static UiObject StoreLocation = getObjectByText(CAMERASETTINGS_STORELOCATION);
    private static UiObject VideoDetailOption = getObjectByText(VIDEO_DETAIL);
    private static UiObject DeleteVideoOption = getObjectByText(DELETE_VIDEOS);
    private static int PhotosNumber = 0;
    private static int VideosNumber = 0;
    private static int getAllPhotoNumbers(){
        int photonumber=0;
        File Dcim = new File(Environment.getExternalStorageDirectory()+"/Dcim/Camera/");
        String allfiles[] = Dcim.list();
        for(int i=0; i< allfiles.length;i++){
            String photos[] = allfiles[i].split(".");
            if(photos[1].equals("jpg")){
                photonumber++;
            }
        }
        return photonumber;
    }
    private static int getAllVideoNumbers(){
        int videoonumber=0;
        File Dcim = new File(Environment.getExternalStorageDirectory()+"/Dcim/Camera/");
        String allfiles[] = Dcim.list();
        for(int i=0; i< allfiles.length;i++){
            String videos[] = allfiles[i].split(".");
            if(videos[1].equals("3gp")){
                videoonumber++;
            }
        }
        return videoonumber;
    }
    public static void changeCameraSettings(){//改变相机设置
        try {
            SettingsButton.click();
            if(StoreLocation.getText().equals("OFF") ) {
                StoreLocation.click();
            }
//            if(GeneralSetting.isSelected()){
//
//            }else if(CameraSetting.isSelected()){
//
//            }else if(VideoSetting.isSelected()){
//
//            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void checkRestoredefaults(){//检查恢复出厂设置是否成功
        try {
            SettingsButton.click();
            Assert.assertEquals("After restore defaults,the GPS state", "OFF",
                    StoreLocation.getText());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void setCameraRestoredefaults(){//恢复相机默认设置
        try {
            openAppliction("Camera");
            SettingsButton.clickAndWaitForNewWindow();
            RestreDefaultsButton.click();
            OKButton.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void launchCameraByShortcut(){//通过快捷方式启动相机
        try {
            gDevice.pressHome();
           gDevice.findObject(new UiSelector().text("Camera").fromParent(new UiSelector().index(0))).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void switchToFrontCamera(){//切换到前摄像头
        if(isFrontCamera()){
            try {
                FrontSwitchButton.click();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static void switchToBackCamera(){//切换到后摄像头
        if(!isFrontCamera()){
            try {
                FrontSwitchButton.click();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean  isFrontCamera(){//判断是否是前摄预览
        if(FlashButton.exists()){
            return false;
        }else{
            return true;
        }
    }
    public static void switchtoGallery(){//从相机进入图库
        try {
            if(!MiniBox.exists()){
                CaptureButton.click();
            }
            MiniBox.clickAndWaitForNewWindow();

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void manualFocus(){//手动对焦
        try {
            CenterFocusArea.click();
            waitTime(3);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
   public static void takePictures(){//拍照
       PhotosNumber = getAllPhotoNumbers();
       try {
           CaptureButton.click();
           waitTime(2);
       } catch (UiObjectNotFoundException e) {
           e.printStackTrace();
       }
   }
    public static boolean isDisplayIWantToButton(){//判断“Iwantto button”
        if(IWantToButton.exists()){
            return true;
        }else{
            return false;
        }
    }
    public static void deletePicture(){//删除照片
        PhotosNumber = getAllPhotoNumbers();
        try {
            switchtoGallery();
            clickIWantToButton();
            DeletePictureOption.clickAndWaitForNewWindow();
            OKButton.click();
            pressKey("back");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteVideo(){//删除视频
        try {
            switchtoGallery();
            clickIWantToButton();
            DeletePictureOption.clickAndWaitForNewWindow();
            OKButton.click();
            pressKey("back");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void recordVideo(int RecordTime){//录制视频
        VideosNumber =getAllVideoNumbers();
        try {
            RecordButton.click();
            waitTime(RecordTime);
            RecordButton.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void checkRecordVideoResult(){//检查录制视频结果
        int BeforeVideoNumber = VideosNumber;
        int CurrentVideoNumber = getAllVideoNumbers();
        Assert.assertEquals("checkTakePicturesResult",BeforeVideoNumber+1,CurrentVideoNumber);
    }

    public static void clickCenterScreen(){//点击屏幕
        gDevice.click(gDevice.getDisplayWidth()/2, gDevice.getDisplayHeight()/10);
    }
    public static void clickIWantToButton(){//点击“I want to”按钮
        try {
            while(!isDisplayIWantToButton()){
                clickCenterScreen();
            }
            IWantToButton.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void checkLaunchCameraResult(){
        assertEquals("launchCameraFromAppList",
                CAMERA_PACKAGE_NAME,gDevice.getCurrentPackageName());
    }
    public static void checkTakePicturesResult(){//检查拍照结果
        int BeforePhotoNumber = PhotosNumber;
        int CurrentPhotoNumber = getAllPhotoNumbers();
        Assert.assertEquals("checkTakePicturesResult",BeforePhotoNumber+1,CurrentPhotoNumber);
    }

    public static  void checkSwitchToGalleryResult(){
        Assert.assertEquals("switchToGalleryFromCamer",GALLERY,gDevice.getCurrentPackageName());
    }

    public static void checkdeleteResult(){//检查删除结果
        int BeforePhotoNumber = PhotosNumber;
        int CurrentPhotoNumber = getAllPhotoNumbers();
        int BeforeVideoNumber = VideosNumber;
        int CurrentVideoNumber = getAllVideoNumbers();
        boolean checkphotonumbers = (CurrentPhotoNumber == BeforePhotoNumber+1);
        boolean checkvideonumbers = (CurrentVideoNumber == BeforeVideoNumber+1);
        Assert.assertTrue("Delete function",(checkphotonumbers || checkvideonumbers));
    }
    @TargetApi(Build.VERSION_CODES.N)
    public static int getCurrentHOUR(){//得到当前小时
        Calendar c = Calendar.getInstance();
        int  hour = c.get(Calendar.HOUR_OF_DAY);
        return hour;
    }
    @TargetApi(Build.VERSION_CODES.N)
    public static int getCurrentMinute(){//得到当前分钟
        Calendar c = Calendar.getInstance();
        int  minute = c.get(Calendar.MINUTE);
        return minute;
    }
}
