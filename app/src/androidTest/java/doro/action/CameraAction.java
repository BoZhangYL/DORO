package doro.action;

import android.os.Environment;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.squareup.spoon.Spoon;

import org.hamcrest.Asst;
import org.junit.Assert;

import java.io.File;

import ckt.base.VP4;
import doro.page.CameraPage;
import doro.page.EmailPage;

import static doro.page.CameraPage.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by bo.zhang on 2016/12/15   .
 */

public class CameraAction extends VP4 {
    private static UiObject FrontSwitchButton = getObjectById(CAMERA_FRONT_SWITCH_BUTTON);
    private static UiObject FlashButton = getObjectById(CAMERA_FLASH_BUTTON);
    private static UiObject CaptureButton = getObjectById(CAMERA_CAPTURE_BUTTON);
    private static UiObject RecordButton = getObjectById(CAMERA_RECORD_BUTTON);
    private static UiObject MiniBox = getObjectById(CAMERA_MINI_BOX);
    private static UiObject CenterFocusArea = getObjectById(CAMERA_FOCUS_AREA);
    private static UiObject SettingsButton = getObjectById(CAMERA_SETTINGS_BUTTON);
    private static UiObject IWantToButton = getObjectByText(I_WANT_TO_BUTTON);
    private static UiObject LocationState = getObjectByDesc(
            (CAMERASETTINGS_STATE_LOCATION));
    private static UiObject StoreLocation = getObjectByText(CAMERASETTINGS_STORELOCATION);
    private static UiObject RestreDefaultsButton = getObjectByText(RESTORE_DEFAULTS);
    private static UiObject OKButton = getUiObjectByText(OK_BUTTON);
    private static UiObject DeletePictureOption = getObjectByText(DELETE_PICTURES);
    private static UiObject DeleteVideoOption = getObjectByText(DELETE_VIDEOS);
    private static UiObject RecordTimeIcon = getObjectById(RECORDTIME);
    private static UiObject SnapButton = getUiObjectByText(CameraPage.SNAP_BUTTON);
    private static UiObject APhotoButton = getUiObjectByText(CameraPage.A_PHOTO_BITTON);
    private static UiObject AVideoButton = getObjectByText(CameraPage.A_VIDEO_BUTTON);
    private static UiObject ASelfieButton = getObjectByText(CameraPage.A_SELFIE_BUTTTON);

    /*得到当前所有的拍摄的照片数
    * 通过判断/Dcim/Camera/文件夹下所有jpg结尾的文件数
    * */
    private static int getAllPhotoNumbers() {
        int photonumber = 0;
        File Dcim = new File(Environment.getExternalStorageDirectory() + "/Dcim/Camera/");
        File Dcim_SD = new File("/mnt/m_external_sd/Dcim/Camera/");
        String[] names = Dcim.list();
        String[] names1 = Dcim_SD.list();
        if (Dcim.exists()) {
            for (int i = 0; i < names.length; i++) {
                String[] photos = names[i].split("\\.");
                if (photos[1].equals("jpg")) {
                    photonumber++;
                }
            }
        }
        if (Dcim_SD.exists()) {
            for (int j = 0; j < names1.length; j++) {
                String[] photos1 = names1[j].split("\\.");
                if (photos1[1].equals("jpg")) {
                    photonumber++;
                }
            }
        }
        return photonumber;
    }

    /*得到当前所有的拍摄的视频数
    * 通过判断/Dcim/Camera/文件夹下所有3gp结尾的文件数
    * */
    private static int getAllVideoNumbers() {
        int videoonumber = 0;
        File Dcim = new File(Environment.getExternalStorageDirectory() + "/Dcim/Camera/");
        File Dcim_SD = new File("/mnt/m_external_sd/Dcim/Camera/");
        String[] names = Dcim.list();
        String[] names1 = Dcim_SD.list();
        if (Dcim.exists()) {
            for (int i = 0; i < names.length; i++) {
                String[] photos = names[i].split("\\.");
                if (photos[1].equals("3gp")) {
                    videoonumber++;
                }
            }
        }
        if (Dcim_SD.exists()) {
            for (int j = 0; j < names1.length; j++) {
                String[] photos1 = names1[j].split("\\.");
                if (photos1[1].equals("3gp")) {
                    videoonumber++;
                }
            }
        }
        return videoonumber;
    }

