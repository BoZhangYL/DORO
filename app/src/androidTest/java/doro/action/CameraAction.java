package doro.action;

import android.annotation.TargetApi;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.test.uiautomator.Tracer;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

import org.junit.Assert;

import java.io.File;

import ckt.base.VP4;

import static doro.action.CalendarAction.getCurrentDay;
import static doro.action.CalendarAction.getCurrentMonth;
import static doro.action.CalendarAction.getCurrentYear;
import static doro.page.CameraPage.*;

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
    public static void launchCameraByShortcut(){
       // switchToHomePage();
        // getUiObjectByIdIndexIndex(HOME_GROUP_VIEW,1,0).clickAndWaitForNewWindow();
        try {
            gDevice.pressHome();
            gDevice.findObject(new UiSelector().resourceId("com.doro.apps.launcher3:id/this_is_da_boss_layout").index(2).index(1)).
                    clickAndWaitForNewWindow();
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
    public static void switchtoGallery(){
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
            MiniBox.clickAndWaitForNewWindow();
            if(!isDisplayIWantToButton()){
                gDevice.click(gDevice.getDisplayWidth()/2, gDevice.getDisplayHeight()/2);
            }
            IWantToButton.clickAndWaitForNewWindow();
            PictureDetailOption.click();
            CapturedPictureName = PictureNameOption.getText();
            pressKey("back");
            if(!isDisplayIWantToButton()){
                gDevice.click(gDevice.getDisplayWidth()/2, gDevice.getDisplayHeight()/2);
            }
            IWantToButton.clickAndWaitForNewWindow();
            DeletePictureOption.clickAndWaitForNewWindow();
            OKButton.click();
            pressKey("back");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void checkTakePicturesResult(){//检查拍照结果
        Assert.assertTrue("MiniBox Update",MiniBox.exists());  //minibox更新
        checkPictureName();//检查照片名称
    //照片是否是拍摄的照片（名字，照片内容）
    }

    private static void checkPictureName(){
        try {

            if(!isDisplayIWantToButton()){
                gDevice.click(gDevice.getDisplayWidth()/2, gDevice.getDisplayHeight()/2);
            }
            IWantToButton.clickAndWaitForNewWindow();
            PictureDetailOption.click();
            String PictureName = PictureNameOption.getText();
            String [] names = PictureName.split("_");
            String PictureNameSlice1 = names[0];
            String PictureNameSlice2 = names[1];
            String PictureNameSlice3 = names[2];
            char []dates =PictureNameSlice2.toCharArray();
            int year = Integer.valueOf(""+dates[0]+dates[1]+dates[2]+dates[3]);
            int Month = Integer.valueOf(""+dates[4]+dates[5]);
            int day = Integer.valueOf(""+dates[6]+dates[7]);
            char[]times =PictureNameSlice3.toCharArray();
            int Hours = Integer.valueOf(""+times[0]+times[1]);
            int Minute = Integer.valueOf(""+times[2]+times[3]);
            boolean DateRight = false;
            boolean TimeRight = false;
            if(year == getCurrentYear()){
                if (Month == getCurrentMonth()){
                    if(day == getCurrentDay()){
                        DateRight = true;
                    }
                }
            }
            if(Hours == getCurrentHOUR() ){//暂时未考虑59到00
                if(Minute == getCurrentMinute() || (Minute-1) == getCurrentMinute()  ){
                    TimeRight = true;
                }
            }
            Assert.assertEquals("PictureNameSlice1:IMG","Name: IMG",PictureNameSlice1);
            Assert.assertTrue("PictureNameSlice2:DATE",DateRight);
            Assert.assertTrue("PictureNameSlice3:Time",TimeRight);
            pressKey("Back/Back");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static  void checkSwitchToGalleryResult(){
        Assert.assertEquals("switchToGalleryFromCamer",GALLERY,gDevice.getCurrentPackageName());
    }
    public static void checkdeletePicuresResult(){
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
