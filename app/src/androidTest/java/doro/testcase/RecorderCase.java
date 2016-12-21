package doro.testcase;

import android.provider.Settings;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiSelector;

import com.doro.R;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.action.RecorderAction;

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
    public void EnterRecoder(){
        openAppliction("Rec\u200Border");//进入recorder界面
        RecordAction.pressRecordButon();//按录音键;
        RecordAction.saveRecorder();//按停止键保存录音
        try{getObjectById("com.doro.apps.soundrec:id/button").click();
        }catch (Exception e){
            e.printStackTrace();
        }
        UiCollection Collection=new UiCollection(new UiSelector().className("android.widget.FrameLayout"));
        int recordCount=Collection.getChildCount(new UiSelector().resourceId("com.doro.apps.soundrec:id/icon_and_label"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Record count:"+recordCount);


    }
}
