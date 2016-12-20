package doro.testcase;


import android.support.test.runner.AndroidJUnit4;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import doro.action.TimerAction;


/**
 * Created by admin on 2016/12/6.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class  TimerCase extends TimerAction{
    @BeforeClass
    public static void initTimer(){
        unLock();
    }

    @Test
    public void Case1_launchTimer(){//打开Timer应用
        openTimer();
        checkLaunchTimerResult();
        waitTime(3);
    }

    @Test
    public void Case2_setTimerto30s(){//设置30s的Timer
        openTimer();
        startTimer(30);
        check30sTimerResult();
        clickStopButton();
    }
}

