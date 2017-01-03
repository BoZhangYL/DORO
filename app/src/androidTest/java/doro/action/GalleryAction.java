package doro.action;

import android.graphics.Rect;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.hamcrest.Asst;

import ckt.base.VP4;
import doro.page.CameraPage;
import doro.page.GalleryPage;

/**
 * Created by bo.zhang on 2016/12/27   .
 */

public class GalleryAction extends VP4{
    private static int ColumnsNumber=GalleryPage.DEFAULT_COLUMNS_NUMBER;//每行显示的照片数
    private static int GalleryPicturesNumbers = 0;
    private static int GalleryVideosNumbers = 0;
    private static int GalleryFavouriteNumber =0;
    /*
    * 从app list打开图库
    * */
    public static void launchGalleryFromAppList(){
        openAppliction(GalleryPage.GALLERY);
    }

    /*
    * 检查图库打开是否成功
    * */
    public static void checkLaunchGallery(){
        Asst.assertEquals("checkLaunchGallery", GalleryPage.GALLERY_PACKAGE,
                gDevice.getCurrentPackageName());
    }

    /*
    * 从联系人打开图库
    * */
    public static void launchGalleryFromContacts(){
        openAppliction("Cont\u200Bacts");
        clickContactsAddContactButton();
        clickContactsEditPictureButton();
    }

    /*
    * 从相机打开图库
    * */
    public static void launchGalleryFromCamera(){
        openAppliction(CameraPage.CAMERA);
        CameraAction.switchtoGallery();
    }

