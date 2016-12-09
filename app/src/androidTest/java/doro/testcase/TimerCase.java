package doro.testcase;

import android.app.Instrumentation;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ckt.base.VP4;
import doro.action.TimerAction;

/**
 * Created by admin on 2016/12/6.
 */
@RunWith(AndroidJUnit4.class)
public class TimerCase extends VP4{
    @Test
    public void launchTimer(){//打开Timer应用
        unLock();
        openAppliction("Timer");
    }
    @Test
    public void setTimerto30s(){//设置30s的Timer
        openAppliction("Timer");
        TimerAction newTimer=new TimerAction();
        newTimer.SetTimerNumber(30);
        newTimer.ClickTimerButton("Start");
    }

}
