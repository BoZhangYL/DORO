package doro.action;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;

import org.junit.Test;

import ckt.base.VP4;

import static android.R.attr.id;
import static android.R.attr.staticWallpaperPreview;
import static android.view.View.Z;
import static ckt.base.VP2.getObjectsById;

/**
 * Created by admin on 2016/12/20.
 */

public class RecorderAction extends VP4{
    public void pressRecordButon(){//按录音按钮
        try {
             getObjectById("com.doro.apps.soundrec:id/btn_rec").click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void saveRecorder(){//按停止录音按钮
        try {
            getObjectById("com.doro.apps.soundrec:id/btn_stop").click();
            waitTime(30);
        }catch (Exception e){
            e.printStackTrace();
    }
  }
    public void checkRecord(){
        try {
        getObjectById("com.doro.apps.soundrec:id/button").click();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
