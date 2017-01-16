package doro.testcase;

import android.os.RemoteException;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.action.GalleryAction;

/**
 * Created by bo.zhang on 2016/12/27   .
 */
@RunWith(AndroidJUnit4.class)
public class GalleryCase extends VP4{

 @BeforeClass
    public static void initGalleryCase(){
     initDevice();
     VP4.unLock();
     GalleryAction.getAllPicturesCount();

 }
    @Test
    public void enterGallery(){
//        D8040-981:进入gallery [Enter gallery]
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.checkLaunchGallery();
        VP4.switchToHomePage();
        GalleryAction.launchGalleryFromContacts();
        GalleryAction.checkLaunchGallery();
        VP4.switchToHomePage();
        GalleryAction.launchGalleryFromCamera();
        GalleryAction.checkLaunchGallery();
    }
    @Test
    public void quitGallery(){
//        D8040-982:退出gallery [Quit Gallery]
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.exitGalleryByBackKey();
        GalleryAction.checkExitGallery();
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.exitGalleryByHomeKey();
        GalleryAction.checkExitGallery();
    }
    @Test
    public void GalleryFilter(){
//        D8040-1026:图库过滤 [gallery filter]
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.changeToMyGalleryDisplay();
        GalleryAction.checkMyGalleryDisplay();
        GalleryAction.changeToAllPicturesDisplay();
        GalleryAction.checkAllPicturesDisplay();
        GalleryAction.changeToAllVideosDisplay();
        GalleryAction.checkAllVideoDisplay();
        GalleryAction.changeToFavouritesDisplay();
        GalleryAction.checkFavouriteDisplay();
    }
    @Test
    public void deletePhotos(){
//        D8040-1031:删除图片 [Delete picture]
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.changeToAllPicturesDisplay();
        GalleryAction.checkAllPicturesDisplay();
        GalleryAction.deleteOneRandomPicture();
        GalleryAction.deleteMultiRandomPictures();
    }

    @Test
    public void deleteVideos(){
//        D8040-1032:删除视频 [Delete video]
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.changeToAllVideosDisplay();
        GalleryAction.checkAllVideoDisplay();
        GalleryAction.deleteOneRandomVideo();
        GalleryAction.delteMultiRandomVideos();
    }
    @Test
    public void deleteFromMyGalleryView(){
//        D8040-1033:我的图库界面删除 [delete in my gallery interface]
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.changeToMyGalleryDisplay();
        GalleryAction.checkMyGalleryDisplay();
        GalleryAction.deleteOnePictureOrVideo();
    }
    @Test
    public void checkGalleryMenuDisplay(){
//        D8040-1037:列表显示选项 [list view option]
       GalleryAction.launchGalleryFromAppList();
        GalleryAction.checkGalleryMenu();
    }
    @Test
    public void makeAsFavorite(){
//        图片\视屏标签 [mark as favorite]
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.makeOneFavorite();
        GalleryAction.unmakeOneFavorite();
    }

    @Test
    public void GallerySettings(){
//        D8040-1039:图库设置 [gallery settings]
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.openGallerySettings();
    }

    @Test
    public void GalleryDisplaySettings(){
//        D8040-1041:显示设置 [display option]
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.openDisplaySettings();
    }

    @Test
    public void checkGalleryLandscapeeMode(){
//        D8040-1053:横屏模式查看 [check in landscape mode]
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.swtchToLandscapeMode();
    }

    @Test
    public void SinglePictureOption(){
//        D8040-1054:单张图片选项 [single picture option]
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.changeToAllPicturesDisplay();
        GalleryAction.clickSinglePictureOption();
        GalleryAction.checkSinglePictureOption();
    }

    @Test
    public void deleteFromSinglePictureOption(){
//        D8040-1060:删除单张图片 [delete picture]
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.changeToAllPicturesDisplay();
        GalleryAction.clickSinglePictureOption();
        GalleryAction.deleteSinglePicture();
    }

    @Test
    public void SingleVideoOption(){
        GalleryAction.launchGalleryFromAppList();
        GalleryAction.changeToAllVideosDisplay();
        GalleryAction.clickGingleVideoOption();
        GalleryAction.checkSingleVideoOption();
    }
     /*
    *
    *  D8040-1031:删除图片 [Delete picture]
    *  D8040-1032:删除视频 [Delete video]
    *  D8040-1033:我的图库界面删除 [delete in my gallery interface]
    *  D8040-1034:图库刷新 [gallery refresh]
    *  D8040-1037:列表显示选项 [list view option]
    *  D8040-1038:图片\视屏标签 [mark as favorite]
    *  D8040-1039:图库设置 [gallery settings]
    *  D8040-1041:显示设置 [display option]
    *  D8040-1046:读取SD卡上的图片视频 [read the pictures and videoes from SD card]
    *  D8040-1048:缩放图片 [magnify and narrow picture]
    *  D8040-1053:横屏模式查看 [check in landscape mode]
    *  D8040-1054:单张图片选项 [single picture option]
    *  D8040-1060:删除单张图片 [delete picture]
    *  D8040-1063:点击查看单个视频 [View single video]
    *  D8040-1065:播放视频 [play video]
    * */
}
