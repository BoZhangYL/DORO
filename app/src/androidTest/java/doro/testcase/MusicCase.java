package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObject;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import doro.action.MusicAction;

import static doro.page.MusicPage.*;

/**
 * Created by admin on 2016/12/21.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MusicCase extends MusicAction{
    @BeforeClass
    public static void initMusic(){
        unLock();
    }

   @Test
    public void Case1_launchMusic(){//进入MusicPlayer界面
        openMusic();
        checkLanuchMusicResult();
    }

   @Test
    public void Case2_playMusic(){//播放Music
       mDevice.pressBack();
       playMusic();
       waitTime(5);
       //不能获取控件，暂无结果
   }

    @Test
        public void Case3_pauseMusic(){//暂停Music
        waitTime(5);
        pauseMusic();
        //不能获取控件，暂无结果
    }

    @Test
    public void Case4_deleteMusic(){
        openMusic();
        deleteMusic();
        clickCancel();//删除一首歌曲时取消删除
        deleteMusic();
        clickOK();//删除一首歌曲
        deleteAllMusic();
        clickOK();//删除所有歌曲
        checkDeleteAllMusiceResult();
    }
}
