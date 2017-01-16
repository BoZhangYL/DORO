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
import static doro.page.RecorderPage.RECORDBUTTON_ID;
import static doro.page.RecorderPage.RECORDER_CONFIRM_ID;
import static doro.page.RecorderPage.RECORDER_DELETE_ID;
import static doro.page.RecorderPage.RECORDER_MENU_ID;
import static doro.page.RecorderPage.RECORDER_OK_ID;
import static doro.page.RecorderPage.RECORDER_SELECTALL_ID;
import static doro.page.RecorderPage.RECORDLIST_ID;
import static doro.page.RecorderPage.RECORD_PLAYBUTTON_ID;
import static doro.page.RecorderPage.RECORD_STOPBUTTON_ID;

/**
 * Created by admin on 2016/12/20.
 */

public class RecorderAction extends VP4 {
    public void clearRecords() {//清空录音
        try {
            getObjectById(RECORDLIST_ID).click();
            waitTime(3);
            getObjectById(RECORDER_MENU_ID).click();
            waitTime(3);
            getUiObjectByText(RECORDER_DELETE_ID).click();
            waitTime(3);
            getObjectById(RECORDER_SELECTALL_ID).click();
            waitTime(3);
            getObjectById(RECORDER_CONFIRM_ID).click();
            waitTime(3);
            getUiObjectByText(RECORDER_OK_ID).click();
            waitTime(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pressRecordButon() {//按录音按钮
        try {
            getObjectById(RECORDBUTTON_ID).click();
            waitTime(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveRecorder() {//按停止录音按钮
        try {
            getObjectById(RECORD_STOPBUTTON_ID).click();
            waitTime(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkRecord() {//查看录音
        try {
            getObjectById(RECORDLIST_ID).click();
            waitTime(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playRecord() {

        try {
            getObjectById(RECORD_PLAYBUTTON_ID).click();
            waitTime(3);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

