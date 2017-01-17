package doro.testcase;

import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.action.InternetAction;


/**
 * Created by bo.zhang on 2017/01/17   .
 */
@RunWith(AndroidJUnit4.class)
public class InternetCase extends VP4{
    @BeforeClass
    public static void initInternet(){
        VP4.unLock();
    }
    @Test
    public void LaunchIntenetFromApplictionList(){
        InternetAction.openInternetApp();
    }
    @Test
    public void exitInternet(){
        InternetAction.exitInternetApp();
    }
    @Test
    public void checkOverviewPage(){
        InternetAction.openInternetApp();
        InternetAction.checkOverView();
    }
}
