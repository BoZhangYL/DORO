package doro.action;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.v4.graphics.BitmapCompat;
import android.widget.ListView;

import com.squareup.spoon.Spoon;

import org.junit.Assert;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Random;

import ckt.base.VP4;
import static doro.page.MusicPage.*;

/**
 * Created by admin on 2016/12/21.
 */

public class MusicAction extends VP4 {
    public void openMusic() {
        openAppliction(Music_Text);
    }

    public void checkLanuchMusicResult() {//检查进入Music的结果
        try {
            Assert.assertEquals("Phone do not enter Music!", Music_Iwantto_Text, getObjectById(Music_Iwantto_ID).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMusic() {//播放Music
        try {
            soryByTitle();
            getLinearLayout(1, Music_SongList_CLASS, Music_Song_CLASS).clickAndWaitForNewWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic() {//暂停Music

        clickByPonit(360, 1119);
    }

    public void soryByTitle(){//选择以歌曲标题排序
        try {
            if (!getObjectById(Music_header_title_ID).getText().equals(Music_Text)) {
                getObjectById(Music_Iwantto_ID).clickAndWaitForNewWindow();
                getObjectByText(Music_SoryBy_Text).clickAndWaitForNewWindow();
                UiCollection Collection = new UiCollection(new UiSelector().className(Music_SongList_CLASS));
                UiSelector childPattern = new UiSelector().className(Music_SoryBy_CLASS);
                Collection.getChildByInstance(childPattern, 0).clickAndWaitForNewWindow();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void deleteMusic() {//删除一首Music
        try {
            soryByTitle();
            clickById(Music_Iwantto_ID);
            scrollAndGetUIObject(Music_Delete_Text);
            clickByText(Music_Delete_Text);
            clickByClass(Music_Title_Song_Class, 0);
            clickConfirm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllMusic() {//删除所有Music
        try {
            soryByTitle();
            clickById(Music_Iwantto_ID);
            scrollAndGetUIObject(Music_Delete_Text);
            clickByText(Music_Delete_Text);
            clickById(Music_DeleteAll_ID);
            clickConfirm();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clickConfirm() {//点击Confirm键
        try {
            UiObject ConfirmButton = getObjectById(Music_ConfirmButton_ID);
            ConfirmButton.clickAndWaitForNewWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickOK() {//点击OK
        try {
            UiObject OKButton = getObjectByText(Music_OK_Text);
            OKButton.clickAndWaitForNewWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickCancel() {//点击Cancel
        try {
            UiObject CancelButton = getObjectByText(Music_Cancel_Text);
            CancelButton.clickAndWaitForNewWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkDeleteAllMusicResult() {//检查删除所有歌曲
        try {
            Assert.assertEquals("Fail to delete all music!", Music_Noresults_Text, getObjectById(Music_Noresults_ID).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkNoResults() {//检查手机中是否有歌曲
        UiObject NoResults = getObjectByText(Music_Noresults_Text);
        Assert.assertTrue("Please copy some music to phone!", !NoResults.exists());
    }

    public void checkPlayMusicResults() {//检查歌曲是否播放
        try {
            mDevice.pressBack();
            NotificationBarAction notificationBarAction=new NotificationBarAction();
            notificationBarAction.pullDownNotificationBar();
            UiObject MusicplayerIcon = getObjectById(Music_Icon_ID);
            Assert.assertTrue("Music is not played!", MusicplayerIcon.exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkPauseMusicResults() {
        //通过对比两张截图是否一致来检查歌曲播放是否暂停
        String playImage="/mnt/sdcard/PauseMusicResult1.png";
        String pauseImage="/mnt/sdcard/PauseMusicResult2.png";
        File F1=new File(playImage);
        mDevice.takeScreenshot(F1);
        waitTime(4);
        File F2=new File(pauseImage);
        mDevice.takeScreenshot(F2);
        try {
            Bitmap m1=BitmapFactory.decodeFile(playImage);
            Bitmap m2=BitmapFactory.decodeFile(pauseImage);
            int width=m2.getWidth();
            int height=m2.getHeight();
            int numDiffPixels=0;
            for (int y=0;y<height;y++){
                for (int x=0;x<width;x++){
                    if (m2.getPixel(x,y)!=m1.getPixel(x,y)){
                        numDiffPixels++;
                    }
                }
            }
            double totalPixels=width*height;
            double diffPercent=numDiffPixels/totalPixels;
            System.out.println(numDiffPixels);
            System.out.println(totalPixels);
            System.out.println(diffPercent);
            Assert.assertTrue("Fail to Pause Music!",1-diffPercent<0.994);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public int getSongsCount() throws UiObjectNotFoundException{
        //计算播放列表中有多少歌曲
        HashSet<String> filename = new HashSet<String>();
        UiScrollable list = new UiScrollable(new UiSelector().className(Music_SongList_CLASS));
        UiSelector selector = new UiSelector().className(Music_Song_CLASS);
        list.scrollToBeginning(5);
        boolean flag = false;
        while (true) {
            int count = list.getChildCount(selector);
            list.setSwipeDeadZonePercentage(0.05);
            for (int i = 1; i < count; i++) {
                String name = list.getChildByInstance(selector, i).getChild(new UiSelector().className(Music_SoryBy_CLASS)).getText();
                filename.add(name);
            }
            if (flag) {
                break;
            }
            if (!list.scrollForward(80)) {
                flag = true;
            }
        }
        list.setSwipeDeadZonePercentage(0.1);
        return filename.size();
    }

}

