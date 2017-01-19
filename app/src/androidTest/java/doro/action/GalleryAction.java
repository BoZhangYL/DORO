package doro.action;

import android.graphics.Rect;
import android.os.RemoteException;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.view.View;

import org.hamcrest.Asst;

import ckt.base.VP4;
import doro.page.CameraPage;
import doro.page.GalleryPage;

/**
 * Created by bo.zhang on 2016/12/27   .
 */

public class GalleryAction extends VP4{
    private static UiCollection GridView = new UiCollection(new UiSelector().
            resourceId(GalleryPage.GALLERY_GRAID_VIEW));//网格视图
    private static UiSelector images = new UiSelector().resourceId(GalleryPage.GRID_IMAGE);//图片名
    private static UiSelector Favourite = new UiSelector().resourceId(GalleryPage.
            GALLERY_FAVORITE_ICON);//最喜爱图标
    private static UiSelector DeleteCheckBox = new UiSelector().resourceId(GalleryPage.
            GALLERY_DELETE_CHECK_BOX);//删除选择框
    private static int ColumnsNumber=GalleryPage.DEFAULT_COLUMNS_NUMBER;//每行显示的照片数
    private static int GalleryPicturesNumbers = 0; //图库中的图片数
    private static int GalleryVideosNumbers = 0; //图库中的视频数
    private static int GalleryFavouriteNumber ; //图库中当前的最喜爱数
    private static int AllFavours=0;//图库中所有的favourite数量
    private static String[] AllCurrentNamess = new String[GalleryPage.MAX_PICTURES_NUMBERS];
    //当前所有图片视频的名字
    private static String FirstGridName=null; //图库中favourite数量
    private static UiScrollable scr = new UiScrollable(new UiSelector().scrollable(true));
    private static UiSelector DisplayEmpty =new UiSelector().resourceId(GalleryPage.DISPLAY_EMPTY);
    /*
    * 播放视频
    * */
    public static void playOneVideo(){
        try {
            Asst.assertTrue("图库没有一个视频",!(GridView.getChildCount()==0));
            GridView.getChildByInstance(images,0).clickAndWaitForNewWindow();
            gDevice.click(gDevice.getDisplayWidth()/2,gDevice.getDisplayHeight()/2);
            gDevice.click(gDevice.getDisplayWidth()/2,gDevice.getDisplayHeight()/2);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    * 点击单个视频设置选项
    * */
    public static void clickGingleVideoOption(){
        try {
            Asst.assertTrue("图库没有一个视频",!(GridView.getChildCount()==0));
            GridView.getChildByInstance(images,0).clickAndWaitForNewWindow();
            if(!getObjectByText(GalleryPage.IWANTTO_BUTTON).exists()){
                gDevice.click(gDevice.getDisplayWidth()/10,gDevice.getDisplayHeight());
            }
            clickIWantToButton();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    /*
    * 检查当视频设置选项
    * */
    public static void checkSingleVideoOption(){

        Asst.assertTrue("分享视频按钮不存在",getObjectByText(GalleryPage.
                GALLERY_SINGLE_VIDEO_OPTION_SHARE_VIDEO).exists());
        Asst.assertTrue("视频详情按钮不存在",getObjectByText(GalleryPage.
                GALLERY_SINGLE_VIDEO_OPTION_VIDEO_DETAILS).exists());
        Asst.assertTrue("添加/删除Favourite按钮不存在",getObjectByText(GalleryPage.
                GALLERY_SINGLE_VIDEO_OPTION_ADD_TO_FAVOURITE).exists() || getObjectByText
                (GalleryPage.GALLERY_SINGLE_VIDEO_OPTION_REMOVE_FOVOURITR).exists());
        Asst.assertTrue("删除视频按钮不存在",getObjectByText(GalleryPage.
                GALLERY_SINGLE_VIDEO_OPTION_DELETE_VIDEO).exists());
    }

    /*
    * 从单张图片选项界面删除一张图片
    * */
    public static void deleteSinglePicture(){
        VP4.scrollToEnd(20);
        try {
            getObjectByText(GalleryPage.GALLERY_SINGLE_PICTURE_OPTION_DELETE_PICTURE).
                    clickAndWaitForNewWindow();
            clickOKButton();
            GalleryPicturesNumbers --;
            while(!getObjectById(GalleryPage.GALLERY_HEADER).exists()){
                pressKey("back");
            }
            changeToMyGalleryDisplay();
            waitTime(5);
            int numbers[] =getCurrentPicturesVideosNum();
            Asst.assertEquals("从单张图片设置界面删除图片失败",GalleryPicturesNumbers,numbers[0]);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }


    /*
    * 点击单张图片菜单按钮
    * */
    public static void clickSinglePictureOption(){
        try {
            Asst.assertTrue("图库没有一张图片",!(GridView.getChildCount()==0));
            GridView.getChildByInstance(images,0).clickAndWaitForNewWindow();
            if(!getObjectByText(GalleryPage.IWANTTO_BUTTON).exists()){
                gDevice.click(gDevice.getDisplayWidth()/10,gDevice.getDisplayHeight());
            }
            clickIWantToButton();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 检查单张图片菜单显示
    * */
    public static void checkSinglePictureOption(){
        Asst.assertTrue("编辑图片按钮不存在",getObjectByText(GalleryPage.
                GALLERY_SINGLE_PICTURE_OPTION_EDIT_PICTURE).exists());
        Asst.assertTrue("设置图片按钮不存在",getObjectByText(GalleryPage.
                GALLERY_SINGLE_PICTURE_OPTION_SET_PICTURE).exists());
        Asst.assertTrue("分享图片按钮不存在",getObjectByText(GalleryPage.
                GALLERY_SINGLE_PICTURE_OPTION_SHARE_PICTURE).exists());
        Asst.assertTrue("图片详情按钮不存在",getObjectByText(GalleryPage.
                GALLERY_SINGLE_PICTURE_OPTION_PICTURE_DETAIL).exists());
        Asst.assertTrue("添加/删除favourite按钮不存在",getObjectByText(GalleryPage.
                GALLERY_SINGLE_PICTURE_OPTION_ADD_TO_FAVOURITE).exists() || getObjectByText
                (GalleryPage.GALLERY_SINGLE_VIDEO_OPTION_REMOVE_FOVOURITR).exists());
        VP4.scrollToEnd(20);
        Asst.assertTrue("删除图片按钮不存在",getObjectByText(GalleryPage.
                GALLERY_SINGLE_PICTURE_OPTION_DELETE_PICTURE).exists());
    }

    /*
    * 检查横屏模式
    * */
    public static void swtchToLandscapeMode(){
        waitTime(5);
        //int height =gDevice.getDisplayHeight();
        try {
            gDevice.setOrientationLeft();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
//        try {
//            Asst.assertEquals("图库界面旋转显示不成功",height,
//                    getObjectById(GalleryPage.SDADOW_TOP).getBounds().right);
//        } catch (UiObjectNotFoundException e) {
//            e.printStackTrace();
//        }
    }
    /*
    * 切换到竖屏模式
    * */
    public static void switchToPortraitMode(){
        waitTime(5);
        try {
            gDevice.setOrientationNatural();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /*
    *查看显示设置
    * */
    public static void openDisplaySettings(){
        clickIWantToButton();
        clickDisplayOption();
        Asst.assertTrue("My gallery选项不存在",getObjectByText(GalleryPage.
                GALLERYSETTINGS_DISPLAY_MYGALLERY).exists());
        Asst.assertTrue("All pictures选项不存在", getObjectByText(GalleryPage.
                GALLERYSETTINGS_DISPLAY_ALLPICTURES).exists());
        Asst.assertTrue("All videos 选项不存在",getObjectByText(GalleryPage.
                GALLERYSETTINGS_DISPLAY_ALLVIDEOS).exists());
        Asst.assertTrue("Favourites选项不存在",getObjectByText(GalleryPage.
                GALLERYSETTINGS_DISPLAY_FAVOURITES).exists());
    }

    /*
    * 查看gallery Settings
    * */
    public static void openGallerySettings(){
        clickIWantToButton();
        clickSet();
        Asst.assertTrue("每列显示的个数选项不存在",getObjectByText(GalleryPage.
                GALLERYSETTINGS_SET_COLUMNS).exists());
        Asst.assertTrue("默认显示选项不存在",getObjectByText(GalleryPage.
                GALLERYSETTINGS_SET_DEFAULTDISPLAY).exists());

    }

    private static void clickSet(){
        try {
            getObjectByText(GalleryPage.GALLERYSETTINGS_SET).clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    /*
    * 添加一个favourite
    * */
    public static void makeOneFavorite(){
        clickIWantToButton();
        try {
            getObjectByText(GalleryPage.GALLERYSETTINGS_FAVOURITE).click();
            if(GridView.getChildCount()>0){
                UiObject OnePicture =GridView.getChildByInstance(images,0);
                OnePicture.click();
                clickConfirmButton();
                waitTime(5);
                GalleryFavouriteNumber++;
                int number[] = getCurrentPicturesVideosNum();
                //int currentFov = GridView.getChildByInstance(Favourite,0).getChildCount();
                Asst.assertEquals("添加一个favourite失败",GalleryFavouriteNumber, number[2]);
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 删除一个favourite
    * */
    public static void unmakeOneFavorite(){
        clickIWantToButton();
        try {
            getObjectByText(GalleryPage.GALLERYSETTINGS_FAVOURITE).click();
            if(GridView.getChildCount()>0){
                UiObject OnePicture =GridView.getChildByInstance(images,0);
                OnePicture.click();
                clickConfirmButton();
                waitTime(5);
                GalleryFavouriteNumber--;
                int number[] = getCurrentPicturesVideosNum();
                //int currentFov = GridView.getChildByInstance(Favourite,0).getChildCount();
                Asst.assertEquals("添加一个favourite失败",GalleryFavouriteNumber, number[2]);
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 检查图库菜单显示
    * */
    public static void checkGalleryMenu(){
        clickIWantToButton();
        Asst.assertTrue("Display..选项不存在",getObjectByText(GalleryPage.
                GALLERYSETTINGS_DISPLAY).exists());
        Asst.assertTrue("Manage favourite选项不存在",getObjectByText(GalleryPage.
                GALLERYSETTINGS_FAVOURITE).exists());
        Asst.assertTrue("Delete pictures(s选项不存在",getObjectByText(GalleryPage.
                GALLERYSETTINGS_DELETE_PICTURES).exists());
        Asst.assertTrue("set...选项不存在",getObjectByText(GalleryPage.GALLERYSETTINGS_SET).
                exists());
    }
    public static void getAllPicturesCount(){
        launchGalleryFromAppList();
        AllCurrentNamess = getPictureVideoNmae();
        AllFavours = GalleryFavouriteNumber;
        for(int i = 0; i< getAllCurrentNamesCount(AllCurrentNamess); i++){
            String name[] = AllCurrentNamess[i].split("\\.");
            if(name[1].equals("jpg") || name[1].equals("png") || name[1].equals("gif") ||
                    name[1].equals("bmp")){
                GalleryPicturesNumbers++;
            }else if(name[1].equals("3gp")||name[1].equals("mp4")){
                GalleryVideosNumbers++;
            }
        }
    }

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
  /*  public static void getSomePicturesVideos(){
//        openAppliction(CameraPage.CAMERA);
//        CameraAction.takePictures();
//        CameraAction.takePictures();
//        CameraAction.takePictures();
//        CameraAction.takePictures();
//        CameraAction.recordVideo(CameraPage.DEFAULT_RECORD_TIMES);
        openAppliction(GalleryPage.GALLERY);
        getGalleryPctureVideoNumbers();
    }
*/
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
    * 点击Delete picture（s）点击删除按钮
    * */
    private static void clickDeletePictureButton(){
        clickAndWaitForNewWindowByText(GalleryPage.GALLERYSETTINGS_DELETE_PICTURES);
    }

    /*
    * 点击Confirm按钮
    * */
    private static void clickConfirmButton(){
        clickAndWaitForNewWindowByText(GalleryPage.CONFIRM_BUTTON);
    }

    /*
    * 点击ok按钮
    * */
    private static void clickOKButton(){
        clickAndWaitForNewWindowByText(GalleryPage.OK_BUTTON);
    }

    /*
    * 点击Cancel按钮
    * */
    private static void clickCancelButton(){
        clickAndWaitForNewWindowByText(GalleryPage.CANCEL_BUTTON);
    }


    /*
    * 切换到My Gallery显示
    * */
    public static void changeToMyGalleryDisplay(){

        clickIWantToButton();
        clickDisplayOption();
        clickAndWaitForNewWindowByText(GalleryPage.GALLERYSETTINGS_DISPLAY_MYGALLERY);
        if(scr.exists()) {
            scrollToBegin(20);
        }
    }

    /*
    * 检查MyGalley显示
    * */
    public static void checkMyGalleryDisplay(){
        int[] numbers = getCurrentPicturesVideosNum();
        int pictureNumber = numbers[0];
        int VideoNumber = numbers[1];
        Asst.assertEquals("checkMyGalleryDisplay",GalleryPicturesNumbers + GalleryVideosNumbers,
                pictureNumber + VideoNumber);
    }

    /*
    * 切换到All pictures 显示
    * */
    public static void changeToAllPicturesDisplay(){
        clickIWantToButton();
        clickDisplayOption();
        clickAndWaitForNewWindowByText(GalleryPage.GALLERYSETTINGS_DISPLAY_ALLPICTURES);
        if(scr.exists()) {
            scrollToBegin(20);
        }
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
        if(scr.exists()) {
            scrollToBegin(20);
        }
    }

    /*
    * 检查All Video 显示
    * */
    public static void checkAllVideoDisplay(){
        int[] numbers = getCurrentPicturesVideosNum();
        int pictureNumber = numbers[0];
        int VideoNumber = numbers[1];
        Asst.assertEquals("检查所有图片显示",0, pictureNumber);
        Asst.assertEquals("检查所有视频显示",GalleryVideosNumbers, VideoNumber);
    }

    /*
    * 切换到Favourites显示
    * */
    public static void changeToFavouritesDisplay(){
        clickIWantToButton();
        clickDisplayOption();
        clickAndWaitForNewWindowByText(GalleryPage.GALLERYSETTINGS_DISPLAY_FAVOURITES);
        if(scr.exists()) {
            scrollToBegin(20);
        }
    }

    /*
    * 检查favourite显示
    * */
    public static void checkFavouriteDisplay(){
        if(GalleryFavouriteNumber == 0){
            try {
                Asst.assertTrue("checkFavouriteDisplay",isDisplayEmpty());
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            int[] numbers = getCurrentPicturesVideosNum();
            int Favouritenumbers = numbers[2];
            Asst.assertEquals("checkFavouriteDisplay", GalleryFavouriteNumber, Favouritenumbers);
        }
    }
    /*
    * 判断当前页面是否为空
    * */
    private static boolean isDisplayEmpty() throws UiObjectNotFoundException {
        return GridView.getChild(DisplayEmpty).exists();
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
    private static void moveDownOneGraidViewPage(){
        try {
            int y1 = gDevice.getDisplayHeight() + GridView.getChildByInstance(images,GridView.
                    getChildCount()/2).getBounds().top;
            int y0 = gDevice.getDisplayHeight() + GridView.getChildByInstance(images,GridView.
                    getChildCount()/2).getBounds().bottom;
            int x0 =gDevice.getDisplayWidth()/2;
            int x1 = x0;
            gDevice.swipe(x0,y0,x1,y1, 20);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    * 得到当前的图片数，视频数,最爱照片数
    * */
    public static int[] getCurrentPicturesVideosNum(){
        if(scr.exists()) {
            scrollToBegin(20);
        }
        int nums[] ={0,0,0};
        String []  AllCurrentNames = getPictureVideoNmae();
        nums[2] = GalleryFavouriteNumber;
        for(int i = 0; i<getAllCurrentNamesCount(AllCurrentNames) ; i++){
            String name[] = AllCurrentNames[i].split("\\.");
            if(name[1].equals("jpg") || name[1].equals("png") || name[1].equals("gif") ||
                    name[1].equals("bmp")){
                nums[0]++;
            }else if(name[1].equals("3gp")||name[1].equals("mp4")){
                nums[1]++;
            }
        }
        return nums;
    }
    private static int getAllCurrentNamesCount(String[] names){
        int count = 0;
        for(int i=0;i<names.length;i++){
            if(names[i]!=null){
                count++;
            }
        }
        return count;
    }
    /*
    * 得到当前网格界面所有照片，视频的名字
    * */
    private static String[] getPictureVideoNmae(){
        if(scr.exists()) {
            scrollToBegin(20);
        }
        String [] AllCurrentNames =new String[GalleryPage.MAX_PICTURES_NUMBERS];
        GalleryFavouriteNumber = 0;
        try {
            for(int i=0;i<GridView.getChildCount();i++){
                AllCurrentNames[i]= GridView.getChildByInstance(images,i).getContentDescription();
                if(GridView.getChildByInstance(Favourite,i).exists()){
                    GalleryFavouriteNumber++;
                }
            }
            FirstGridName =AllCurrentNames[0];
            if(GridView.getChildByInstance(images,0).exists()) {
                moveDownOneGraidViewPage();
            }
            while(!FirstGridName.equals(GridView.getChildByInstance(images,0).getContentDescription())){
               int LastGridNumber= GridView.getChildCount() % ColumnsNumber;
                if(LastGridNumber ==0){
                    for(int i = getAllCurrentNamesCount(AllCurrentNames), j = 0; j<ColumnsNumber; j++){
                        AllCurrentNames[i + j] =GridView.getChildByInstance(images,GridView.
                                getChildCount()-j-1).getContentDescription();
                        if(GridView.getChildByInstance(Favourite,GridView.
                                getChildCount()-j-1).exists()){
                            GalleryFavouriteNumber++;
                        }
                    }
                }else if(LastGridNumber != 0){
                    for(int i=getAllCurrentNamesCount(AllCurrentNames),j=0;j<LastGridNumber;j++){
                        AllCurrentNames[i + j] =GridView.getChildByInstance(images,GridView.
                                getChildCount()-j-1).getContentDescription();
                        if(GridView.getChildByInstance(Favourite,GridView.
                                getChildCount()-j-1).exists()){
                            GalleryFavouriteNumber++;
                        }
                    }
                }
                FirstGridName =GridView.getChildByInstance(images,0).getContentDescription();
                moveDownOneGraidViewPage();
            }

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    return AllCurrentNames;
    }

    /*
    * 删除一张随机图片
    * */
    public static void deleteOneRandomPicture(){
        int pictures[] =getCurrentPicturesVideosNum();
        if(scr.exists()) {
            scrollToBegin(20);
        }
        clickIWantToButton();
        clickDeletePictureButton();
        selectDeletePictures();
        clickConfirmButton();
        clickCancelButton();
        Asst.assertEquals("取消随机删除一张照片",GalleryPicturesNumbers,
                pictures[0]);
        clickConfirmButton();
        clickOKButton();
        waitTime(10);
        GalleryPicturesNumbers--;
        pictures=getCurrentPicturesVideosNum();
        Asst.assertEquals("确认随机删除一张照片",GalleryPicturesNumbers,
                pictures[0]);
    }
    /*
    * 删除一个视频
    * */
    public static void deleteOneRandomVideo(){
        int pictures[] =getCurrentPicturesVideosNum();
        if(scr.exists()) {
            scrollToBegin(20);
        }
        clickIWantToButton();
        clickDeletePictureButton();
        selectDeletePictures();
        clickConfirmButton();
        clickCancelButton();
        Asst.assertEquals("取消随机删除一个视频",GalleryVideosNumbers,
                pictures[1]);
        clickConfirmButton();
        clickOKButton();
        waitTime(10);
        GalleryVideosNumbers--;
        pictures=getCurrentPicturesVideosNum();
        Asst.assertEquals("确认随机删除一个视频",GalleryVideosNumbers,
                pictures[1]);
    }

    public static void deleteOnePictureOrVideo(){
        int pictures[] =getCurrentPicturesVideosNum();
        if(scr.exists()) {
            scrollToBegin(20);
        }
        clickIWantToButton();
        clickDeletePictureButton();
        int x =selectDeletePictures();
        clickConfirmButton();
        clickCancelButton();
        Asst.assertEquals("取消随机删除一张照片或者视频",GalleryPicturesNumbers+GalleryVideosNumbers,
                pictures[0]+pictures[1]);
        try {
            String DeleteName = GridView.getChildByInstance(images,x).getContentDescription();
            String spartName[] = DeleteName.split("\\.");
            if(spartName[1].equals("mp4") || spartName[1].equals("3gp")){
                GalleryVideosNumbers--;
            }else  GalleryPicturesNumbers--;
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        clickConfirmButton();
        clickOKButton();
        waitTime(10);
        pictures=getCurrentPicturesVideosNum();
        Asst.assertEquals("确认随机删除一张照片或者视频",GalleryPicturesNumbers+GalleryVideosNumbers,
                pictures[0]+pictures[1]);
    }
    /*
    * 删除多张随机照片
    * */
    public static void deleteMultiRandomPictures(){
        int pictures[] =getCurrentPicturesVideosNum();
        if(scr.exists()) {
            scrollToBegin(20);
        }
        clickIWantToButton();
        clickDeletePictureButton();
        int multipictures =selectMltiRandomPictures();
        clickConfirmButton();
        clickCancelButton();
        Asst.assertEquals("取消随机删除多张照片",GalleryPicturesNumbers,
                pictures[0]);
        clickConfirmButton();
        clickOKButton();
        waitTime(10);
        GalleryPicturesNumbers-=multipictures;
        pictures=getCurrentPicturesVideosNum();
        Asst.assertEquals("确认随机删除多张照片",GalleryPicturesNumbers,
                pictures[0]);
    }


    /*
    * 删除多个视频
    * */
    public static void delteMultiRandomVideos(){
        int pictures[] =getCurrentPicturesVideosNum();
        if(scr.exists()) {
            scrollToBegin(20);
        }
        clickIWantToButton();
        clickDeletePictureButton();
        int multipictures =selectMltiRandomPictures();
        clickConfirmButton();
        clickOKButton();
        waitTime(10);
        GalleryVideosNumbers-=multipictures;
        pictures=getCurrentPicturesVideosNum();
        Asst.assertEquals("确认随机删除多个视频",GalleryVideosNumbers,
                pictures[1]);
    }


    /*
    * 选择一张随机的照片
    * */
    private static int selectDeletePictures(){
        int instance = getOneRandomPictureNumber();
        try {
            while(instance > GridView.getChildCount()){
                moveDownOneGraidViewPage();
                instance -=ColumnsNumber;
            }
            Asst.assertTrue("DeleteCheckBox not exist",
                    GridView.getChildByInstance(DeleteCheckBox,instance).exists() );
            GridView.getChildByInstance(DeleteCheckBox,instance).click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /*
    * 选择多张不同的照片
    * */
    private static int selectMltiRandomPictures(){
        int instance[] = getMultiRandomPictureNumbers();
        int selectedpictures=0;
        for(int i=0;i<instance.length;i++){
            try {
                while(instance[i] > GridView.getChildCount()){
                    moveDownOneGraidViewPage();
                    instance[i] -=ColumnsNumber;
                }
                if(GridView.getChildByInstance(DeleteCheckBox,instance[i]).exists()){
                    GridView.getChildByInstance(DeleteCheckBox,instance[i]).click();
                    selectedpictures++;
                }
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
            if(scr.exists()) {
                scrollToBegin(20);
            }
        }
        return selectedpictures;
    }

    /*
    * 得到一个一张随机的照片
    * */
    private static int getOneRandomPictureNumber(){
        int numbers=0;
        numbers =(int)( Math.random() * GalleryPicturesNumbers);
        if(numbers ==0){
            numbers = 1;
        }
        return numbers - 1;
    }

    /*
    * 得到多个不一样随机数
    * */
    private static int[] getMultiRandomPictureNumbers(){
        int[] numbers =new int[getOneRandomPictureNumber()];
        for(int i=0;i<numbers.length;i++){
            numbers[i] = getOneRandomPictureNumber();
            for(int j=0;j<i;j++){
                if(numbers[j]==numbers[i]){
                    i--;
                }
            }
        }
        return numbers;
    }
/*
    *//*
    * 判断是否是favourite
    * *//*
    private static boolean isFavourite(int indexs){
        boolean FavouriteState =false;
        UiCollection GridView = new UiCollection(new UiSelector().resourceId("com.doro.apps.gallery:id/gridview"));
        UiSelector images = new UiSelector().resourceId("com.doro.apps.gallery:id/favorite_icon");
        try {
            FavouriteState = GridView.getChildByInstance(images,indexs).exists();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        return FavouriteState;
    }

    private static int getFavourCount(){
        int Favourbumbers;
        UiCollection GridView = new UiCollection(new UiSelector().resourceId("com.doro.apps.gallery:id/gridview"));
        UiSelector images = new UiSelector().resourceId("com.doro.apps.gallery:id/favorite_icon");
        Favourbumbers = GridView.getChildCount(images);
        return Favourbumbers;
    }*/

    /* *//*
   * 得到图库显示的照片数，视频数
   * *//*
    private static void getGalleryPctureVideoNumbers(){
        changeToMyGalleryDisplay();
        getPictureVideoNmae();
        AllNames =AllCurrentNames;
        for(int i = 0; i< getAllCurrentNamesCount(); i++){
            String name[] = AllNames[i].split("\\.");
            if(name[1].equals("jpg") || name[1].equals("png") || name[1].equals("gif") ||
                    name[1].equals("bmp")){
                GalleryPicturesNumbers++;
            }else if(name[1].equals("3gp")||name[1].equals("mp4")){
                GalleryVideosNumbers++;
            }
            if(isFavourite(i)){
                GalleryFavouriteNumber++;
            }
        }
    }*/

}