    /*改变相机的GPS位置设置
    * */
    public static void changeCameraSettings() {
        waitTime(5);
        try {
            SettingsButton.click();
            if (LocationState.getText().equals("OFF")) {
                StoreLocation.click();
            }
            waitTime(2);
            while (getObjectByText("ALLOW").exists()) {
                getObjectByText("ALLOW").clickAndWaitForNewWindow();
            }
            waitTime(2);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*检查恢复出厂设置是否成功
    * */
    public static void checkRestoredefaults() {
        waitTime(5);
        try {
            SettingsButton.click();
            Assert.assertEquals("After restore defaults,the GPS state", "OFF",
                    LocationState.getText());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*恢复相机默认设置
    * */
    public static void setCameraRestoredefaults() {
        try {
            openAppliction("Camera");
            SettingsButton.clickAndWaitForNewWindow();
            RestreDefaultsButton.click();
            OKButton.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*通过快捷方式启动相机
    * */
    public static void launchCameraByShortcut() {
        try {
            gDevice.pressHome();
            gDevice.findObject(new UiSelector().text("Camera").fromParent(new UiSelector().
                    index(0))).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
            Spoon.screenshot("launchCameraByShortcut");
        }
    }

    /*切换到前摄像头
    * */
    public static void switchToFrontCamera() {
        if (isFrontCamera()) {
            try {
                FrontSwitchButton.clickAndWaitForNewWindow();
                Spoon.screenshot("switchToFrontCamera");
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /*切换到后摄像头
    * */
    public static void switchToBackCamera() {
        if (!isFrontCamera()) {
            try {
                FrontSwitchButton.clickAndWaitForNewWindow();
                Spoon.screenshot("switchToBackCamera");
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /*判断是否是前摄预览
    * */
    public static boolean isFrontCamera() {
        if (FlashButton.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /*从相机进入图库
    * */
    public static void switchtoGallery() {
        waitTime(2);
        try {
            if (!MiniBox.exists()) {
                CaptureButton.click();
            }
            MiniBox.clickAndWaitForNewWindow();
            Spoon.screenshot("switchtoGallery");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*手动对焦
    * */
    public static void manualFocus() {
        try {
            CenterFocusArea.click();
            waitTime(5);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*拍照
    * */
    public static void takePictures() {
        int beforePictureNum = getAllPhotoNumbers();
        try {
            CaptureButton.click();
            waitTime(10);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        Asst.assertEquals("take pictures fail", beforePictureNum + 1, getAllPhotoNumbers());
    }

    /*判断“Iwantto button”是否显示
    * */
    public static boolean isDisplayIWantToButton() {
        if (IWantToButton.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /*删除照片
    * */
    public static void deletePicture() {
        waitTime(5);
        int currentPhotosNumber = getAllPhotoNumbers();
        try {
            switchtoGallery();
            clickIWantToButton();
            DeletePictureOption.clickAndWaitForNewWindow();
            OKButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        Asst.assertEquals("Delete pictures fail", currentPhotosNumber - 1, getAllPhotoNumbers());
    }


    /*删除视频
    * */
    public static void deleteVideo() {
        int beforeVideosNumber = getAllVideoNumbers();
        try {
            switchtoGallery();
            clickIWantToButton();
            DeleteVideoOption.clickAndWaitForNewWindow();
            OKButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        Asst.assertEquals("Delete Video fail", beforeVideosNumber - 1, getAllVideoNumbers());
    }

    /*录制视频
    * */
    public static void recordVideo(int RecordTime) {
        int beforeVideosNumber = getAllVideoNumbers();
        try {
            RecordButton.click();
            waitTime(RecordTime);
            RecordButton.click();
            waitTime(10);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        Asst.assertEquals("record Video fail", beforeVideosNumber + 1, getAllVideoNumbers());
    }


    /*点击屏幕
    * */
    public static void clickCenterScreen() {
        gDevice.click(gDevice.getDisplayWidth() / 2, gDevice.getDisplayHeight() / 10);
    }

    /*点击“I want to”按钮
    * */
    public static void clickIWantToButton() {
        try {
            while (!isDisplayIWantToButton()) {
                clickCenterScreen();
            }
            IWantToButton.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*检查打开相机是否成功
    * */
    public static void checkLaunchCameraResult() {
        Spoon.screenshot("checkLaunchCameraResult");
        assertEquals("launchCameraFromAppList",
                CAMERA_PACKAGE_NAME, gDevice.getCurrentPackageName());
    }


    /*检查从相机进入图库的结果
    * */
    public static void checkSwitchToGalleryResult() {
        Spoon.screenshot("checkSwitchToGalleryResult");
        Assert.assertEquals("switchToGalleryFromCamer", GALLERY, gDevice.getCurrentPackageName());
    }


    /*检查停止录像
    * */
    public static void checkStopRecoed() {
        int beforeVideosNumber = getAllVideoNumbers();
        boolean RecordState = false;
        try {
            RecordButton.click();
            waitTime(5);
            RecordButton.click();
            if (!RecordTimeIcon.exists()) {
                RecordState = true;
            }
            Asst.assertTrue("checkStopRecoed", RecordState);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        Asst.assertEquals("Check record video fail ", beforeVideosNumber + 1, getAllVideoNumbers());
    }

    /*
    * 点击snap按钮
    * */
    public static void clickSnapButton() {
        if (SnapButton.exists()) {
            try {
                SnapButton.clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            VP4.switchToMenuPage();
            try {
                SnapButton.clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * 点击A Photo按钮
    * */
    public static void clickAPhotoButton() {
        try {
            APhotoButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 点击A Video 按钮
    * */

    public static void clickAVideoButton() {
        try {
            AVideoButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 点击A Selfie按钮
    * */
    public static void clickASelfieButton() {
        try {
            ASelfieButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void checkAPhotoView() {
        waitTime(5);
        Asst.assertTrue("通过A Photo按钮打开相机，闪光灯按钮不存在", FlashButton.exists());
        Asst.assertTrue("通过A Photo按钮打开相机，拍照按钮不存在", CaptureButton.exists());
        Asst.assertTrue("通过A Photo按钮打开相机，录像按钮存在", !RecordButton.exists());
    }

    public static void checkAVideoView() {
        waitTime(5);
        Asst.assertTrue("通过A Video按钮打开相机，闪光灯按钮不存在", FlashButton.exists());
        Asst.assertTrue("通过A Video按钮打开相机，拍照按钮存在", !CaptureButton.exists());
        Asst.assertTrue("通过A Video按钮打开相机，录像按钮不存在", RecordButton.exists());
    }

    public static void checkASelfie() {
        waitTime(5);
        Asst.assertTrue("通过A Selfie按钮打开相机，闪光灯按钮存在", !FlashButton.exists());
        Asst.assertTrue("通过A Selfie按钮打开相机，拍照按钮不存在", CaptureButton.exists());
        Asst.assertTrue("通过A Selfie按钮打开相机，录像按钮存在", !RecordButton.exists());
    }
}
