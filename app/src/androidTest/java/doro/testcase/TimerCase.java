package doro.testcase;


import android.support.test.runner.AndroidJUnit4;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static doro.page.TimerPage.*;
import ckt.base.VP4;
import doro.action.TimerAction;

/**
 * Created by admin on 2016/12/6.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class  TimerCase extends VP4{
    @BeforeClass
    public static void initTimer(){
        unLock();
    }

    @Test
    public void Case1_launchTimer(){//打开Timer应用
        openAppliction(Timer_Title_Text);
        try {
            Assert.assertEquals("Case1",Timer_Title_Text,getObjectById(Title_ID).getText());
            Thread.sleep(3000);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

   @Test
    public void Case2_setTimerto30s(){//设置30s的Timer
        openAppliction(Timer_Title_Text);
        TimerAction newTimer=new TimerAction();
        newTimer.SetTimerNumber(30);
        try{
        Assert.assertEquals("Case2",Stop_Text,getObjectById(Other_Button_ID).getText());
        newTimer.ClickTimerButton(Stop_Text);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
