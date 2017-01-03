package doro.testcase;

import android.provider.Settings;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;

import com.doro.R;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.action.RecorderAction;

import static android.R.attr.id;

/**
 * Creted by admin on 2016/12/20.
 */
@RunWith(AndroidJUnit4.class)
public class RecorderCase extends VP4 {
    RecorderAction RecordAction = new RecorderAction();
    @BeforeClass
    public static void initRecorder(){
        unLock();//解锁
    }
    @Test
    public void addRecoder(){
    try{
        openAppliction("Rec\u200Border");//进入recorder界面
        RecordAction.clearRecords();//清除全部录音
        RecordAction.pressRecordButon();//按录音键开始录音
        RecordAction.saveRecorder();//按停止键保存录音
        RecordAction.checkRecord();//按列表键查看录音
        Assert.assertTrue("没有成功保存新录音文件",getObjectById("com.doro.apps.soundrec:id/icon").exists());//断言函数：判断录音是否存在
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void playRecoder(){//无法进行断言
        openAppliction("Rec\u200Border");//进入recorder界面
        RecordAction.pressRecordButon();//按录音键开始录音
        RecordAction.saveRecorder();//按停止键保存录音
        RecordAction.checkRecord();//按列表键查看录音
        RecordAction.playRecord();//播放录音

    }
}
