package doro.action;

import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import com.squareup.spoon.Spoon;

import org.junit.Assert;

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
            Assert.assertEquals("没有进入音乐播放器！", Music_Iwantto_Text, getObjectById(Music_Iwantto_ID).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMusic() {//播放Music
        try {
            getObjectById(Music_Iwantto_ID).clickAndWaitForNewWindow();
            getObjectByText(Music_SoryBy_Text).clickAndWaitForNewWindow();
            getObjectByText(Music_Title_Text).clickAndWaitForNewWindow();
            getLinearLayout(1,Music_SongList_CLASS,Music_Song_CLASS).clickAndWaitForNewWindow();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void pauseMusic(){//暂停Music
        clickByPonit(360,1119);
    }

    public void deleteMusic(){//删除一首Music
        try {
            getObjectById(Music_Iwantto_ID).clickAndWaitForNewWindow();
            getObjectByText(Music_SoryBy_Text).clickAndWaitForNewWindow();
            getObjectByText(Music_Title_Text).clickAndWaitForNewWindow();
            clickById(Music_Iwantto_ID);
            scrollAndGetUIObject(Music_Delete_Text);
            clickByText(Music_Delete_Text);
            clickByClass(Music_Title_Song_Class,0);
            clickConfirm();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteAllMusic(){//删除所有Music
        try {
            getObjectById(Music_Iwantto_ID).clickAndWaitForNewWindow();
            getObjectByText(Music_SoryBy_Text).clickAndWaitForNewWindow();
            getObjectByText(Music_Title_Text).clickAndWaitForNewWindow();
            clickById(Music_Iwantto_ID);
            scrollAndGetUIObject(Music_Delete_Text);
            clickByText(Music_Delete_Text);
            clickById(Music_DeleteAll_ID);
            clickConfirm();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void clickConfirm(){//点击Confirm键
        try {
            UiObject ConfirmButton = getObjectById(Music_ConfirmButton_ID);
            ConfirmButton.clickAndWaitForNewWindow();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void clickOK(){//点击OK
        try {
            UiObject OKButton = getObjectByText(Music_OK_Text);
            OKButton.clickAndWaitForNewWindow();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void clickCancel(){//点击Cancel
        try {
            UiObject CancelButton = getObjectByText(Music_Cancel_Text);
            CancelButton.clickAndWaitForNewWindow();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void checkDeleteAllMusiceResult(){
        try {
            Assert.assertEquals("删除所有歌曲失败！", Music_Noresults_Text, getObjectById(Music_Noresults_ID).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkNoResults(){
        UiObject NoResults=getObjectByText(Music_Noresults_Text);
        Assert.assertTrue("请在手机中拷入一些歌曲！",!NoResults.exists());
    }
    public void checkPlayMusicResults(){
        try {
            waitTime(1);
            mDevice.sleep();
            waitTime(1);
            mDevice.wakeUp();
            UiObject MusicplayerIcon=getObjectById(Music_Icon_ID);
            Assert.assertTrue("没有播放音乐！",MusicplayerIcon.exists());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void checkPauseMusicResults(){
        Spoon.screenshot("checkPauseMusicResult1");
        waitTime(5);
        Spoon.screenshot("checkPauseMusicResult2");
    }


}

