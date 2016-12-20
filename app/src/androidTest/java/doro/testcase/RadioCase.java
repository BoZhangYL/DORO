package doro.testcase;

import android.support.test.runner.AndroidJUnit4;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import doro.action.RadioAction;

/**
 * Created by admin on 2016/12/20.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RadioCase extends RadioAction {
    @BeforeClass
    public static void initRadio() {
        unLock();
    }

    @Test
    public void Case1_enterRadioWithoutHeadset(){
        openRadio();
        checkRadioWithoutHeadsetResult();
    }
}