    /*
    * 使用返回键退出图库
    * */
    public static void exitGalleryByBackKey(){
        try {
            pressKey("Back");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 使用home键退出图库
    * */
    public static void exitGalleryByHomeKey(){
        try {
            pressKey("Home");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 检查退出图库是否正常
    * */
    public static void checkExitGallery(){
        Asst.assertTrue("checkExitGallery",!GalleryPage.GALLERY_PACKAGE.
                equals(gDevice.getCurrentPackageName()));
    }

    /*
    * 点击添加联系人按钮
    * */
    private static void clickContactsAddContactButton(){
        clickAndWaitForNewWindowByText(GalleryPage.CONTACTS_ADD_CONTACT);
    }

    /*
    * 在添加新联系人界面点击添加图片按钮
    * */
    private static void clickContactsEditPictureButton(){
        clickAndWaitForNewWindowByID(GalleryPage.CONTACTS_EDIT_PICTURE);
    }

    /*
    * 得到一些图片和视频
    * */
    public static void getSomePicturesVideos(){
//        openAppliction(CameraPage.CAMERA);
//        CameraAction.takePictures();
//        CameraAction.takePictures();
//        CameraAction.takePictures();
//        CameraAction.takePictures();
//        CameraAction.recordVideo(CameraPage.DEFAULT_RECORD_TIMES);
        openAppliction(GalleryPage.GALLERY);
        getGalleryPctureVideoNumbers();
    }

    /*
    * 点击I want to按钮
    * */
    private static void clickIWantToButton(){
        clickAndWaitForNewWindowByID(GalleryPage.IWANTTO_BUTTON);
    }

    /*
    * 点击diaplay...按钮
    * */
    private static void clickDisplayOption(){
        clickAndWaitForNewWindowByText(GalleryPage.GALLERYSETTINGS_DISPLAY);
    }

    /*
    * 切换到My Gallery显示
    * */
    public static void changeToMyGalleryDisplay(){

        clickIWantToButton();
        clickDisplayOption();
        clickAndWaitForNewWindowByText(GalleryPage.GALLERYSETTINGS_DISPLAY_MYGALLERY);
    }

    /*
    * 检查MyGalley显示
    * */
    public static void checkMyGalleryDisplay(){
        int[] numbers = getCurrentPicturesVideosNum();
        int pictureNumber = numbers[0];
        int VideoNumber = numbers[1];
        Asst.assertEquals("checkMyGalleryDisplay",GalleryPicturesNumbers, pictureNumber);
        Asst.assertEquals("checkMyGalleryDisplay",GalleryVideosNumbers, VideoNumber);
    }

    /*
    * 切换到All pictures 显示
    * */
    public static void changeToAllPicturesDisplay(){
        clickIWantToButton();
        clickDisplayOption();
        clickAndWaitForNewWindowByText(GalleryPage.GALLERYSETTINGS_DISPLAY_ALLPICTURES);

    }

    /*
    * 检查All Pictures显示
    * */
    public static void checkAllPicturesDisplay(){
        int[] numbers = getCurrentPicturesVideosNum();
        int pictureNumber = numbers[0];
        int VideoNumber = numbers[1];
        Asst.assertEquals("checkAllPicturesDisplay",GalleryPicturesNumbers, pictureNumber);
        Asst.assertEquals("checkAllPicturesDisplay",0, VideoNumber);
    }

    /*
    * 切换到All Video显示
    * */
    public static void changeToAllVideosDisplay(){
        clickIWantToButton();
        clickDisplayOption();
        clickAndWaitForNewWindowByText(GalleryPage.GALLERYSETTINGS_DISPLAY_ALLVIDEOS);
    }

    /*
    * 检查All Video 显示
    * */
    public static void checkAllVideoDisplay(){
        int[] numbers = getCurrentPicturesVideosNum();
        int pictureNumber = numbers[0];
        int VideoNumber = numbers[1];
        Asst.assertEquals("checkAllVideoDisplay",0, pictureNumber);
        Asst.assertEquals("checkAllVideoDisplay",GalleryVideosNumbers, VideoNumber);
    }

    /*
    * 切换到Favourites显示
    * */
    public static void changeToFavouritesDisplay(){
        clickIWantToButton();
        clickDisplayOption();
        clickAndWaitForNewWindowByText(GalleryPage.GALLERYSETTINGS_DISPLAY_FAVOURITES);
    }

    /*
    * 检查favourite显示
    * */
    public static void checkFavouriteDisplay(){
        int[] numbers = getCurrentPicturesVideosNum();
        int FavouriteNumber = numbers[2];
        Asst.assertEquals("checkFavouriteDisplay",GalleryFavouriteNumber, FavouriteNumber);
    }

    /*
    * 点击text打开一个新窗口
    * */
    public static void clickAndWaitForNewWindowByText(String text){
        try {
            getObjectByText(text).
                    clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 通过id点击控件打开一个新窗口
    * */
    public static void clickAndWaitForNewWindowByID(String ResourceID){
        try {
            getObjectById(ResourceID).
                    clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 向下移动一个网格页面的距离
    * Rect OnePageBounds =getObjectById(GalleryPage.GALLERY_GRAID_VIEW).getBounds();
    * */
    private static void moveDownOneGraidViewPage(Rect rect){
            int x0 = rect.right/2;
            int y0 = rect.bottom;
            int x1 = x0;
            int y1 = rect.top;
            gDevice.swipe(x0,y0,x1,y1, VP4.STEP_NORMAL);
    }

    /*
   * 得到图库显示的照片数，视频数
   * */
    private static void getGalleryPctureVideoNumbers(){
        changeToMyGalleryDisplay();
        UiObject GraidView = getObjectById(GalleryPage.GALLERY_GRAID_VIEW);
        int ExpectColumns;
        int ActColumns;
        if(ColumnsNumber ==2){
            ExpectColumns = 6;
        }else if(ColumnsNumber ==3){
            ExpectColumns = 15;
        }else if(ColumnsNumber ==4){
            ExpectColumns = 24;
        }
        try {
            do{
                ActColumns = GraidView.getChildCount();
            for (int i=0; i<ActColumns; i++){
                if(isFavourite(i)){
                    GalleryFavouriteNumber++;
                }
                String names =getPictureVideoNmae(i);
                String[]name = names.split("\\.");
                if(name[1].equals("jpg") || name[1].equals("png")||name[1].equals("gif")){
                    GalleryPicturesNumbers++;
                }else /*if(name[1].equals("3gp") || name[1].equals("mp4") || name[1].equals("mp4"))*/{
                    GalleryVideosNumbers++;
                }
                moveDownOneGraidViewPage(GraidView.getBounds());
            }
                }while(ActColumns!=ColumnsNumber);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 得到当前的图片数，视频数
    * */
    public static int[] getCurrentPicturesVideosNum(){
        UiObject GraidView = getObjectById(GalleryPage.GALLERY_GRAID_VIEW);
        int ExpectColumns;
        int ActColumns;
        int numbers[] ={0,0,0};
        if(ColumnsNumber ==2){
            ExpectColumns = 6;
        }else if(ColumnsNumber ==3){
            ExpectColumns = 15;
        }else if(ColumnsNumber ==4){
            ExpectColumns = 24;
        }
        try {
            do{
                ActColumns = GraidView.getChildCount();
                for (int i=0; i<ActColumns; i++){
                    if(isFavourite(i)){
                        numbers[2]++;
                    }
                    String names =getPictureVideoNmae(i);
                    String[]name = names.split("\\.");
                    if(name[1].equals("jpg") || name[1].equals("png")||name[1].equals("gif")){
                        numbers[0]++;
                    }else{
                        numbers[1]++;
                    }
                    moveDownOneGraidViewPage(GraidView.getBounds());
                }
            }while(ActColumns!=ColumnsNumber);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    /*
    * 得到当前网格界面所有照片，视频的名字
    * */
    private static String getPictureVideoNmae(int indexs){
        String name = null;
        try {
            name = gDevice.findObject(new UiSelector().resourceId(GalleryPage.GALLERY_GRAID_VIEW).
                    index(indexs).index(0).index(0).index(1)).getContentDescription();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }

    /*
    * 判断是否是favourite
    * */
    private static boolean isFavourite(int indexs){
        boolean FavouriteState =false;
        FavouriteState = gDevice.findObject(new UiSelector().resourceId(GalleryPage.GALLERY_GRAID_VIEW).
                index(indexs).index(0).index(0).index(2)).exists();
        return FavouriteState;
    }
}

