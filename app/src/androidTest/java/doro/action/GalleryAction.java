package doro.action;

import android.support.test.uiautomator.UiObjectNotFoundException;

import org.hamcrest.Asst;

import ckt.base.VP4;
import doro.page.CameraPage;
import doro.page.GalleryPage;

/**
 * Created by bo.zhang on 2016/12/27   .
 */

public class GalleryAction extends VP4{
    private static int ColumnsNumber=GalleryPage.DEFAULT_COLUMNS_NUMBER;
    public static void launchGalleryFromAppList(){
        openAppliction(GalleryPage.GALLERY);
    }
    public static void checkLaunchGallery(){
        Asst.assertEquals("checkLaunchGallery", GalleryPage.GALLERY_PACKAGE,
                gDevice.getCurrentPackageName());
    }
    public static void launchGalleryFromContacts(){
        openAppliction("Cont\u200Bacts");
        clickContactsAddContactButton();
        clickContactsEditPictureButton();
    }
    public static void launchGalleryFromCamera(){
        openAppliction(CameraPage.CAMERA);
        CameraAction.switchtoGallery();
    }
    public static void exitGalleryByBackKey(){
        try {
            pressKey("Back");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void exitGalleryByHomeKey(){
        try {
            pressKey("Home");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void checkExitGallery(){
        Asst.assertTrue("checkExitGallery",!GalleryPage.GALLERY_PACKAGE.
                equals(gDevice.getCurrentPackageName()));
    }
    private static void clickContactsAddContactButton(){
        try {
            getObjectByText(GalleryPage.CONTACTS_ADD_CONTACT).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void clickContactsEditPictureButton(){
        try {
            getObjectById(GalleryPage.CONTACTS_EDIT_PICTURE).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}

