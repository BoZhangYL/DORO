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

    public void checkLanuchMusicResult() {//检查进入Music的结果
        try {
            Assert.assertEquals("Case1_launchMusic", Music_Iwantto_Text, getObjectById(Music_Iwantto_ID).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMusic() {//播放Music
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

    public void pauseMusic(){//暂停Music
        mDevice.pressBack();
        clickByPonit(360,1119);
    }

    public void deleteMusic(){//删除一首Music
        try {
            UiObject ConfirmButton = getObjectById(Music_ConfirmButton_ID);
            if(!ConfirmButton.exists()) {
                UiObject IwanttoCloseButton=getObjectById(Music_IwanttoClose_ID);
                if(!IwanttoCloseButton.exists()){
                    clickById(Music_Iwantto_ID);
                }
                scrollAndGetUIObject(Music_Delete_Text);
                clickByText(Music_Delete_Text);
            }
            Random i = new Random();
            int j = i.nextInt(5);
            clickByClass(Music_Title_Song_Class,j);
            clickConfirm();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteAllMusic(){//删除所有Music
        try {
            UiObject ConfirmButton = getObjectById(Music_ConfirmButton_ID);
            if(!ConfirmButton.exists()) {
                UiObject IwanttoCloseButton = getObjectById(Music_IwanttoClose_ID);
                if (!IwanttoCloseButton.exists()) {
                    clickById(Music_Iwantto_ID);
                }
                scrollAndGetUIObject(Music_Delete_Text);
                clickByText(Music_Delete_Text);
            }
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
            Assert.assertEquals("Case4_deleteMusic", Music_Noresults_Text, getObjectById(Music_Noresults_ID).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkNoResults(){
        UiObject NoResults=getObjectByText("Music_Noresults_Text");
        Assert.assertTrue("Please copy some music to phone!",!NoResults.exists());
    }



}

