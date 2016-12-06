package doro.testcase;

import org.junit.Test;

import static ckt.base.VP.gDevice;
import static ckt.base.VP.initDevice;

/**
 * Created by user on 2016/12/06   .
 */

public class AlarmCase {
    @Test
    public void testA(){
        initDevice();
        gDevice.pressHome();

    }
}
