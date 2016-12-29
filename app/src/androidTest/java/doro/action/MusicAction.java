package doro.action;

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

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

    public void checkLanuchMusicResult() {
        try {
            Assert.assertEquals("Case1_launchMusic(", Music_Iwantto_Text, getObjectById(Music_Iwantto_ID).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMusic() {
        try {
            String Title = getObjectById(Music_header_title_ID).getText();
            switch (Title) {
                case Music_Text:
                    /*Random ran = new Random();
                    int result = ran.nextInt(8);
                    clickByClass(Music_Title_Song_Class,result);*/
                    clickById(Music_Title_Song_ID);
                    break;
                case Music_Artist_Text:
                    UiObject Song=getObjectById(Music_Artist_Song_ID);
                    while (Song.exists()) {
                           clickById(Music_Artist_Song_ID);
                    }
                    clickById(Music_Title_Song_ID);
                    break;
                case Music_Al​bum_Text:
                    clickById(Music_Artist_Song_ID);
                    clickById(Music_Title_Song_ID);
                    break;
                case Music_Genre_Text:
                    break;
                case Music_Favourites_Text:
                    break;
                }
            }catch(Exception e){
                e.printStackTrace();
        }
    }
    public void pauseMusic(){
        mDevice.pressBack();
        clickByPonit(360,1119);
    }
    public void deleteMusic(){
        try {
            clickById(Music_Iwantto_ID);
            scrollAndGetUIObject(Music_Delete_Text);
            clickByText(Music_Delete_Text);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}