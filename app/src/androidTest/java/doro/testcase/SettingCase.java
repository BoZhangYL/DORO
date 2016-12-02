package doro.testcase;

import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP2;

/**
 * Created by elon on 2016/12/1.
 */
@RunWith(AndroidJUnit4.class)
public class SettingCase extends VP2 {
    @Test
    public void testA(){
        initDevice();
        gDevice.pressHome();
        clickByText("Settings");
        Spoon.screenshot("takeScreenShot","Settings");
    }
}
