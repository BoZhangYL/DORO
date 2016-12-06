package doro.testcase;

import com.squareup.spoon.Spoon;

import org.junit.Test;

import static ckt.base.VP.gDevice;
import static ckt.base.VP.initDevice;
import static ckt.base.VP2.clickByText;

/**
 * Created by user on 2016/12/06   .
 */

public class AlarmCase {
    @Test
    public void testA(){
        initDevice();
        gDevice.pressHome();
        clickByText("Settings");
        Spoon.screenshot("takeScreenShot","Settings");
    }
}
