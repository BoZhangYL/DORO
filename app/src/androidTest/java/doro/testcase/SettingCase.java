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
    SettingAction SettingAction = new SettingAction();
    @Test
    public void setSorageSD(){ //设置默认存储为SD卡
        openAppliction("Settings"); //找到设置应用
        SettingAction.setStorageIsSD(true); //设置默认存储为SD卡
        SettingAction.CheckStorageIsSD(true); //检查默认设置是否为SD卡
    }
    @Test
    public void setSoragePhone(){ //设置默认存储为Phone
        openAppliction("Settings"); //找到设置应用
        SettingAction.setStorageIsSD(false); //设置默认存储为Phone
        SettingAction.CheckStorageIsSD(false); //检查默认设置是否为Phone
    }
}
