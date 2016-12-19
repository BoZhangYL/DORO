package doro.testcase;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import ckt.base.VP4;
import doro.action.AlarmAction;

/**
 * Created by user on 2016/12/06   .
 */
@RunWith(AndroidJUnit4.class)
public class AlarmCase extends VP4{
//    @Test
//    public void case1_enterAndExitAlarm(){
//        openAppliction("Alarm");
//        new AlarmAction().enterAndExitAlarm(5);
//    }
//    @Test
//    public void case2_creatAlarm(){
//        openAppliction("Alarm");
//        new AlarmAction().addRepeatAlarm(0);
//    }
    @Test
    public void case2_creatAlarm(){
        openAppliction("Alarm");
       // new AlarmAction().addRepeatAlarm("Monday",null,null,null,null,null,"Sunday");
      new AlarmAction().addTimeAlarm("07:25");
       // new AlarmAction().setRingtone("Silent");
       // new AlarmAction().deleteOneAlarm();
    }
}
