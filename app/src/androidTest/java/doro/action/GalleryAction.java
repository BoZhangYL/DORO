package doro.action;

import android.graphics.Rect;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

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
    private static void moveDownOneGraidViewPage(Rect rect){
            int x0 = rect.right/2;
            int y0 = rect.bottom;
            int x1 = x0;
            int y1 = rect.top;
            gDevice.swipe(x0,y0,x1,y1, 20);
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
                moveDownOneGraidViewPage(GridView.getChildByInstance(images, 0).getBounds());
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
                moveDownOneGraidViewPage(GridView.getChildByInstance(images,0).getBounds());
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
        Asst.assertEquals("Cancel delete one picture",GalleryPicturesNumbers,
                pictures[0]);
        clickConfirmButton();
        clickOKButton();
        waitTime(10);
        GalleryPicturesNumbers--;
        pictures=getCurrentPicturesVideosNum();
        Asst.assertEquals("Cancel delete one picture",GalleryPicturesNumbers,
                pictures[0]);
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
        Asst.assertEquals("Cancel delete one picture",GalleryPicturesNumbers,
                pictures[0]);
        clickConfirmButton();
        clickOKButton();
        waitTime(10);
        GalleryPicturesNumbers-=multipictures;
        pictures=getCurrentPicturesVideosNum();
        Asst.assertEquals("Cancel delete one picture",GalleryPicturesNumbers,
                pictures[0]);
    }


    /*
    * 选择一张随机的照片
    * */
    private static int selectDeletePictures(){
        int instance = getOneRandomPictureNumber();
        try {
            while(instance > GridView.getChildCount()){
                moveDownOneGraidViewPage(GridView.getChildByInstance(images,0).getBounds());
                instance -=ColumnsNumber;
            }
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
                    moveDownOneGraidViewPage(GridView.getChildByInstance(images,0).getBounds());
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

