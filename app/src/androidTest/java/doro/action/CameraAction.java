package doro.action;

import android.annotation.TargetApi;
import android.icu.util.Calendar;
import android.os.Build;
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
       // switchToHomePage();
        // getUiObjectByIdIndexIndex(HOME_GROUP_VIEW,1,0).clickAndWaitForNewWindow();
        try {
            gDevice.pressHome();
           gDevice.findObject(new UiSelector().text("Camera").fromParent(new UiSelector().index(0))).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void switchToFrontCamera(){//切换到前摄像头
        if(!isFrontCamera()){
            try {
                FrontSwitchButton.click();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static void switchToBackCamera(){//切换到后摄像头
        if(isFrontCamera()){
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
        try {
            switchtoGallery();
            clickIWantToButton();
            PictureDetailOption.click();
            CapturedPictureName = PictureNameOption.getText();
            pressKey("back");
            clickIWantToButton();
            DeletePictureOption.clickAndWaitForNewWindow();
            OKButton.click();
            pressKey("back");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteVideo(){
        try {
            switchtoGallery();
            clickIWantToButton();
            PictureDetailOption.click();
            CapturedPictureName = PictureNameOption.getText();
            pressKey("back");
            clickIWantToButton();
            DeletePictureOption.clickAndWaitForNewWindow();
            OKButton.click();
            pressKey("back");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void recordVideo(int RecordTime){//录制视频
        try {
            RecordButton.click();
            waitTime(RecordTime);
            RecordButton.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void checkRecordVideoResult(){//检查录制视频结果
        Assert.assertTrue("MiniBox Update",MiniBox.exists());  //minibox更新
        String name[] =checkName();//检查照片名称
        Assert.assertEquals("PictureNameSlice1:IMG", "Name: VID", name[0]);
        Assert.assertTrue("PictureNameSlice2:DATE", Boolean.valueOf(name[1]));
        Assert.assertTrue("PictureNameSlice3:Time", Boolean.valueOf(name[2]));
        try {
            pressKey("Back");
            clickCenterScreen();
            Assert.assertTrue("Play video",PlayVideoView.exists());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void clickCenterScreen(){//点击屏幕中心
        gDevice.click(gDevice.getDisplayWidth()/2, gDevice.getDisplayHeight()/2);
    }
    public static void clickIWantToButton(){//点击“I want to”按钮
        if(!isDisplayIWantToButton()){
            clickCenterScreen();
        }
        try {
            IWantToButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void checkLaunchCameraResult(){
        assertEquals("launchCameraFromAppList",
                CAMERA_PACKAGE_NAME,gDevice.getCurrentPackageName());
    }
    public static void checkTakePicturesResult(){//检查拍照结果
        Assert.assertTrue("MiniBox Update",MiniBox.exists());  //minibox更新
        String name[] =checkName();//检查照片名称
        Assert.assertEquals("PictureNameSlice1:IMG", "Name: IMG", name[0]);
        Assert.assertTrue("PictureNameSlice2:DATE", Boolean.valueOf(name[1]));
        Assert.assertTrue("PictureNameSlice3:Time", Boolean.valueOf(name[2]));
        try {
            pressKey("Back/Back");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        //照片是否是拍摄的照片（名字，照片内容）
    }

    private static String [] checkName() {//检查照片名字是否正常
        String[] NameResult = new String[0];
        try {

            clickIWantToButton();
            PictureDetailOption.click();
            String PictureName = PictureNameOption.getText();
            String[] names = PictureName.split("_");
            String PictureNameSlice1 = names[0];
            String PictureNameSlice2 = names[1];
            String PictureNameSlice3 = names[2];
            char[] dates = PictureNameSlice2.toCharArray();
            int year = Integer.valueOf("" + dates[0] + dates[1] + dates[2] + dates[3]);
            int Month = Integer.valueOf("" + dates[4] + dates[5]);
            int day = Integer.valueOf("" + dates[6] + dates[7]);
            char[] times = PictureNameSlice3.toCharArray();
            int Hours = Integer.valueOf("" + times[0] + times[1]);
            int Minute = Integer.valueOf("" + times[2] + times[3]);
            boolean DateRight = false;
            boolean TimeRight = false;
            if (year == getCurrentYear()) {
                if (Month == getCurrentMonth()) {
                    if (day == getCurrentDay()) {
                        DateRight = true;
                    }
                }
            }
            if (Hours == getCurrentHOUR()) {//暂时未考虑59到00
                if (Minute == getCurrentMinute() || (Minute - 1) == getCurrentMinute()) {
                    TimeRight = true;
                }
            }
            NameResult = new String[]{PictureNameSlice1, String.valueOf(DateRight),
                    String.valueOf(TimeRight)};
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        return NameResult;
    }

    public static  void checkSwitchToGalleryResult(){
        Assert.assertEquals("switchToGalleryFromCamer",GALLERY,gDevice.getCurrentPackageName());
    }

    public static void checkdeleteResult(){
        File CapturedPicture = new File("/sdcard/dcim/"+CapturedPictureName);
        Assert.assertTrue("deletePicures", !CapturedPicture.exists());
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static int getCurrentHOUR(){//得到当前小时
        Calendar c = Calendar.getInstance();
        int  hour = c.get(Calendar.HOUR);
        return hour;
    }
    @TargetApi(Build.VERSION_CODES.N)
    public static int getCurrentMinute(){//得到当前分钟
        Calendar c = Calendar.getInstance();
        int  minute = c.get(Calendar.MINUTE);
        return minute;
    }
}
