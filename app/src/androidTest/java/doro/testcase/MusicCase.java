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
        openMusic();//进入Music
        checkLanuchMusicResult();//检查进入结果
        checkNoResults();//检查是否有歌曲
    }

   @Test
    public void Case2_playMusic(){//播放Music
       openMusic();//进入Music
       playMusic();//播放歌曲
       checkPlayMusicResults();//在锁屏界面判断是否播放音乐
       //由于不能获取播放栏的控件，在锁屏界面判断是否播放音乐
   }

    @Test
        public void Case3_pauseMusic(){//暂停Music
        unLock();//解锁
        openMusic();//进入Music
        playMusic();//播放歌曲
        waitTime(1);
        pauseMusic();//暂停歌曲
        checkPauseMusicResults();//暂停界面截图
        //不能获取播放栏的控件，则通过暂停界面截图与5s后的截图，这两张截图判断是否暂停
    }

    @Test
    public void Case4_deleteMusic(){
        openMusic();
        deleteMusic();
        clickCancel();//删除一首歌曲时取消删除
        deleteMusic();
        clickOK();//删除一首歌曲
        checkNoResults();
        deleteAllMusic();
        clickOK();//删除所有歌曲
        checkDeleteAllMusiceResult();
    }

}
