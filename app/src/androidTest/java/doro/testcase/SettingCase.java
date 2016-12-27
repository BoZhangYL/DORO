package doro.testcase;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.action.SettingAction;

/**
 * Created by elon on 2016/12/1.
 */
@RunWith(AndroidJUnit4.class)
public class SettingCase extends VP4 {
    @Test
    public void testa(){
        openAppliction("Settings"); //找到设置应用
        new SettingAction().dateAndTime(); //找到时间设置
        new SettingAction().whatProvidedTime(0);//使用自定义时间
        new SettingAction().setDate("2013-12-31");
    }
}
